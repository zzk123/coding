package com.zzk.IO.NIO;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-06 16:19
 */
public class testDemo2 {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(83));

        Selector selector = Selector.open();
        //服务器通道注册SelectionKey.OP_ACCEPT 事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        try{
            while(true){
                //如果条件成立。说明本次询问selector，并没有获取任何准备好的、感兴趣的事件
                if(selector.select(100) == 0){
                    continue;
                }
                //获取到的事件类类型
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();

                while(selectionKeyIterator.hasNext()){
                    SelectionKey readyKey = selectionKeyIterator.next();
                    //删除readKey，防止下次获取到
                    selectionKeyIterator.remove();

                    SelectableChannel selectableChannel = readyKey.channel();
                    if(readyKey.isValid() && readyKey.isAcceptable()){
                        System.out.println("通道准备好了");
                        //当server socket channel 通道准备好了，就可以从server socket channel中获取 socketChannel
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectableChannel;
                        SocketChannel socketChannel = serverSocketChannel1.accept();
                        registerSocketChannel(socketChannel, selector);
                    }else if(readyKey.isValid() && readyKey.isConnectable()){
                        System.out.println("建立连接");
                    }else if(readyKey.isValid() && readyKey.isReadable()){
                        System.out.println("数据完成，可以去读取");
                        readSocketChannel(readyKey);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
    }

    /**
     * 在Server socket channel接收到/准备好 一个新的tcp连接后
     * 就会向程序返回一个新的 socketChannel
     * 但是这个新的socket channel 并没有在 selector 选择器/代理器中注册
     * 所以程序还没法通过 selector通知这个 socket channel的事件
     * 所以拿到新的 socket channel后，第一个事情就是到 selector 选择器/代理器中注册这个事件
     * @param socketChannel
     * @param selector
     * @throws Exception
     */
    private static void registerSocketChannel(SocketChannel socketChannel, Selector selector) throws Exception{
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(2048));
    }


    /**
     * 用于读取从客户端传来的消息
     * 并且观察从客户端过来的soket channel 在经过多次传输后，是否完成传输
     * 如果传输完成，就返回一个true的标记
     * @param readyKey
     * @throws Exception
     */
    private static void readSocketChannel(SelectionKey readyKey) throws Exception{
        SocketChannel clientSocketChannel = (SocketChannel) readyKey.channel();

        InetSocketAddress socketAddress = (InetSocketAddress) clientSocketChannel.getRemoteAddress();
        Integer resourcePort = socketAddress.getPort();

        ByteBuffer contextBytes = (ByteBuffer) readyKey.attachment();

        int realLen = 1;
        try{
            realLen = clientSocketChannel.read(contextBytes);
        }catch (Exception e){
            clientSocketChannel.close();
            return;
        }

        if(realLen == -1){
            return;
        }

        contextBytes.flip();

        byte[] messageBytes = contextBytes.array();
        String messageEncode = new String(messageBytes, "UTF-8");
        String message = URLDecoder.decode(messageEncode, "UTF-8");

        if(message.indexOf("over") != -1){
            contextBytes.clear();
            ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("回发结果", "UTF-8").getBytes());
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        }else{
            contextBytes.position(realLen);
            contextBytes.limit(contextBytes.capacity());
        }
    }
}

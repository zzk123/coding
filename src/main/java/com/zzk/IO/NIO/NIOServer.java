package com.zzk.IO.NIO;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-06 15:53
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        ServerSocketChannel sChannel = ServerSocketChannel.open();
        sChannel.configureBlocking(false);
        sChannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket = sChannel.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        serverSocket.bind(address);

        while(true){

            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while(keyIterator.hasNext()){

                SelectionKey key = keyIterator.next();

                if(key.isAcceptable()){

                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                    // 服务器会为每个连接创建一个SocketChannel
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    // 这个新连接主要用于从客户端读取数据
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if(key.isReadable()){

                    SocketChannel channel = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(channel));
                    channel.close();
                }

                keyIterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel socketChannel) throws Exception{

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();

        while(true){

            buffer.clear();
            int n = socketChannel.read(buffer);
            if(n == -1){
                break;
            }
            buffer.flip();
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for(int i = 0; i < limit; i++){
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
}

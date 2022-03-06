package com.zzk.IO.AIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-06 19:27
 */
public class SocketServer {

    private static final Object waitObject = new Object();

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(group);

        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", 83));
        serverSocketChannel.accept(null, new ServerSocketChannelHandle(serverSocketChannel));

        synchronized (waitObject){
            waitObject.wait();
        }

    }

    static class ServerSocketChannelHandle implements CompletionHandler<AsynchronousSocketChannel, Void>{

        private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

        public ServerSocketChannelHandle(AsynchronousServerSocketChannel asynchronousServerSocketChannel){
            this.asynchronousServerSocketChannel = asynchronousServerSocketChannel;
        }

        /**
         *
         * @param result
         * @param attachment
         */
        @Override
        public void completed(AsynchronousSocketChannel result, Void attachment) {
            this.asynchronousServerSocketChannel.accept(attachment, this);

            ByteBuffer readBuffer = ByteBuffer.allocate(50);
            result.read(readBuffer, new StringBuffer(), new SocketChannelReadHandle(result, readBuffer));
        }

        @Override
        public void failed(Throwable exc, Void attachment) {

        }
    }

    static class SocketChannelReadHandle implements CompletionHandler<Integer, StringBuffer>{

        private AsynchronousSocketChannel asynchronousSocketChannel;

        private ByteBuffer byteBuffer;

        public SocketChannelReadHandle(AsynchronousSocketChannel asynchronousSocketChannel, ByteBuffer byteBuffer){
            this.asynchronousSocketChannel = asynchronousSocketChannel;
            this.byteBuffer = byteBuffer;
        }


        @Override
        public void completed(Integer result, StringBuffer attachment) {
            if(result == -1){
                try{
                    this.asynchronousSocketChannel.close();
                }catch (Exception e){

                }
                return;
            }
            this.byteBuffer.flip();
            byte[] contexts = new byte[1024];
            this.byteBuffer.get(contexts, 0, result);
            this.byteBuffer.clear();
            try{
                String nowContext = new String(contexts, 0, result, "UTF-8");
                attachment.append(nowContext);
            }catch (Exception e){

            }

            if(attachment.indexOf("over") == -1){
                return;
            }

            this.asynchronousSocketChannel.read(this.byteBuffer, attachment, this);
        }



        @Override
        public void failed(Throwable exc, StringBuffer attachment) {
            try{
                this.asynchronousSocketChannel.close();
            }catch (Exception e){

            }
        }
    }
}

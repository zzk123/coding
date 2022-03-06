package com.zzk.IO.BIO;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @program: coding
 * @description: 请求线程
 * @author: zzk
 * @create: 2022-03-06 15:22
 */
public class SocketClientRequestThread implements Runnable {

    private CountDownLatch countDownLatch;

    private Integer clientIndex;

    public SocketClientRequestThread(CountDownLatch countDownLatch, Integer clientIndex){
        this.clientIndex = clientIndex;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        Socket socket = null;
        OutputStream clientRequest = null;
        InputStream clientResponse = null;

        try{
            socket = new Socket("localhost", 83);
            clientRequest = socket.getOutputStream();
            clientResponse = socket.getInputStream();

            this.countDownLatch.await();

            clientRequest.write(("这是第" + this.clientIndex + "个请求").getBytes());
            clientRequest.flush();

            int maxLen = 1024;
            byte[] contextBytes = new byte[maxLen];
            int realLen;
            String message = "";
            while((realLen = clientResponse.read(contextBytes, 0, maxLen)) != -1){
                message += new String(contextBytes, 0, realLen);
            }
            System.out.println(message);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(clientRequest != null){
                    clientRequest.close();
                }
                if(clientResponse != null){
                    clientResponse.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

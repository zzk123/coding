package com.zzk.IO.BIO;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: coding
 * @description: 接收请求响应请求
 * @author: zzk
 * @create: 2022-03-06 15:30
 */
public class testDemo3  {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(83);

        try{
            while (true){
                Socket socket = serverSocket.accept();

                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                Integer sourcePort = socket.getPort();
                int maxLen = 2048;
                byte[] contextBytes = new byte[maxLen];
                int realLen = in.read(contextBytes, 0, maxLen);

                String message = new String(contextBytes, 0, realLen);
                System.out.println(message);
                out.write("回发响应".getBytes());

                out.close();
                in.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                serverSocket.close();
            }
        }
    }
}

package com.zzk.IO.NIO;

import com.zzk.algorithm.coding.other.Solution;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-06 16:05
 */
public class NIOClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 83);
        OutputStream out = socket.getOutputStream();
        String s = "over hello world";
        out.write(s.getBytes());
        out.close();
    }
}

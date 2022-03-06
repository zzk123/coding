package com.zzk.IO.BIO;

import java.util.concurrent.CountDownLatch;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-06 15:21
 */
public class testDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Integer clientNumber = 20;
        CountDownLatch countDownLatch = new CountDownLatch(clientNumber);

        for(int index=0; index < clientNumber; index++, countDownLatch.countDown()){
            SocketClientRequestThread clientRequestThread = new SocketClientRequestThread(countDownLatch,index);
            new Thread(clientRequestThread).start();
        }

        synchronized (testDemo2.class){
            testDemo2.class.wait();
        }
    }
}

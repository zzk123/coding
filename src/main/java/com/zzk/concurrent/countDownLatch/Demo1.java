package com.zzk.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 15:50
 */
public class Demo1 {

    static class MyThread extends Thread{

        private CountDownLatch countDownLatch;

        public MyThread(String name, CountDownLatch countDownLatch){
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " doing something");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        MyThread t1 = new MyThread("t1", countDownLatch);
        MyThread t2 = new MyThread("t2", countDownLatch);
        t1.start();
        t2.start();
        System.out.println("Waiting for t1 thread and t2 thread to finish");
        try{
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " continue");
    }
}

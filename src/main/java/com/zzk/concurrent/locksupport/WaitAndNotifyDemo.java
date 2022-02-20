package com.zzk.concurrent.locksupport;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-19 23:11
 */
public class WaitAndNotifyDemo {

    static class MyThread extends Thread{
        @Override
        public void run() {
            synchronized (this){
                System.out.println("before notify");
                notify();
                System.out.println("after notify");
            }
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        synchronized (myThread){
            try{
                myThread.start();
                //主线程睡眠3s
                Thread.sleep(3000);
                System.out.println("before await");
                //阻塞主线程
                myThread.wait();
                System.out.println("after await");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

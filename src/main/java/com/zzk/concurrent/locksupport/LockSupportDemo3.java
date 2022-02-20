package com.zzk.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-19 23:20
 */
public class LockSupportDemo3 {

    static class MyThread extends Thread{
        private Object object;

        public MyThread(Object object){
            this.object = object;
        }

        @Override
        public void run() {
            System.out.println("before interrupt");
            try{
                //主线程睡眠3s
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Thread thread = (Thread) object;
            //中断线程
            thread.interrupt();
            System.out.println("after interrupt");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");
        //获取许可
        LockSupport.park("ParkAndUnParkDemo");
        System.out.println("after park");
    }
}

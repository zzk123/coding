package com.zzk.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-19 23:20
 */
public class LockSupportDemo2 {

    static class MyThread extends Thread{
        private Object object;

        public MyThread(Object object){
            this.object = object;
        }

        @Override
        public void run() {
            System.out.println("before unpark");
            //释放许可
            LockSupport.unpark((Thread) object);
            System.out.println("after unpark");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();

        try{
            //主线程睡眠3s
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("before park");
        //获取许可
        LockSupport.park("ParkAndUnParkDemo");
        System.out.println("after park");
    }
}

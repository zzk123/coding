package com.zzk.concurrent.reentrantLock;

import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-20 21:48
 */
public class ReentrantLockDemo {

    static class MyThread extends Thread{

        private Lock lock;

        public MyThread(String name, Lock lock){
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try{
                System.out.println(Thread.currentThread() + " running");
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);

        MyThread t1 = new MyThread("t1", lock);
        MyThread t2 = new MyThread("t2", lock);
        MyThread t3 = new MyThread("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }
}

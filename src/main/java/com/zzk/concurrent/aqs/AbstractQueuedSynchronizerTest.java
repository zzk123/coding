package com.zzk.concurrent.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-19 22:22
 */
public class AbstractQueuedSynchronizerTest {

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
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();

        MyThread t1 = new MyThread("t1", lock);
        MyThread t2 = new MyThread("t2", lock);
        t1.start();
        t2.start();
    }
}

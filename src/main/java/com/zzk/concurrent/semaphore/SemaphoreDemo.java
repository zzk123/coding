package com.zzk.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 17:18
 */
public class SemaphoreDemo {

    static class MyThread extends Thread{

        private Semaphore semaphore;

        public MyThread(String name, Semaphore semaphore){
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int count = 3;
            System.out.println(Thread.currentThread().getName() + " trying to acquire");
            try{
                semaphore.acquire(count);
                System.out.println(Thread.currentThread().getName() + " acquire successfully");
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " release successfully");
            }
        }
    }

    public static void main(String[] args) {
        int sem_size = 10;
        Semaphore semaphore = new Semaphore(sem_size);
        MyThread t1 = new MyThread("t1", semaphore);
        MyThread t2 = new MyThread("t2", semaphore);
        t1.start();
        t2.start();
        int permits = 5;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try{
            semaphore.acquire(permits);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }
    }
}

package com.zzk.concurrent.cyclicBarrier;

import com.zzk.jvm.classloading.SubClass;

import java.util.concurrent.CyclicBarrier;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 16:52
 */
public class Demo {

    static class MyThread extends Thread{

        private CyclicBarrier cyclicBarrier;

        public MyThread(String name, CyclicBarrier cyclicBarrier){
            super(name);
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " going to await");
            try{
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " continue");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(3, new Thread("barrierAction"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " barrier action");
            }
        });
        MyThread t1 = new MyThread("t1", barrier);
        MyThread t2 = new MyThread("t2", barrier);
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " going to await");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " continue");
    }
}

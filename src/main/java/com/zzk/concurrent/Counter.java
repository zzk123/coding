package com.zzk.concurrent;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-12 20:59
 */
public class Counter {

    private AtomicInteger atomicI = new AtomicInteger(0);

    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int j = 0; j < 100; j++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 10000; i++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for(Thread t : ts){
            t.start();
        }

        for(Thread t : ts){
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount(){
        for(;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if(suc){
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count(){
        i++;
    }
}

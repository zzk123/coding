package com.zzk.concurrent.countDownLatch;

import java.util.*;

/**
 * @program: coding
 * @description: 线程1添加10个元素到容器中，线程2实现监控元素个数
 * @author: zzk
 * @create: 2022-02-27 15:56
 */
public class Demo2 {

    static class T2{

        volatile List list = new ArrayList<>();

        public void add(int i){
            list.add(i);
        }

        public int getSize(){
            return list.size();
        }
    }

    public static void main(String[] args) {
        T2 t2 = new T2();

        Object lock = new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 启动");
                if(t2.getSize() != 5){
                    try{
                        //会释放锁
                        lock.wait();
                        System.out.println("t2 结束");
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t2").start();

        new Thread(()->{
            synchronized (lock){
                System.out.println("t1 启动");
                for(int i=0; i<9; i++){
                    t2.add(i);
                    System.out.println("add " + i);
                    if(t2.getSize() == 5){
                        //不会释放锁
                        lock.notify();
                        try{
                            lock.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1").start();
    }
}

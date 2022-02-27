package com.zzk.concurrent.countDownLatch;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @program: coding
 * @description: 线程1添加10个元素到容器中，线程2实现监控元素个数
 * 使用 CountDownLatch 代替 wait notify 好处是通讯简单，不涉及锁定，count值为 0时当前线程继续执行
 * @author: zzk
 * @create: 2022-02-27 16:05
 */
public class Demo3 {

    static class T3{

        volatile List list = new ArrayList<>();

        public void add(int i){
            list.add(i);
        }

        public int getSize(){
            return list.size();
        }
    }

    public static void main(String[] args) {
        T3 t3 = new T3();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start");
            if(t3.getSize() != 5){
                try{
                    countDownLatch.await();
                    System.out.println("t2 end");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 start");
            for(int i=0 ;i<9; i++){
                t3.add(i);
                System.out.println("add " + i);
                if( t3.getSize() == 5){
                    System.out.println("countdown is open");
                    countDownLatch.countDown();
                }
            }
            System.out.println("t1 end");
        }, "t1").start();
    }
}

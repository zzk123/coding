package com.zzk.concurrent.blockingQueue.synchronousQueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-26 17:54
 */
public class SynchronousQueueDemo1 {

    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Customer(queue).start();
        new Product(queue).start();
    }

    static class Product extends Thread{

        SynchronousQueue<Integer> queue;

        public Product(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true){
                int rand = new Random().nextInt(1000);
                System.out.println("生产了一个产品：" + rand);
                System.out.println("等待三秒运送出去....");
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                queue.offer(rand);
                System.out.println("产品生成完成：" + rand);
            }
        }
    }

    static class Customer extends Thread{

        SynchronousQueue<Integer> queue;

        public Customer(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true){
                try {
                    System.out.println("消费了一个产品："+queue.take());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("--------------------------");
            }
        }
    }
}

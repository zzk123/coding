package com.zzk.concurrent.blockingQueue.synchronousQueue;

import java.util.concurrent.SynchronousQueue;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-26 17:43
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) throws Exception{
        final SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start put");
                try{
                    queue.put(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("end put");
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    System.out.println("take from putThread: " + queue.take());
                } catch (InterruptedException e) {
                }
                System.out.println("take thread end");
            }
        });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();
    }
}

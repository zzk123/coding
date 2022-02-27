package com.zzk.concurrent.blockingQueue.delyQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-24 00:18
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws Exception {
        DelayQueue delayQueue = new DelayQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                delayQueue.offer(new MyDelayedTask("task1", 10000));
                delayQueue.offer(new MyDelayedTask("task2", 3900));
                delayQueue.offer(new MyDelayedTask("task3", 1900));
                delayQueue.offer(new MyDelayedTask("task4", 5900));
                delayQueue.offer(new MyDelayedTask("task5", 6900));
            }
        }).start();

        while(true){
            Delayed take = delayQueue.take();
            System.out.println(take);
        }
    }
}

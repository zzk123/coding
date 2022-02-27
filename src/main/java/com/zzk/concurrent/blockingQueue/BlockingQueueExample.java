package com.zzk.concurrent.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-23 23:55
 */
public class BlockingQueueExample {

    public static void main(String[] args) {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

    }
}

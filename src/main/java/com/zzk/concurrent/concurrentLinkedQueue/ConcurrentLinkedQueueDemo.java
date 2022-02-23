package com.zzk.concurrent.concurrentLinkedQueue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-23 22:59
 */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();
        PutThread p1 = new PutThread(clq);
        GetThread p2 = new GetThread(clq);

        p1.start();
        p2.start();
    }
}

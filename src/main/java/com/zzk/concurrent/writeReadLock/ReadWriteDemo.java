package com.zzk.concurrent.writeReadLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-20 23:07
 */
public class ReadWriteDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
        ReadThread rt1 = new ReadThread("rt1", rrwLock);
        ReadThread rt2 = new ReadThread("rt2", rrwLock);
        WriteThread wt3 = new WriteThread("wt1", rrwLock);
        rt1.start();
        rt2.start();
        wt3.start();
    }
}

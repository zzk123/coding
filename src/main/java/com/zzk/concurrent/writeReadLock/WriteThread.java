package com.zzk.concurrent.writeReadLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-20 23:04
 */
public class WriteThread extends Thread {

    private ReentrantReadWriteLock rrwLock;

    public WriteThread(String name, ReentrantReadWriteLock rrwLock){
        super(name);
        this.rrwLock = rrwLock;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to lock");
        try{
            rrwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " Lock successfully");
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            rrwLock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " unlock successfully");

        }
    }
}

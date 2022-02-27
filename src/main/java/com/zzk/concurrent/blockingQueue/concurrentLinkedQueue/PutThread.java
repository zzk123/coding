package com.zzk.concurrent.blockingQueue.concurrentLinkedQueue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-23 22:55
 */
public class PutThread extends Thread {


    private ConcurrentLinkedQueue<Integer> clq;

    public PutThread(ConcurrentLinkedQueue<Integer> clq){
        this.clq = clq;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try{
                System.out.println("add " + i);
                clq.add(i);
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

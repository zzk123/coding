package com.zzk.concurrent.blockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-23 23:54
 */
public class Producer implements Runnable {

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            queue.put("1");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("3");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

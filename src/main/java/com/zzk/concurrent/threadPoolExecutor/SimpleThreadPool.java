package com.zzk.concurrent.threadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-26 20:58
 */
public class SimpleThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0; i<10; i++){
            Runnable worker = new WorkerThread("" + i);
            executorService.execute(worker);
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){

        }
        System.out.println("finished all Threads");
    }
}

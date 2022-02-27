package com.zzk.concurrent.threadPoolExecutor;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-26 20:55
 */
public class WorkerThread implements Runnable {


    private String command;

    public WorkerThread(String s){
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start.commond = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " end");
    }

    private void processCommand(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

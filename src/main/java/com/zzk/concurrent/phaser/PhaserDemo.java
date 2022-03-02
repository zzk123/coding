package com.zzk.concurrent.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 17:48
 */
public class PhaserDemo {

    private static Random random = new Random(System.currentTimeMillis());

    static class Task extends Thread{

        private Phaser phaser;

        public Task(Phaser phaser){
            this.phaser = phaser;
            this.phaser.register();
        }

        @Override
        public void run() {
            try{
                System.out.println("The thread 【" + getName() + "】 is working");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("The thread 【" + getName() + "】 work finished");
            phaser.arriveAndAwaitAdvance();
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        //创建5个任务
        for(int i=0; i<5; i++){
            new Task(phaser).start();
        }
        //動態註冊
        phaser.register();
        //等待其他线程完成工作
        phaser.arriveAndAwaitAdvance();

        System.out.println("All of worker finished the task");
    }
}

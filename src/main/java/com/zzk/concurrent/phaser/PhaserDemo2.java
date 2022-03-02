package com.zzk.concurrent.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 18:03
 */
public class PhaserDemo2 {

    private static Random random = new Random(System.currentTimeMillis());

    static class Athlete extends Thread {

        private Phaser phaser;

        private int no;

        public Athlete(Phaser phaser, int no){
            this.phaser = phaser;
            this.no = no;
        }

        @Override
        public void run() {
            try{
                System.out.println(no + "：当前处于第：" + phaser.getPhase() + " 阶段");
                System.out.println(no + " : start bicycle");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + " : end bicycle");

                //等待线程
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + "：当前处于第：" + phaser.getPhase() + " 阶段");
                System.out.println(no + " : start long jump");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + " : end long jump");

                //等待线程
                phaser.arriveAndAwaitAdvance();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        for(int i=0; i<6; i++){
            new Athlete(phaser, i).start();
        }
    }
}

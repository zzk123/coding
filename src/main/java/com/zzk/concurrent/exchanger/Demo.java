package com.zzk.concurrent.exchanger;

import java.util.Currency;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 21:55
 */
public class Demo {

    static class Producer extends Thread {

        private Exchanger<Integer> exchanger;

        private static int data = 0;

        Producer(String name, Exchanger<Integer> exchanger){
            super("Producer-" + name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for(int i=0; i<5; i++){
                try{
                    TimeUnit.SECONDS.sleep(1);
                    data = i;
                    System.out.println(getName() + " 交换前 " + data);
                    data = exchanger.exchange(data);
                    System.out.println(getName() + " 交换后 " + data);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consmer extends Thread {

        private Exchanger<Integer> exchanger;

        private static int data = 0;

        Consmer(String name, Exchanger<Integer> exchanger){
            super("Consmer-" + name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while(true){
                data = 0;
                System.out.println(getName() + " 交换前 " + data);
                try{
                    TimeUnit.SECONDS.sleep(1);
                    data = exchanger.exchange(data);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(getName() + " 交换后 " + data);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Producer("", exchanger).start();
        new Consmer("", exchanger).start();
        TimeUnit.SECONDS.sleep(7);
        System.exit(-1);
    }
}

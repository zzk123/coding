package com.zzk.concurrent.aqs;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-20 21:09
 */
public class Consumer {

    private Depot depot;

    public Consumer(Depot depot){
        this.depot = depot;
    }

    public void consume(int no){
        new Thread(new Runnable() {
            @Override
            public void run() {
                depot.consume(no);
            }
        }, no + " consume thread").start();
    }
}

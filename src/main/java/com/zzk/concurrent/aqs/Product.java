package com.zzk.concurrent.aqs;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-20 21:11
 */
public class Product {

    private Depot depot;

    public Product(Depot depot){
        this.depot = depot;
    }

    public void product(int no){
        new Thread(new Runnable() {
            @Override
            public void run() {
                depot.product(no);
            }
        }, no + " product thread").start();
    }
}

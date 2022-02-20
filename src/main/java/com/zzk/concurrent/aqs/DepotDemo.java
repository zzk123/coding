package com.zzk.concurrent.aqs;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-20 21:11
 */
public class DepotDemo {

    public static void main(String[] args) {
        Depot depot = new Depot(500);
        new Product(depot).product(500);
        new Product(depot).product(200);
        new Consumer(depot).consume(500);
        new Consumer(depot).consume(200);
    }
}

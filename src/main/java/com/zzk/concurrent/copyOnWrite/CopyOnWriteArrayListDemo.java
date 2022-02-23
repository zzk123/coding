package com.zzk.concurrent.copyOnWrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-23 22:20
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> cowal = new CopyOnWriteArrayList<>();
        for(int i=0; i<10; i++){
            cowal.add(i);
        }
        PutThread p1 = new PutThread(cowal);
        p1.start();
        Iterator<Integer> iterator = cowal.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + "  ");
        }
        System.out.println();
        try{
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        iterator = cowal.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }
}

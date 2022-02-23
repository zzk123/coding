package com.zzk.concurrent.copyOnWrite;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-23 22:16
 */
public class PutThread extends Thread {

    private CopyOnWriteArrayList<Integer> cowal;

    public PutThread(CopyOnWriteArrayList<Integer> cowal){
        this.cowal = cowal;
    }

    @Override
    public void run() {
        try{
            for(int i=100; i < 110; i++){
                cowal.add(i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

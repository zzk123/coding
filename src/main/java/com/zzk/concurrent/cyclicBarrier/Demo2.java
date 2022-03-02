package com.zzk.concurrent.cyclicBarrier;

import org.checkerframework.checker.units.qual.C;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 17:02
 */
public class Demo2 {

    static class BankWaterService implements Runnable{

        private CyclicBarrier c = new CyclicBarrier(4, this);

        private Executor executors = Executors.newFixedThreadPool(4);

        private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

        private void count(){
            for(int i=0; i<4; i++){
                executors.execute(new Runnable() {
                    @Override
                    public void run() {
                        sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                        try{
                            c.await();
                        }catch (InterruptedException | BrokenBarrierException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        @Override
        public void run() {
            int result = 0;
            for(Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()){
                result += sheet.getValue();
            }
            sheetBankWaterCount.put("result", result);
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        BankWaterService b = new BankWaterService();
        b.count();
    }
}

package com.zzk.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-19 23:20
 */
public class LockSupportDemo {

    static class MyThread extends Thread{
        private Object object;

        public MyThread(Object object){
            this.object = object;
        }

        @Override
        public void run() {
            System.out.println("before unpark");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //获取blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
            //释放许可
            LockSupport.unpark((Thread) object);
            //休眠500ms，保证先执行park中的 setBlocker(t, null)
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //再次获取 blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));

            System.out.println("after unpark");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");
        //获取许可
        LockSupport.park("ParkAndUnParkDemo");
        System.out.println("after park");
    }
}

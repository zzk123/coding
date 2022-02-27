package com.zzk.concurrent.blockingQueue.synchronousQueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: coding
 * @description: 会出现生产者消费者处理速度异常，出现饥渴情况
 * @author: zzk
 * @create: 2022-02-26 18:03
 */
public class SynchronousQueueDemo2 {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        for(int i=0; i<5; i++){
            new Thread(new ThreadProducer(queue)).start();
        }

        for(int i=0; i<5; i++){
            new Thread(new ThreadConsumer(queue)).start();
        }
    }

   static class ThreadProducer implements Runnable{

       SynchronousQueue<String> queue;

       static int cnt = 0;

       ThreadProducer(SynchronousQueue<String> queue){
           this.queue = queue;
       }

        @Override
        public void run() {
            String name = "";
            int val = 0;
            Random random = new Random(System.currentTimeMillis());
            for(int i=0; i<2; i++){
                cnt = (cnt + 1) & 0xFFFFFFFF;
                try{
                    val = random.nextInt()%15;
                    if(val < 5){
                        name = "offer name:" + cnt;
                        queue.offer(name);
                    }else if(val < 10){
                        name = "put name:" + cnt;
                        queue.put(name);
                    }else{
                        name = "offer wait time and name:" + cnt;
                        queue.offer(name, 1000, TimeUnit.SECONDS);
                    }
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadConsumer implements Runnable{

        SynchronousQueue<String> queue;

        static int cnt = 0;

        ThreadConsumer(SynchronousQueue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            String name;
            for(int i=0; i<2; i++){
                try{
                    name = queue.take();
                    System.out.println("take " + name);
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

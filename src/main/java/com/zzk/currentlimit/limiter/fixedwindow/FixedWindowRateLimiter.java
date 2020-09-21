package com.zzk.currentlimit.limiter.fixedwindow;

import com.zzk.currentlimit.limiter.BaseLimiter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: coding
 * @description: 固定窗口算法实现 - 多线程实现
 * @author: zzk
 * @create: 2020-06-24 00:26
 */
public class FixedWindowRateLimiter implements BaseLimiter, Runnable {

    private static final int DEFAULT_ALLOW_VISIT_PRE_SECOND = 5;

    private final int maxVisitPerSecond;

    private AtomicInteger count;

    FixedWindowRateLimiter(){
        this.maxVisitPerSecond = DEFAULT_ALLOW_VISIT_PRE_SECOND;
        this.count = new AtomicInteger();
    }

    FixedWindowRateLimiter(int maxVisitPerSecond) {
        this.maxVisitPerSecond = maxVisitPerSecond;
        this.count = new AtomicInteger();
    }

    /**
     * 当前是否超过最大数量
     * @return
     */
    @Override
    public boolean isOverLimit() {
        return currentQPS() > maxVisitPerSecond;
    }

    /**
     * 当前数量
     * @return
     */
    @Override
    public int currentQPS() {
        return count.get();
    }

    /**
     * 增加数量
     * @return
     */
    @Override
    public boolean visit() {
        count.incrementAndGet();
        return isOverLimit();
    }

    @Override
    public void run() {
        System.out.println(this.currentQPS());
        count.set(0);
    }


    public static void main(String[] args){
        //定时任务，每1s输出一次数量，并清空计数
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter();
        scheduledExecutorService.scheduleAtFixedRate(rateLimiter, 0, 1, TimeUnit.SECONDS);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    rateLimiter.visit();
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    rateLimiter.visit();
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

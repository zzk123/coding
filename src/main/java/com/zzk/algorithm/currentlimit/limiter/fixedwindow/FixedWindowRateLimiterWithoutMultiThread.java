package com.zzk.algorithm.currentlimit.limiter.fixedwindow;

import com.zzk.algorithm.currentlimit.limiter.BaseLimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: coding
 * @description:固定窗口算法实现 - 不使用多线程实现
 * @author: zzk
 * @create: 2020-07-01 22:41
 */
public class FixedWindowRateLimiterWithoutMultiThread implements BaseLimiter {

    private Long lastVisitAt = System.currentTimeMillis();

    private static final int DEFAULT_ALLOWED_VISIT_PER_SECOND = 5;

    private final int maxVisitPerSecond;

    private AtomicInteger count;

    public FixedWindowRateLimiterWithoutMultiThread(int maxVisitPerSecond){
        this.maxVisitPerSecond = maxVisitPerSecond;
        this.count = new AtomicInteger();
    }

    public FixedWindowRateLimiterWithoutMultiThread(){
        this(DEFAULT_ALLOWED_VISIT_PER_SECOND);
    }


    @Override
    public boolean isOverLimit() {
        return count.get() > maxVisitPerSecond;
    }

    @Override
    public int currentQPS() {
        return count.get();
    }

    @Override
    public boolean visit() {
        long now = System.currentTimeMillis();
        synchronized (lastVisitAt){
            if(now - lastVisitAt > 1000){
                lastVisitAt = now;
                System.out.println(currentQPS());
                count.set(1);
            }
        }
        count.incrementAndGet();
        return isOverLimit();
    }

    public static void main(String[] args) {
        BaseLimiter baseLimiter = new FixedWindowRateLimiterWithoutMultiThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    baseLimiter.visit();
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
                while (true){
                    baseLimiter.visit();
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

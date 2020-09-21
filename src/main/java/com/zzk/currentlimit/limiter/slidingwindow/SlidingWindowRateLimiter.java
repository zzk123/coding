package com.zzk.currentlimit.limiter.slidingwindow;

import com.zzk.currentlimit.limiter.BaseLimiter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: coding https://blog.csdn.net/weixin_34273481/article/details/88752687
 * @description: 滑动窗口实现
 * @author: zzk
 * @create: 2020-06-24 00:33
 */
public class SlidingWindowRateLimiter implements BaseLimiter, Runnable {

    private final long maxVisitPerSecond;

    private static final int DEFAULT_ALLOWED_VISIT_PER_SECOND = 5;

    private static final int DEFAULT_BLOCK = 10;

    private final int block;

    private final AtomicLong[] countPerBlock;

    private AtomicLong count;

    private volatile  int index;

    public SlidingWindowRateLimiter(int block, long maxVisitPerSecond){
        this.block = block;
        this.maxVisitPerSecond = maxVisitPerSecond;
        countPerBlock = new AtomicLong[block];
        for(int i=0; i<block; i++){
            countPerBlock[i] = new AtomicLong();
        }
        count = new AtomicLong(0);
    }

    public SlidingWindowRateLimiter(){
        this(DEFAULT_BLOCK, DEFAULT_ALLOWED_VISIT_PER_SECOND);
    }

    @Override
    public boolean isOverLimit() {
        return currentQPS() > maxVisitPerSecond;
    }

    @Override
    public int currentQPS() {
        return Long.valueOf(count.get()).intValue();
    }

    @Override
    public boolean visit() {
        countPerBlock[index].incrementAndGet();
        count.incrementAndGet();
        return isOverLimit();
    }

    @Override
    public void run() {
        System.out.println(isOverLimit());
        System.out.println(currentQPS());
        System.out.println("index:" + index);
        index = (index + 1) % block;
        long val = countPerBlock[index].getAndSet(0);
        count.addAndGet(-val);
    }
}

package com.zzk.algorithm.currentlimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @program: coding
 * @description: 令牌算法
 * @author: zzk
 * @create: 2020-06-23 23:41
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        //创建一个每秒300个令牌的RateLimiter
        RateLimiter rateLimiter = RateLimiter.create(300);
        //取走令牌
        rateLimiter.acquire();
        //尝试取走令牌
        rateLimiter.tryAcquire();
    }
}

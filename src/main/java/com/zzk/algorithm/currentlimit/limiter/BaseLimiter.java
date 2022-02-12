package com.zzk.algorithm.currentlimit.limiter;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2020-06-24 00:25
 */
public interface BaseLimiter {

    boolean isOverLimit();

    int currentQPS();

    boolean visit();

}

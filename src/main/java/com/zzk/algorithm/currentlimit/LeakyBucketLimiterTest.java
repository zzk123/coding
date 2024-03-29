package com.zzk.algorithm.currentlimit;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: coding
 * @description: 漏桶算法
 * @author: zzk
 * @create: 2020-06-24 00:00
 */
@Slf4j
public class LeakyBucketLimiterTest {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    //桶的容量
    public int capacity = 10;

    //当前水量
    public int water = 0;

    //水流速度/s
    public int rate = 4;

    //最后一次加水时间
    public long lastTime = System.currentTimeMillis();

    public void acquire(){
        //0.5s执行一次线程，模拟每0.5秒内不同数量请求
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            long now = System.currentTimeMillis();
            //计算当前水量
            water = Math.max(0, (int)(water - (now - lastTime) * rate /1000));
            int permits = (int) (Math.random() * 8) + 1;
            log.info("请求数：" + permits + "当前桶余量：" + (capacity - water));
            lastTime = now;
            if(capacity - water < permits){
                // 若桶满,则拒绝
                log.info("限流了");
            }else{
                //还有容量
                water += permits;
                log.info("剩余容量=" + (capacity - water));
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        LeakyBucketLimiterTest limiter = new LeakyBucketLimiterTest();
        limiter.acquire();
    }
}

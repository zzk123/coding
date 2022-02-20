package com.zzk.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-13 15:23
 */
public class ThreadLocalExample {

    public static void main(String[] args) throws Exception {
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(()->{
            threadLocal1.set(1);
            threadLocal2.set(1);
            System.out.println("线程1（threadLocal1）：" + threadLocal1.get());
            System.out.println("线程1（threadLocal2）：" + threadLocal2.get());
        });
        Thread thread2 = new Thread(()->{
            threadLocal1.set(2);
            threadLocal2.set(2);
            System.out.println("线程2（threadLocal1）：" + threadLocal1.get());
            System.out.println("线程2（threadLocal2）：" + threadLocal2.get());
        });
        thread1.start();
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(1);
    }
}

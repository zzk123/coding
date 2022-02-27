package com.zzk.concurrent.fork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 14:30
 */
public class Test2 {

    static class Fibonacci extends RecursiveTask<Integer>{
        final int n;
        Fibonacci(int n){
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if(n <= 1){
                return n;
            }
            Fibonacci f1 = new Fibonacci(n -1);
            f1.fork();

            Fibonacci f2 = new Fibonacci(n-2);

            return f2.compute() + f1.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);//最大并发数
        Fibonacci fibonacci = new Fibonacci(20);
        long startTime = System.currentTimeMillis();
        Integer result = forkJoinPool.invoke(fibonacci);
        long end = System.currentTimeMillis();
        System.out.println("Fork/join sum:" + result + " in " + (end - startTime) + " ms");
    }
}

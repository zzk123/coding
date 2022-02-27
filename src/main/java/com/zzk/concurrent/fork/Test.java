package com.zzk.concurrent.fork;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-27 14:12
 */
public class Test {

    static final class SumTask extends RecursiveTask<Integer> {

        final int start;

        final int end;

        SumTask(int start, int end){
            this.start = start;
            this.end = end;
        }


        @Override
        protected Integer compute() {
            //如果计算量小于1000，那么分配一个线程执行if中的代码，并返回结果
            if(end - start < 1000){
                System.out.println(Thread.currentThread().getName() + " 开始执行 :" + start + " - " + end);
                int sum = 0;
                for(int i = start; i <= end; i++){
                    sum += i;
                }
                return sum;
            }
            //如果计算量大大于1000，那么拆分开两个任务
            SumTask task1 = new SumTask(start, (start + end)/2);
            SumTask task2 = new SumTask((start + end)/2 + 1, end);
            //执行任务
            task1.fork();
            task2.fork();
            //获取任务执行的结果
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = new SumTask(1, 10000);
        pool.submit(task);
        System.out.println(task.get());
    }
}

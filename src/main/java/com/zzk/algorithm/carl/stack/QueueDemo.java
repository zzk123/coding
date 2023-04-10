package com.zzk.algorithm.carl.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 单调队列
 *
 * 问题：输入一个数组nums和一个正整数k，有一个大小为k的窗口在nums上从左至右滑动，请输出每次滑动时窗口中的最大值
 */
public class QueueDemo {

    /**
     * 双向链表方法 - 时间复杂度 O(N)
     * @param arr
     * @param w
     * @return
     */
    public static int[] getMaxWindow(int[] arr, int w){
        if(arr == null || w < 1 || arr.length < w){
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            //1、如果一直小于，就会一直删除链表数据，直到跳出
            //2、只要存在大于，就会跳出添加
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst() == i - w){
                qmax.pollFirst();
            }
            if(i >= w-1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 单调队列
     */
    class MonotonicQueue{

        //双链表。支持头部和尾部增删元素
        private LinkedList<Integer> q = new LinkedList<>();

        /**
         * 队尾添加元素，同时把前面比自己小的元素删除
         */
        public void push(int n){
            //将前面小于自己的元素删除
            while(!q.isEmpty() && q.getLast() < n){
                q.pollLast();
            }
            q.addLast(n);
        }

        /**
         * 返回最大元素
         * @return
         */
        public int max(){
            return q.getFirst();
        }

        /**
         * 队头删除元素
         * @param n
         */
        public void pop(int n){
            if(n == q.getFirst()){
                q.pollFirst();
            }
        }
    }

    /**
     *  输入一个数组nums和一个正整数k，有一个大小为 k的窗口在nums上从左到右滑动，请输出每次滑动时窗口中的最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k){
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            if(i < k-1){
                //先填满窗口的前 k-1
                window.push(nums[i]);
            }else{
                //窗口向前滑动加入新数字
                window.push(nums[i]);
                //记录当前窗口的最大值
                res.add(window.max());
                //移除旧数据
                window.pop(nums[i - k + 1]);
            }
        }
        int[] arr = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            arr[i] = res.get(i);
        }
        return arr;
    }
}

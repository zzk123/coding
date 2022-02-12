package com.zzk.algorithm.coding.stackQueue;

import java.util.LinkedList;

/**
 * @program: coding
 * @description: 9.最大值减去最小值小于或等于num的子数组数量
 * @author: zzk
 * @create: 2020-09-14 22:26
 */
public class GetNum {

    /**
     *
     * @param arr
     * @param num
     * @return
     */
    public int getNum(int[] arr, int num){
        if(arr ==null || arr.length == 0){
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int i = 0;
        int j = 0;
        int res = 0;
        while(i < arr.length){
            while(j < arr.length){
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]){
                    qmin.pollLast();
                }
                qmin.addLast(j);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]){
                    qmax.pollLast();
                }
                qmax.addLast(j);
                if(arr[qmax.getFirst()] - arr[qmin.getFirst()] > num){
                    break;
                }
                j++;
            }
            if(qmin.peekFirst() == i){
                qmin.pollFirst();
            }
            if(qmax.peekFirst() == i){
                qmax.pollFirst();
            }
            res += j-i;
            i++;
        }
        return 0;
    }
}

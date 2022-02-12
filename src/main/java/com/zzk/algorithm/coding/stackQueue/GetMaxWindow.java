package com.zzk.algorithm.coding.stackQueue;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @program: coding
 * @description: 6. 生成窗口最大值数组
 * @author: zzk
 * @create: 2020-06-04 00:11
 */
public class GetMaxWindow {

    public static int[] getMaxWindow(int [] arr, int w){
        if( arr == null || w < 1 || arr.length < w){
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int [] res = new int[arr.length - w + 1];
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            //队列循环去掉最小值
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            //队尾添加i
            qmax.addLast(i);
            //判断是否队头过期
            if(qmax.peekFirst() == i-w){
                qmax.pollFirst();
            }
            //判断窗口长度
            if(i >= w-1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        int[] arr1 = {1,2,3,4,5,6,7,8};
        int[] arr2 = {8,7,6,5,4,3,2,1};
        Arrays.stream(getMaxWindow(arr2, 3)).forEach(System.out::println);
    }
}

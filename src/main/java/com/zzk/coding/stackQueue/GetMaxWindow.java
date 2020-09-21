package com.zzk.coding.stackQueue;

import java.util.LinkedList;

/**
 * @program: coding
 * @description: 6. 生成窗口最大值数组
 * @author: zzk
 * @create: 2020-06-04 00:11
 */
public class GetMaxWindow {

    public int[] getMaxWindow(int [] arr, int w){
        if( arr == null || w < 1 || arr.length < w){
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int [] res = new int[arr.length - w + 1];
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
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
}

package com.zzk.algorithm.coding.array;

/**
 * @ClassName MaxSum
 * @Description 16.子数组的最大累加和问题
 * @Author zzk
 * @Date 2021/1/25 22:01
 **/
public class MaxSum {

    public int maxsum(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int max = Integer.MAX_VALUE;
        int cur = 0;
        for(int i = 0; i != arr.length;  i++){
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }
}

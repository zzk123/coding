package com.zzk.algorithm.coding.other;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName UnformedSum
 * @Description 14.正数数组的最小不可组成和
 * @Author zzk
 * @Date 2021/3/10 23:41
 **/
public class UnformedSum {

    /**
     * 暴力递归法
     * 时间复杂度为O(2^N)，空间复杂度为O(N)
     * @param arr
     * @return
     */
    public int unformedSum1(int[] arr){
        if(arr == null || arr.length == 0){
            return 1;
        }
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i != arr.length; i++){
            min = Math.min(min, arr[i]);
        }
        for(int i = min + 1; i != Integer.MIN_VALUE; i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return 0;
    }

    public void process(int[] arr, int i, int sum, HashSet<Integer> set){
        if(i == arr.length){
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum, set);  //包含当前数arr[i]的情况
        process(arr, i + 1, sum + arr[i], set); // 不包含当前数arr[i]的情况
    }


    /**
     * 动态规划
     * 时间复杂度为O(N * sum)，空间复杂度为O(N)
     * @param arr
     * @return
     */
    public int unformedSum2(int[] arr){
        if(arr == null || arr.length == 0){
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i != arr.length; i++){
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for(int i = 0; i != arr.length; i++){
            for(int j = sum; j >= arr[i]; j--){
                dp[j] = dp[j - arr[i]] ? true : dp[j];
            }
        }
        for(int i = min;  i != dp.length; i++){
            if(!dp[i]){
                return i;
            }
        }
        return sum + 1;
    }

    /**
     * 时间复杂度为O(NlogN) , 额外空间复杂度为O(1)
     * @param arr
     * @return
     */
    public int unformedSum3(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        Arrays.sort(arr); // 把arr排序
        int range = 0;
        for(int i = 0; i != arr.length; i++){
            if(arr[i] > range + 1){
                return range + 1;
            }else{
                range += arr[i];
            }
        }
        return range + 1;
    }
}

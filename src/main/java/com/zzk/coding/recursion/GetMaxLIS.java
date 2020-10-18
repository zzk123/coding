package com.zzk.coding.recursion;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: coding
 * @description: 5.最长递增子序列
 * @author: zzk
 * @create: 2020-10-16 22:56
 */
public class GetMaxLIS {

    public static  final Logger logger = LoggerFactory.getLogger(GetMaxLIS.class);
    /**
     * dp记录以arr[i]为结尾的情况下，arr[0..i]中的最大递增子序列长度
     * @param arr
     * @return
     */
    public int[] getdp1(int[] arr){
        int[] dp = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    /**
     * 根据dp数据找出最长递增子序列的过程
     * 从某一个位置开始逆序还原出决策路径
     * @param arr
     * @param dp
     * @return
     */
    public static int[] generateLIS(int[] arr, int[] dp){
        int len = 0;
        int index = 0;
        for(int i = 0; i < dp.length; i++){
            if(dp[i] > len){
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for(int i = index; i >= 0; i--){
            if(arr[i] < arr[index] && dp[i] == dp[index] - 1){
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }

    /**
     * 时间复杂度为 O(N^2)
     * 主方法
     * @param arr
     * @return
     */
    public int[] lis1(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        int[] dp = getdp1(arr);
        return generateLIS(arr, dp);
    }

    public static int[] getdp2(int[] arr){
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for(int i = 1; i < arr.length; i++){
            l = 0;
            r = right;
            while(l <= r){
                m = (l + r) / 2;
                if(arr[i] > ends[m]){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = 1 + l;
            logger.info("ends:{}", new Gson().toJson(ends));
            //logger.info("dp:{}", new Gson().toJson(dp));
        }
        return dp;
    }

    /**
     * 时间复杂度为 O(NlogN)
     * @param arr
     * @return
     */
    public static int[] lis2(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        int[] dp = getdp2(arr);
        logger.info(new Gson().toJson(dp));
        return generateLIS(arr, dp);
    }

    public static void main(String[] args) {
        int[] arr = {2,1,5,3,6,4,8,9,7};
        logger.info(new Gson().toJson(lis2(arr)));
    }
}

package com.zzk.algorithm.labuladong.dp;

import java.util.Arrays;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-06 22:19
 */
public class DPDemo {


    /**
     * 二分搜索解法
     * 最长递增子序列
     * 时间复杂度为O(nlogn)
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums){
        int[] top = new int[nums.length];
        //牌堆数初始化为0
        int piles = 0;
        for(int i=0; i<nums.length; i++){
            //要处理的扑克数
            int poker = nums[i];
            //搜索左侧边界的二分搜索
            int left = 0, right = piles;
            while(left < right){
                int mid = (left + right)/2;
                if(top[mid] > poker){
                    right = mid;
                }else if(top[mid] < poker){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }
            //没有找到合适牌堆，新建一堆
            if(left == piles){
                piles++;
            }
            //把这张牌放到牌堆顶
            top[left] = poker;
        }
        //牌堆数就是LIS长度
        return piles;
    }
    /**
     * 时间复杂度 O(n^2)
     * 动态规划 ： 最长递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums){
        int[] dp = new int[nums.length];
        //初始化为1
        Arrays.fill(dp, 1);
        for(int i=0; i<nums.length; i++){
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int res = 0;
        //重新遍历一遍数组，找到最长的递增子序列长度
        for(int i=0; i<dp.length; i++){
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

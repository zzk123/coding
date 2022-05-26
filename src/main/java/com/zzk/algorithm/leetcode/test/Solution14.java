package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description: https://leetcode.cn/problems/max-consecutive-ones-iii/
 * @author: zzk
 * @create: 2022-05-15 11:02
 */
public class Solution14 {

    /**
     * 滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int res = 0;
        int maxCount = 0;
        int len = nums.length;
        while(right < len){
            if(nums[right] == 0){
                maxCount++;
            }
            while(maxCount > k){
                if(nums[left++] == 0){
                    maxCount--;
                }
            }
            res = Math.max(res, right-left);
            right++;
        }
        return res+1;
    }

    public static void main(String[] args) {
       int[] nums = new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        longestOnes(nums, k);
    }
}

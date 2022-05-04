package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-04-14 22:17
 */
public class Solution6 {

    public static int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        return Math.max(rob(nums, 0, nums.length-1), rob(nums, 1, nums.length));
    }

    public static int rob(int[] nums, int start, int end){
        int x = 0, y = 0, z = 0;
        for(int i=start; i<end; i++){
            y = z;
            z = Math.max(y, x+nums[i]);
            x = y;
        }
        return z;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        rob(nums);
    }
}

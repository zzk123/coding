package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description: https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
 * @author: zzk
 * @create: 2022-05-08 14:47
 */
public class Solution11 {

    public static void moveZero(int[] nums){
        int length;
        if(nums == null || (length = nums.length) == 0){
            return;
        }
        int j = 0;
        for(int i=0; i<length; i++){
            if(nums[i] != 0){
                if(i > j){
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}

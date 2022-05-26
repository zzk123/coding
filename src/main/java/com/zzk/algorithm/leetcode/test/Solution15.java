package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description: 前缀和 ： https://leetcode.cn/problems/unique-substrings-in-wraparound-string/solution/xi-fa-dai-ni-xue-suan-fa-yi-ci-gao-ding-qian-zhui-/
 * @author: zzk
 * @create: 2022-05-15 15:00
 */
public class Solution15 {

    /**
     * 求一个数组值相差为1连续子数组的总个数
     * @param nums
     * @return
     */
    public static int countSubArray(int[] nums){
        int ans = 0;
        int pre = 0;
        for(int i=1; i<nums.length; i++){
            if(nums[i] - nums[i-1] == 1){
                pre += 1;
            }else{
                pre = 0;
            }
            ans += pre;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(countSubArray(nums));
    }
}

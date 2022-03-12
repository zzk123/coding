package com.zzk.algorithm.labuladong.dp;

import java.util.Arrays;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-12 22:19
 */
public class DPDemo8 {

    /***
     * 线性排序
     *  街上有一排房屋，用一个包含非负整数的数组nums表示，每个元素nums[i]代表i间房子中的现金树，
     *  可以从房子取钱，但是相邻的房子的钱不能被同时取出，在满足条件的情况能最多取出多少钱？
     *  比如：输入 nums=[2,1,7,9,3,1]，可以返回 nums[0]，nums[3]，nums[5]
     */

    /**
     * 解法4， 优化解法3，空间复杂度降为 O(1)
     * @param nums
     * @return
     */
    public int rob4(int[] nums){
        int n = nums.length;
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for(int i=n-1; i>=0; i--){
            dp_i = Math.max(dp_i_1,  nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
    /**
     * 解法3：自底向上的解法
     * @param nums
     * @return
     */
    public int rob3(int[] nums){
        int n = nums.length;
        int[] dp = new int[n+2];
        for(int i=n-1; i>=0; i--){
            dp[i] = Math.max(dp[i+1], nums[i] + dp[i+2]);
        }
        return dp[0];
    }

    /**
     * 解法2，使用备忘录优化
     */
    int[] memo;
    int rob2(int[] nums){
        //初始化备忘录
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp2(nums, 0);
    }

    public int dp2(int[]  nums, int start){
        if(start >= nums.length){
            return 0;
        }
        if(memo[start] != -1){
            return memo[start];
        }
        int res = Math.max(dp2(nums, start+1),
                nums[start]+dp2(nums, start+1));
        memo[start] = res;
        return res;
    }
    /**
     * 解法1
     * @param nums
     * @return
     */
    public int rob(int[] nums){
        return dp(nums, 0);
    }

    public int dp(int[] nums, int start){
        if(start >= nums.length){
            return 0;
        }

        int res = Math.max(
                //不取钱，下间房取
                dp(nums, start+1),
                //取钱，下下间房取
                nums[start] + dp(nums, start+1)
        );
        return res;
    }
}

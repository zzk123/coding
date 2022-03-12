package com.zzk.algorithm.labuladong.dp;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-12 22:57
 */
public class DPDemo9 {

    /***
     * 线性排序
     *  街上有一排房屋，围成一圈，用一个包含非负整数的数组nums表示，每个元素nums[i]代表i间房子中的现金树，
     *  可以从房子取钱，但是相邻的房子的钱不能被同时取出，在满足条件的情况能最多取出多少钱？
     *
     *  不同情况：
     *  1.第一间房子和最后一间房子都不取钱
     *  2.只取第一间房子的钱，不取最后一间房子的钱
     *  3.只取最后一间房子的钱，不取第一间房子的钱
     */

    /**
     * 输入的nums数组视为一个环形数组
     * @param nums
     * @return
     */
    public int rob(int[] nums){
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }
        return Math.max(robRang(nums, 0, n-2),
                robRang(nums, 1, n-1));
    }

    /**
     * 仅计算闭区间 [start, end]的最优结果
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int robRang(int[] nums, int start, int end){
        int n = nums.length;
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for(int i=end; i>=start; i--){
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}

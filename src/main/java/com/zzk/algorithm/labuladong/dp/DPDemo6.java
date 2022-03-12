package com.zzk.algorithm.labuladong.dp;

/**
 * @program: coding
 * @description: 0-1 背包问题
 * @author: zzk
 * @create: 2022-03-10 00:08
 */
public class DPDemo6 {

    /**
     * 输入一个只包含正整数的非空数组nums，判断这个数组是否可以被分割成两个子集，使得两个子集的元素和相等
     * 可以转为：
     * 给一个重量为 sum/2 的背包和N个物品，每个物品的重量为 nums[i]，是否存在一种装法，能够把背包装满？
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums){
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 != 0){
            return false;
        }
        int n = nums.length;
        sum = sum / 2;
        boolean[][]dp = new boolean[n+1][sum+1];
        for(int i=0; i<=n; i++){
            dp[i][0] = true;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=sum; j++){
                if(j-nums[i-1] < 0){
                    //容量不足，不能装入第i个物品
                    dp[i][j] = dp[i-1][j];
                }else{
                    //装入或者不装入背包l
                    //看看是否存在一种情况可以恰好装满
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * 0-1背包问题
     * 重量为W的背包，和数量为N的物品，第i个物品的重量为wt[i]，价值为val[i]
     * 请问最多可以装多少价值
     * 动态规划
     * @param W
     * @param wt
     * @param val
     * @return
     */
    public static int knapsack(int W, int[] wt, int[] val){
        int n = wt.length;
        int[][] dp = new int[n+1][W+1];
        for(int j=0; j< n+1; j++){
            dp[j][0] = 0;
        }
        for(int j=0; j<W+1; j++){
            dp[0][j] = 0;
        }
        //循环多个物件
        for(int i=1; i<= n; i++){
            //循环重量
            for(int j=0; j <= W; j++){
                //对比容量是否能装下，装得下就择优
                if(j >= wt[i-1]){
                    dp[i][j] = Math.max(
                            dp[i-1][j - wt[i-1]] + val[i-1],
                            dp[i-1][j]
                    );
                }
                //装不下就不装
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[wt.length][W];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }

    public static void main1(String[] args) {
        int W = 10;
        int[] wt = {2, 2, 6, 5, 4};
        int[] val = {6, 3, 5, 4, 6};
        System.out.println(knapsack(W, wt, val));
    }
}

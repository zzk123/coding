package com.zzk.algorithm.labuladong.dp;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-12 21:36
 */
public class DPDemo7 {


    /**
     * 将二维数组压缩为一维，时间复杂度为 O(N * amount)，空间复杂度为 O(amount)
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins){
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i=0; i<n; i++){
            for(int j=1; j<=amount; j++){
                if(j-coins[i] >= 0){
                    dp[j] = dp[j] + dp[j-coins[i]];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 完全背包问题
     * 给定不同面额的硬币 coins和一个总金额 amount，写一个方法来计算可以凑成总金额的硬币组合数，假设每一种面额的硬币由无限个
     * 可以转化为
     * 有一个背包，最大容量为 amount，有一系列物品 coins，每个物品的重量为 coins[i]，每个物品的数量无限，请问有多少种方法能够把背包恰好装满
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins){
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                if(j-coins[i-1] >= 0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }
}

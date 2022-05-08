package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-04-13 22:35
 */
public class Solution05 {

    public static int change(int amount, int[] coins){
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int j = 0; j < coins.length; j++){
            for(int i = 0; i <= amount; i++){
                if(i >= coins[j]){
                    dp[i] += dp[i-coins[j]];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};
        change(amount, coins);
    }
}

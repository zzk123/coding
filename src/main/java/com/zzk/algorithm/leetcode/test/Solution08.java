package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-04-20 23:31
 */
public class Solution08 {

    /**
     *
     * @param n
     * @return
     */
    public static int integerBreak(int n){
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i=3; i<=n; i++){
            for(int j=1; j<=i-j; j++){
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
            }
        }
        return dp[n];
    }
}

package com.zzk.algorithm.labuladong.dp;

import com.google.gson.Gson;

/**
 * @program: coding
 * @description: 最长回文子序列
 * @author: zzk
 * @create: 2022-03-07 22:29
 */
public class DPDemo3 {


    /**
     * 优化longestPalindromeSubseq，将二维数组转为一维数组
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq2(String s){
        int n = s.length();
        int[] dp = new int[n];
        for(int i=0; i<n; i++){
            dp[i] = 1;
        }
        for(int i=n-2; i>=0; i--){
            int pre = 0;
            for(int j=i+1; j<n; j++){
                int tmp = dp[j];
                //状态转移方程
                if(s.charAt(i) == s.charAt(j)){
                    dp[j] = pre + 2;
                }else{
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                pre = tmp;
            }
        }
        System.out.println(new Gson().toJson(dp));
        return dp[n-1];
    }
    /**
     * 查找最长回文子序列
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++){
            dp[i][i] = 1;
        }
        for(int i=n-2; i>=0; i--){
            for(int j=i+1; j<n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(new Gson().toJson(dp));
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        String s = "aecda";
        System.out.println(longestPalindromeSubseq(s));
    }
}

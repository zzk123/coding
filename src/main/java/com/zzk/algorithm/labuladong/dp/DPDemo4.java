package com.zzk.algorithm.labuladong.dp;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-07 23:54
 */
public class DPDemo4 {


    /**
     * 优化 minInsertions方法
     * @param s
     * @return
     */
    public static int minInsertions2(String s){
        int n = s.length();
        int[] dp = new int[n];
        int tmp = 0;
        for(int i = n-2; i>=0; i--){
            int pre = 0;
            for(int j=i+1; j<n; j++){
                tmp = dp[j];
                if(s.charAt(i) == s.charAt(j)){
                    dp[j] = pre;
                }else{
                    dp[j] = Math.min(dp[j], dp[j-1]) + 1;
                }
                pre = tmp;
            }
        }
        return dp[n-1];
    }

    /**
     * 最小插入次数构造回文串
     * @param s
     * @return
     */
    public static int minInsertions(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++){
            dp[i][i] = 1;
        }
        for(int i=n-2; i>=0; i--){
            for(int j=i+1; j<n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[0][n-1];
    }
}

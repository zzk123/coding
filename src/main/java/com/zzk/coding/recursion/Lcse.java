package com.zzk.coding.recursion;

import com.google.gson.Gson;

/**
 * @program: coding
 * @description: 7.最长公共子序列问题
 * @author: zzk
 * @create: 2020-10-21 22:22
 */
public class Lcse {

    public static int[][] getdp(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for(int i = 1; i < str1.length; i++){
            dp[i][0] = Math.max(dp[i-1][0], str1[i] == str2[0] ? 1: 0);
        }
        System.out.println(new Gson().toJson(dp));
        for(int j = 1; j < str2.length; j++){
            dp[0][j] = Math.max(dp[0][j-1], str1[0] == str2[j] ? 1 : 0);
        }
        System.out.println(new Gson().toJson(dp));
        for(int i = 1; i < str1.length; i++){
            for(int j = 1; j < str2.length; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if(str1[i] == str2[j]){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
                System.out.println(new Gson().toJson(dp));
            }
        }

        return dp;
    }


    /**
     * 计算dp矩阵中的某个位置时间复杂度为O(1)，动态规划表dp的大小为M*N，
     * 计算dp矩阵的时间复杂度为O(M*N),通过dp得到最长公共子序列的过程为O(M+N)
     * 所以总的时间复杂度为O(M*N)，额外空间复杂度为O(M*N)
     * @param str1
     * @param str2
     * @return
     */
    public static String lcse(String str1, String str2){
        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while(index >= 0){
            if(n > 0 && dp[m][n] == dp[m][n-1]){
                n--;
            }else if(m > 0 && dp[m][n] == dp[m-1][n]){
                m--;
            }else{
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String str1 = "1A2C";
        String str2 = "B1D2";
        System.out.println(new Gson().toJson(getdp(str1.toCharArray(), str2.toCharArray())));
    }
}

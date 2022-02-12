package com.zzk.algorithm.coding.recursion;

import com.google.gson.Gson;

/**
 * @program: coding
 * @description: 8.最长公共子串问题
 * @author: zzk
 * @create: 2020-10-25 17:25
 */
public class Lcst {

    /**
     * 生成M*n的矩阵
     * @param str1
     * @param str2
     * @return
     */
    public static int[][] getdp(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        for(int i = 0; i < str1.length; i++){
            if(str1[i] == str2[0]){
                dp[i][0] = 1;
            }
        }
        for(int j = 1; j < str2.length; j++){
            if(str1[0] == str2[j]){
                dp[0][j] = 1;
            }
        }
        for(int i = 1; i < str1.length; i++){
            for(int j = 1; j < str2.length; j++){
                if(str1[i] == str2[j]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }
        return dp;
    }

    /**
     * 经典的动态规划 时间复杂度为O(M*N)，额外空间复杂度为O(M*N)
     * @param str1
     * @param str2
     * @return
     */
    public static String lcst1(String str1,  String str2){
        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int end = 0;
        int max = 0;
        for(int i = 0; i < chs1.length; i++){
            for(int j = 0; j < chs2.length; j++){
                if(dp[i][j] > max){
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }


    /**
     *  优化后经典的动态规划 时间复杂度为O(M*N)，额外空间复杂度为O(1)
     * @param str1
     * @param str2
     * @return
     */
    public static String lcst2(String str1, String str2){
        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = 0;
        int col = chs2.length - 1;
        int max = 0;
        int end = 0;
        while(row < chs1.length){
            int i = row;
            int j = col;
            int len = 0;
            while(i < chs1.length && j < chs2.length){
                if(chs1[i] != chs2[j]){
                    len = 0;
                }else{
                    len++;
                }
                if(len > max){
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            if(col > 0){
                col--;
            }else{
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static void main(String[] args) {
        String str1 = "AABBCC";
        String str2 = "CCDDAABB";
        System.out.println(new Gson().toJson(getdp(str1.toCharArray(), str2.toCharArray())));
    }
}

package com.zzk.algorithm.coding.str;

/**
 * @ClassName MinCut
 * @Description 21.回文最少分割数
 * @Author zzk
 * @Date 2020/11/25 23:51
 **/
public class MinCut {

    /**
     * 回文字符判断：
     * 1.定义一个二维数组boolean[][]p，如果p[i][j]的值为true，说明字符串str[i...j]是回文串，否则不是
     * 2.p[i][j]如果为true，一定是以下三种情况
     * - str[i..j]由1个字符串组成
     * - str[i..j]由2个字符组成且2个字符组成
     * - str[i+1..j-2]是回文串，即p[i+1][j-1]为true，且str[i] = str[j]，即str[i..j]上首尾两个字符相等
     * @param str
     * @return
     */
    public int minCut(String str){
        if(str == "" || str == null){
            return 0;
        }
        char[] chas = str.toCharArray();
        int len = chas.length;
        int[] dp = new int[len + 1];
        dp[len] = -1;
        boolean[][] p = new boolean[len][len];
        for(int i = len - 1; i >= 0; i--){
            for(int j = i; j < len; j++){
                if(chas[i] == chas[j] && (j - i < 2 || p[i+1][j-1])){
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j+1] + 1);
                }
            }
        }
        return dp[0];
    }
}

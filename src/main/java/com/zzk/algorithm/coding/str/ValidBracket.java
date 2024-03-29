package com.zzk.algorithm.coding.str;

/**
 * @ClassName ValidBracket
 * @Description 14.括号字符串的有效性和最长有效长度
 * @Author zzk
 * @Date 2020/11/18 22:28
 **/
public class ValidBracket {

    public boolean isValid(String str){
        if(str == null || str.equals("")){
            return false;
        }
        char[] chas = str.toCharArray();
        int status = 0;
        for(int i = 0; i < chas.length; i++){
            if(chas[i] != ')' && chas[i] != '('){
                return false;
            }
            if(chas[i] == ')' && --status < 0){
                return false;
            }
            if(chas[i] == '('){
                status++;
            }
        }
        return status == 0;
    }

    /**
     * 动态规划
     * 时间复杂度为O(N)，额外空间复杂度为O(N)
     * @param str
     * @return
     */
    public int maxLength(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] dp = new int[chas.length];
        int pre = 0;
        int res = 0;
        for(int i = 1; i < chas.length; i++){
            if(chas[i] == ')'){
                pre = i - dp[i-1] - 1;
                if(pre >= 0 && chas[pre] == '('){
                    dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre-1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

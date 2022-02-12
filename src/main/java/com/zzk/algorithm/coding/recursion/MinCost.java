package com.zzk.algorithm.coding.recursion;

/**
 * @program: coding
 * @description: 9.最小编辑代价
 * @author: zzk
 * @create: 2020-10-27 22:25
 */
public class MinCost {

    /**
     * 经典的动态规划方法
     * 时间复杂度为O(M*N)，空间复杂度为O(M*N)
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public int minCost1(String str1, String str2, int ic, int dc, int rc){
        if(str1 == null || str2 == null){
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = chs1.length + 1;
        int col = chs2.length + 1;
        int[][] dp = new int[row][col];
        for(int i = 1; i < row; i++){
            dp[i][0] = dc * i;
        }
        for(int j = 1; j < col; j++){
            dp[0][j] = ic * j;
        }
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(chs1[i-1] == chs2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i-1][j-1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + dc);
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 结合空间压缩的动态规划法
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public int minCost2(String str1, String str2, int ic, int dc, int rc){
        if(str1 == null || str2 == null){
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        //str2较长就交换ic和dc的值
        if(chs1.length < chs2.length){
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for(int i = 1; i <= shorts.length; i++){
            dp[i] = ic * i;
        }
        for(int i = 1; i <= longs.length; i++){
            //pre表示左上角的值
            int pre = dp[0];
            dp[0] = dc  * i;
            for(int j = 1; j <= shorts.length; j++){
                //dp[j]没更新前先保持下来
                int tmp = dp[j];
                if(longs[i-1] == shorts[j-1]){
                    dp[j] = pre;
                }else{
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j-1]+ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                //pre变成dp[j]没更新前的值
                pre = tmp;
            }
        }
        return dp[shorts.length];
    }
}

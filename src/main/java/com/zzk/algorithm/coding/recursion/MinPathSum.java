package com.zzk.algorithm.coding.recursion;

/**
 * @program: coding
 * @description: 2.矩阵的最小路径和
 * @author: zzk
 * @create: 2020-10-10 23:37
 */
public class MinPathSum {

    /**
     * 矩阵有M*N个位置，每个位置都计算一次从(0, 0)位置到达自己的最小路径和，计算的时候只是比较上边位置的最小路径与左边
     * 位置的最小路径和哪个更小，所以时间复杂度为O(M*N)，dp矩阵的大小为M * N，所以额外的空间复杂度为O(M * N)
     * @param m
     * @return
     */
    public int minPathSum1(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        //第一列值
        for(int i = 1; i < row; i++){
            dp[i][0] = dp[i-1][0] + m[i][0];
        }
        //第一行的值
        for(int j = 1; j < col; j++){
            dp[0][j] = dp[0][j-1] + m[0][j];
        }
        //比较上边位置的最小路径与左边位置的最小路径
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }


    /**
     * 空间压缩后的动态规划
     * 时间复杂度为O(M * N)，额外空间复杂度为O(min{M , N})
     * @param m
     * @return
     */
    public int minPathSum2(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return 0;
        }
        int more = Math.max(m.length, m[0].length); // 行数和列数较大的为more
        int less = Math.min(m.length, m[0].length); // 行数和列数较小的为less
        boolean rowmore = more == m.length; // 行数是不是大于等于列数
        int[] arr = new int[less]; // 辅助数组的长度仅为行数与列数中的最小值
        arr[0] = m[0][0];
        for(int i = 1; i < less; i++){
            arr[i] = arr[i-1] + (rowmore ? m[0][i] : m[i][0]);
        }
        for(int i = 1; i < more; i++){
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            for(int j = 1; j < less; j++){
                arr[j] = Math.min(arr[j -1], arr[j]) + (rowmore ? m[i][j] :  m[j][i]);
            }
        }
        return arr[less - 1];
    }
}

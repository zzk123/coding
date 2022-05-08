package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-04-09 15:23
 */
public class Solution04 {

    public static int maxCoins(int[] nums){
        int n = nums.length;
        //创建一个辅助数组，并在首尾添加1，方便处理边界情况
        int[] temp = new int[n+2];
        temp[0] = 1;
        temp[n+1] = 1;
        for(int i=0; i<n; i++){
            temp[i+1] = nums[i];
        }
        int[][] dp = new int[n+2][n+2];
        //len表示开区间长度
        for(int len=3; len<=n+2; len++){
            //i表示开区间左端点
            for(int i=0; i<=n+2-len; i++){
                int res = 0;
                //k为开区间的索引
                for(int k=i+1; k<i+len-1; k++){
                    int left = dp[i][k];
                    int right = dp[k][i+len-1];
                    res = Math.max(res, left+temp[i]*temp[k]*temp[i+len-1] + right);
                }
                dp[i][i+len-1] = res;
            }
        }
        return dp[0][n+1];
    }

    public static int maxCoins2(int[] nums) {
        int n = nums.length;
        int[] points = new int[n+2];
        points[0] = points[n+1]=1;
        for(int i=1; i<=n; i++){
            points[i] = nums[i-1];
        }
        int[][] dp = new int[n+2][n+2];
        for(int i=n; i>=0; i--){
            for(int j=i+1; j<n+2; j++){
                for(int k=i+1; k<j; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[i][k]+dp[k][j]+points[i]*points[j]*points[k]);
                }
            }
        }
        return dp[0][n+1];
    }


    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        System.out.println(maxCoins2(nums));
    }
}

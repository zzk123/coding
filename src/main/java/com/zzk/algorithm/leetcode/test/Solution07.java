package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-04-16 11:55
 */
public class Solution07 {

    /**
     * 贪心算法，查找最左和最右，相减
     * @param prices
     * @return
     */
    public int maxProfit(int prices[]) {
        int low = Integer.MIN_VALUE;
        int result = 0;
        for(int i=0; i<prices.length; i++){
            low = Math.min(low, prices[i]);
            result = Math.max(result, prices[i] - low);
        }
        return result;
    }

    public int maxProfit2(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int length = prices.length;
        int[][] dp = new int[length][2];
        int result = 0;
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i=1; i<length; i++){
            dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i-1][0]+prices[i], dp[i-1][1]);
        }
        return dp[length-1][1];
    }

    public int maxProfit3(int[] prices){
        int[] dp = new int[2];
        //记录一次交易，一次交易有买入卖出两种状态
        //0代表持有，1代表卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        //从i=1开始遍历，一共有 prices.length 天
        for(int i=1; i<=prices.length; i++){
            //前天持有或者当天买入
            dp[0] = Math.max(dp[0], -prices[i-1]);
            //前天卖出或者当天卖出（需要前天持有）
            dp[1] = Math.max(dp[1], dp[0] + prices[i-1]);
        }
        return dp[1];
    }
}

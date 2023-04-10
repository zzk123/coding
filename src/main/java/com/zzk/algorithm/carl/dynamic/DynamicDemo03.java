package com.zzk.algorithm.carl.dynamic;

/**
 * 买卖股票最佳时机
 * 动态规划
 */
public class DynamicDemo03 {
    /**
     * 1、买卖股票的最佳时机
     * 给定一个数组 `prices` ，它的第 `i` 个元素 `prices[i]` 表示一支给定股票第 `i` 天的价格。
     * 你只能选择 **某一天** 买入这只股票，并选择在 **未来的某一个不同的日子** 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 `0`
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     */
    //暴力法
    //时间：O(n^2)
    //空间：O(1)
    public int maxProfit(int[] prices){
        int maxProfit = 0;
        for(int i=0; i<prices.length-1; i++){
            for(int j=i+1; j<prices.length; j++){
                int profit = prices[j] - prices[i];
                if(profit > maxProfit){
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    //贪心
    //因为股票就买卖一次，贪心算法就是取最左最小值，取最右最大值，那么得到的差值就是最大利润
    //时间：O(N)
    //空间：O(1)
    public int maxProfit2(int[] prices){
        int low = Integer.MIN_VALUE;
        int result = 0;
        for(int i=0; i<prices.length; i++){
            //取最左最小价格
            low = Math.min(low, prices[i]);
            //直接取最大区间利润
            result = Math.max(result, prices[i]-low);
        }
        return result;
    }

    //一次遍历
    //时间：O(N)
    //空间：O(1)
    public int maxProfit3(int[] prices){
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i=0; i<prices.length; i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }else if(prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    //动态规划
    /**
     * 定义：
     *  - dp[i] [0] 表示第i天持有股票所得最多现金
     *  - dp[i] [1] 表示第i天不持有股票所得最多现金
     *  - 如果第i天持有股票即 dp[i] [0]，那么有两个状态
     *   - 第 i-1 天持有股票，第 i 天 等于 第 i-1天 即 dp[i-1] [0]
     *   - 第 i 天买入股票，所得现金即 -price[i]
     *   - 选择最大的状态，所以 dp[i] [0] = Math.max(dp[i-1] [0]，-prices[i])
     * - 如果 第 i-1 天不持有股票 dp[i] [1]，有两个状态
     *   - 第 i-1 天不持有股票，保持现状，即 dp[i-1] [1]
     *   - 第 i 天卖出股票，第i-1天持有加上第 i 天卖出的现金，即 prices[i] + dp[i-1] [0]
     *   - 选择最大的状态，即 dp[i] [1] = Math.max(dp [i-1] [0]+prices[i], dp[i-1] [1]);
     */
    public int maxProfit4(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        //dp[i] [0] 表示第i天持有股票所得最多现金
        //dp[i] [1] 表示第i天不持有股票所得最多现金
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i=1; i<length; i++){
            dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i][1], dp[i-1][0] + prices[i]);
        }
        return dp[length-1][1];
    }


    public int maxProfit5(int[] prices){
        int[] dp = new int[2];
        //记录一次交易，一次交易有买入卖出两种状态
        //0代表持有，1代表卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        //从i=1开始遍历，一共有 prices.length 天
        for(int i=1; i<=prices.length; i++){
            //前天持有或者当天买入
            dp[0] = Math.max(dp[0], -prices[i]);
            //前天卖出或者当天卖出（需要前天持有）
            dp[1] = Math.max(dp[1], dp[0] + prices[i-1]);
        }
        return dp[1];
    }

    /**
     * 2、买卖股票的最佳时机 II
     * 给定一个数组 `prices` ，其中 `prices[i]` 表示股票第 `i` 天的价格。
     * 在每一天，你可能会决定购买和/或出售股票。你在任何时候 **最多** 只能持有 **一股** 股票。你也可以购买它，然后在 **同一天** 出售。
     * 返回 *你能获得的 **最大** 利润* 。
     *
     * 输入: prices = [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     */
    //时间复杂度：O(n)
    //空间复杂度：O(n)
    public int maxProfit6(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1; i<n; i++){
            //第i天出售股票
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            //第i天买入股票
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }

    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public int maxProfit7(int[] prices){
        int[] dp = new int[2];
        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i=1; i<=prices.length; i++){
            //前天持有。再次购买，加上之前的收益
            dp[0] = Math.max(dp[0], dp[1] - prices[i-1]);
            //前天卖出。或者当天卖出，先持有
            dp[1] = Math.max(dp[1], dp[0] + prices[i-1]);
        }
        return dp[1];
    }


    /**
     * 3、买卖股票的最佳时机 III
     * 给定一个数组，它的第 `i` 个元素是一支给定的股票在第 `i` 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 **两笔** 交易。
     * **注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     */
    /**
     * 一天一共就有五个状态，
     * 1. 没有操作
     * 2. 第一次买入
     * 3. 第一次卖出
     * 4. 第二次买入
     * 5. 第二次卖出
     *
     * dp[i] [j]中 i表示第i天，j为 [0 - 4] 五个状态，dp[i] [j]表示第i天状态j所剩最大现金
     * 达到dp[i] [1]状态，有两个具体操作：
     * - 操作一：第i天买入股票了，那么dp[i] [1] = dp[i-1] [0] - prices[i]
     * - 操作二：第i天没有操作，而是沿用前一天买入的状态，即：dp[i] [1] = dp[i - 1] [1]
     * 一定是选最大的，所以 dp[i] [1] = max(dp[i-1] [0] - prices[i], dp[i - 1] [1]);
     */
    public int maxProfit8(int[] prices){
        int len = prices.length;
        if(prices.length == 0){
            return 0;
        }

        int[][] dp = new int[len][5];
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];

        for(int i=1; i<len; i++){
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i][1] + prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i][2] - prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i][3] + prices[i]);
        }
        return dp[len-1][4];
    }

    //空间优化
    public int maxProfit9(int[] prices){
        int[] dp = new int[4];
        // 存储两次交易的状态就行了
        // dp[0]代表第一次交易的买入
        dp[0] = -prices[0];
        // dp[1]代表第一次交易的卖出
        dp[1] = 0;
        // dp[2]代表第二次交易的买入
        dp[2] = -prices[0];
        // dp[3]代表第二次交易的卖出
        dp[3] = 0;
        for(int i=1; i<=prices.length; i++){
            // 要么保持不变，要么没有就买，有了就卖
            dp[0] = Math.max(dp[0], -prices[i-1]);
            dp[1] = Math.max(dp[1], dp[0]+prices[i-1]);
            // 这已经是第二次交易了，所以得加上前一次交易卖出去的收获
            dp[2] = Math.max(dp[2], dp[1]-prices[i-1]);
            dp[3] = Math.max(dp[3], dp[2]+ prices[i-1]);
        }
        return dp[3];
    }

    /**
     * 4、买卖股票的最佳时机 IV
     * 给定一个整数数组 `prices` ，它的第 `i` 个元素 `prices[i]` 是一支给定的股票在第 `i` 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 **k** 笔交易。
     * **注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     */
    public int maxProfit10(int k, int[] prices){
        if(prices.length == 0){
            return 0;
        }
        int len = prices.length;
        //[天数][交易次数][是否持有股票]
        int[][][] dp = new int[len][k+1][2];

        //初始化数据，确保最终结果是最多k次买卖的最大利润
        for(int i=0; i<=k; i++){
            dp[0][i][1] = -prices[0];
        }

        for(int i=1; i<len; i++){
            for(int j=1; j<=k; j++){
                //dp方程，0表示不持有/卖出，1表示持有/买入
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }
        return dp[len-1][k][1];
    }

    /**
     * 5、最佳买卖股票时机含冷冻期
     * 给定一个整数数组`prices`，其中第 `prices[i]` 表示第 `i` 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * - 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * **注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */
    /**
     * 思路
     * 我们用 f[i]  表示第 i 天结束之后的「累计最大收益」。根据题目描述，由于我们最多只能同时买入（持有）一支股票，并且卖出股票后有冷冻期的限制，因此我们会有三种不同的状态：
     * - 我们目前持有一支股票，对应的「累计最大收益」记为 f[i] [0]；
     * - 我们目前不持有任何股票，并且处于冷冻期中，对应的「累计最大收益」记为 f[i] [1]；
     * - 我们目前不持有任何股票，并且不处于冷冻期中，对应的「累计最大收益」记为 f[i] [2]
     */
    public int maxProfit11(int[] prices){
        if(prices.length == 0){
            return 0;
        }
        int n = prices.length;
        //f[i][0] 手上持有股票的最大收益
        //f[i][1] 手上不持有股票，并且处于冷冻期中的最大收益
        //f[i][2] 手上不持有股票，并且不处于冷冻期中的最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for(int i=1; i<n; i++){
            f[i][0] = Math.max(f[i-1][0], f[i-1][2]-prices[i]);
            f[i][1] = f[i-1][0] + prices[i];
            f[i][2] = Math.max(f[i-1][1], f[i-1][2]);
        }
        return Math.max(f[n-1][1], f[n-1][2]);
    }

    /**
     * 6、买卖股票的最佳时机含手续费
     * 给定一个整数数组 `prices`，其中 `prices[i]`表示第 `i` 天的股票价格 ；整数 `fee` 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * **注意：**这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     */
    /**
     * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出：8
     * 解释：能够达到的最大利润:
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
     */
    public int maxProfit12(int[] prices, int fee){
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        for(int i=1; i<len; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i] - fee);
        }
        return Math.max(dp[len-1][0], dp[len-1][1]);
    }
}

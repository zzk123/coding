package com.zzk.algorithm.carl.dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 动态规划
 * 完全背包 demo
 */
public class PackageDemo {
    /**
     * 1、零钱兑换
     * 给你一个整数数组 `coins` ，表示不同面额的硬币；以及一个整数 `amount` ，表示总金额。
     * 计算并返回可以凑成总金额所需的 **最少的硬币个数** 。如果没有任何一种硬币组合能组成总金额，返回 `-1` 。
     * 你可以认为每种硬币的数量是无限的。
     *
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     */
    public int coinChange(int[] coins, int amount){
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for(int i=0; i<=amount; i++){
            for(int j=0; j<coins.length; j++){
                if(i-coins[i] < 0){
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i-coins[i]]+1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 2、组合总和 Ⅳ
     * 给你一个由 **不同** 整数组成的数组 `nums` ，和一个目标整数 `target` 。请你从 `nums` 中找出并返回总和为 `target` 的元素组合的个数。
     * 题目数据保证答案符合 32 位整数范围。
     *
     * 输入：nums = [1,2,3], target = 4
     * 输出：7
     * 解释：
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * 请注意，顺序不同的序列被视作不同的组合。
     */
    /**
     * 思路
     * 求的是排序，强调顺序，就是外层 for 遍历背包，内层for循环遍历物品
     */
    public int combinationSum(int[] nums, int target){
        int[] dp = new int[target+1];
        dp[0] = 1;
        //外面数值
        for(int i=0; i<=target; i++){
            //里面数组值
            for(int j=0; j<nums.length; j++){
                if(i >= nums[j]){
                    dp[i] += dp[i-nums[i]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 3、零钱兑换 II
     * 给你一个整数数组 `coins` 表示不同面额的硬币，另给一个整数 `amount` 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 `0` 。
     * 假设每一种面额的硬币有无限个。
     *
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     *
     * 本题求的是组合，不强调顺序 ，求组合就是外层for遍历物品，内层for循环遍历背包
     */
    public int change(int amout, int[] coins){
        int[] dp = new int[amout+1];
        dp[0] = 1;
        //外面钱币
        for(int j=0; j<coins.length; j++){
            //里面金额
            for(int i=0; i<=amout; i++){
                if(i >= coins[j]){
                    dp[i] += dp[i-coins[j]];
                }
            }
        }
        return dp[amout];
    }

    /**
     * 4、爬楼梯
     * 假设你正在爬楼梯。需要 `n` 阶你才能到达楼顶。
     * 每次你可以爬 `1` 或 `2` 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     *
     * 题目修改
     * 一步一个台阶，两个台阶，三个台阶，.......，直到 m个台阶。问有多少种不同的方法可以爬到楼顶呢？
     *
     * 总结
     * 1阶，2阶，.... m阶就是物品，楼顶就是背包。
     * 每一阶可以重复使用，例如跳了1阶，还可以继续跳1阶。
     * 问跳到楼顶有几种方法其实就是问装满背包有几种方法，就是一个完全背包问题
     */
    public int climbStairs(int n){
        int[] dp = new int[n+1];
        int[] weight = {1,2};
        dp[0] = 1;

        //背包
        for(int i=0; i<=n; i++){
            //物品
            for(int j=0; j<weight.length; j++){
                if(i >= weight[j]){
                    dp[i] += dp[i-weight[j]];
                }
            }
        }
        return dp[n];
    }

    /**
     * 5、完全平方数
     * 给你一个整数 `n` ，返回 *和为 `n` 的完全平方数的最少数量* 。
     *
     * 本题求最小个数，那么有顺序和没有顺序都可以，都不影响最小个数
     */
    public int numSquares(int n){
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n+1];
        // 初始化
        for(int j=0; j<=n; j++){
            dp[j] = max;
        }
        // 当和为0时，组合的个数为0

        dp[0] = 0;
        // 遍历背包
        for(int j=1; j<=n; j++){
            // 遍历物品
            for(int i=1; i*i <= j; i++){
                dp[j] = Math.min(dp[j], dp[j-i*i] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 6、单词拆分
     * 给你一个字符串 `s` 和一个字符串列表 `wordDict` 作为字典。请你判断是否可以利用字典中出现的单词拼接出 `s` 。
     * **注意：**不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     *输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     *
     *
     */
    public boolean wordBreak(String s, List<String> wordDict){
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i=1; i<=s.length(); i++){
            for(int j=0; j<i; j++){
                if(dp[j] && wordDictSet.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

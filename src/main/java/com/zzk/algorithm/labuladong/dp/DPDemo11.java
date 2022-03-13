package com.zzk.algorithm.labuladong.dp;
import java.util.HashMap;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-13 13:39
 */
public class DPDemo11 {

    /**
     * 给定一个非负整数数组 nums 和一个目标值 target，可以给每一个元素 nums[i]添加正号+和负号-，
     * 请问有几种负号的组合能够使得 nums元素中的和为 targte
     */

    /**
     * 解法3：使用动态规划
     * 如果把 nums划分为两个子集A，B，分别代表分配 + or - 的数，那么它们和target存在如下关系
     * sum(A) - sum(B) = target
     * sum(A) = sum(B) + target
     * 2sum(A) = sum(num) + target
     * 可以推出 sum(A) = (target + sum(nums))/2，把原问题转为：
     * nums中存在几个子集sum(A)，使得A中的元素和为 (target + sum(nums))/2
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays3(int[] nums, int target){
        int sum = 0;
        for(int n: nums){
            sum += n;
        }
        if(sum < target || (sum + target) % 2 == 1){
            return 0;
        }
        return subsets(nums, (sum + target)/2);
    }
    public int subsets(int[] nums, int sum){
        int n = nums.length;
        int[][] dp = new int[n+1][sum+1];
        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
        }
        for(int i=1; i<=n; i++){
            for(int j=0; j<=sum; j++){
                if(j >= nums[i-1]){
                    //两种选择的结果之和
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                }else{
                    //背包的空间不足，只能选择不装物品i
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * 针对subsets方法的优化，从二维数组降为一维数组
     * @param nums
     * @param sum
     * @return
     */
    public int subsets2(int[] nums, int sum){
        int n = nums.length;
        int[] dp = new int[sum+1];
        dp[0] = 1;

        for(int i=1; i<=n; i++){
            for(int j=sum; j>=0; j--){
                if(j >= nums[i-1]){
                    dp[j] = dp[j] + dp[j-nums[i-1]];
                 }else{
                    dp[j] = dp[j];
                }
            }
        }
        return dp[sum];
    }

    /**
     * 解法2：针对解法1的优化，消除重叠小问题
     * 时间复杂度 O(N*target)
     * @param nums
     * @param target
     * @return
     */
    int findTargetSumWays2(int[] nums, int target){
        if(nums.length == 0){
            return 0;
        }
        return dp(nums, 0, target);
    }
    //备忘录
    HashMap<String, Integer> memo = new HashMap<>();
    public int dp(int[] nums, int i, int rest){
        if(i == nums.length){
            if(rest == 0){
                return 1;
            }
            return 0;
        }
        // 把他们转为字符串作为哈希表的键
        String key = i + "," + rest;
        //避免重复计算
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        //穷举计算
        int result = dp(nums, i+1, rest-nums[i]) + dp(nums, i+1, rest+nums[i]);
        //记录
        memo.put(key, result);
        return result;
    }


    /**
     * 解法1，回溯算法，时间复杂度 O(2^N)，N为nums的大小
     * @param nums
     * @param target
     * @return
     */
    int result = 0;
    public int findTargetSumWays(int[] nums, int target){
        if(nums.length == 0){
            return 0;
        }
        backtrack(nums, 0, target);
        return result;
    }

    /**
     * 回溯算法
     * @param nums
     * @param i
     * @param rest
     */
    public void backtrack(int[] nums, int i, int rest){
        if(i == nums.length){
            if(rest == 0){
                result++;
            }
            return;
        }
        //给 nums[i]-号
        rest += nums[i];
        //穷举 nums[i+1]
        backtrack(nums, i+1, rest);
        //撤销选择
        rest -= nums[i];
        //给 nums[i] +号
        rest -= nums[i];
        //穷举 nums[i+1]
        backtrack(nums, i+1, rest);
        //撤销选择
        rest += nums[i];
    }

}

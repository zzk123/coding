package com.zzk.algorithm.carl.dynamic;

/**
 * 动态规划
 * 01背包 demo
 */
public class Package01Demo {

    /**
     * 1、分割等和子集
     * 给你一个 **只包含正整数** 的 **非空** 数组 `nums` 。
     * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
     */
    /**
     * 思路
     * 定义数组：dp[j]表示容量为j的背包，所背的物品价值可以最大为 dp[j]
     * 递推公式：dp[j] = max(dp[j], dp[j - weight[i]] + value[i])，
     * 本题中物品 i 的重量为 nums[i]，价值为 nums[i]，
     * 所以递推公式为：dp[j] = max(dp[j], dp[j-nums[i]] + nums[i])
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        //物品在外，元素在外
        for (int i = 0; i < n; i++) {
            //倒序循环，一维数组要这样
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }

    /**
     * 2、最后一块石头的重量 II
     * 有一堆石头，用整数数组 `stones` 表示。其中 `stones[i]` 表示第 `i` 块石头的重量。
     * 每一回合，从中选出**任意两块石头**，然后将它们一起粉碎。假设石头的重量分别为 `x` 和 `y`，且 `x <= y`。那么粉碎的可能结果如下：
     * - 如果 `x == y`，那么两块石头都会被完全粉碎；
     * - 如果 `x != y`，那么重量为 `x` 的石头将会完全粉碎，而重量为 `y` 的石头新重量为 `y-x`。
     * 最后，**最多只会剩下一块** 石头。返回此石头 **最小的可能重量** 。如果没有石头剩下，就返回 `0`。
     *
     * 输入：stones = [2,7,4,1,8,1]
     * 输出：1
     * 解释：
     * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
     * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
     * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
     * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
     */
    /**
     * 思路
     * 01背包问题。以上问题可以转化为石头尽量分为重量相同的两堆，这样相撞后剩下的石头最小
     * dp[j]表示重量为 j 的背包，最多可以背 dp[j] 重量的石头
     * 递推公式：dp[j] = max(dp[j], dp[j-stores[i]] + store[i])
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int s : stones) {
            sum += s;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }

    /**
     * 3、一和零
     * 给你一个二进制字符串数组 `strs` 和两个整数 `m` 和 `n` 。
     * 请你找出并返回 `strs` 的最大子集的长度，该子集中 **最多** 有 `m` 个 `0` 和 `n` 个 `1` 。
     * 如果 `x` 的所有元素也是 `y` 的元素，集合 `x` 是集合 `y` 的 **子集**
     * <p>
     * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     * 输出：4
     * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
     * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0];
            int ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones]);
                    }
                }
            }
        }
        return dp[length][m][n];
    }

    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    /**
     * 4、目标和
     * 给你一个整数数组 `nums` 和一个整数 `target` 。
     * 向数组中的每个整数前添加 `'+'` 或 `'-'` ，然后串联起所有整数，可以构造一个 **表达式** ：
     * - 例如，`nums = [2, 1]` ，可以在 `2` 之前添加 `'+'` ，在 `1` 之前添加 `'-'` ，然后串联起来得到表达式 `"+2-1"` 。
     * 返回可以通过上述方法构造的、运算结果等于 `target` 的不同 **表达式** 的数目。
     *
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     */

    /**
     * 思路
     * 记数组元素和为 sum，添加 负号的元素和为 neg，添加 正号的和为 sum - neg，
     * 那么公式为 neg = (sum-target)/2
     * 问题转化成在数组 nums  中选取若干元素，使得这些元素之和等于 neg
     */
    //动态规划
    public int findTargetSumWays(int[] nums, int target){
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        int diff = sum - target;
        if(diff < 0 || diff % 2 != 0){
            return 0;
        }
        int n = nums.length;
        int neg = diff/2;
        int[][] dp = new int[n+1][neg+1];
        dp[0][0] = 1;
        for(int i=1; i<=n; i++){
            int num = nums[i-1];
            for(int j=0; j<=neg; j++){
                dp[i][j] = dp[i-1][j];
                if(j >= num){
                    dp[i][j] += dp[i-1][j-num];
                }
            }
        }
        return dp[n][neg];
    }
    //优化空间
    public int findTargetSumWays2(int[] nums, int target){
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        int diff = sum - target;
        if(diff < 0 || diff % 2 != 0){
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg+1];
        dp[0] = 1;
        for(int num : nums){
            for(int j=neg; j >= neg; j--){
                dp[j] += dp[j-num];
            }
        }
        return dp[neg];
     }
}
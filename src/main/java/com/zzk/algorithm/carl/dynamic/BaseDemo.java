package com.zzk.algorithm.carl.dynamic;

import java.util.Arrays;

/**
 * 动态规划
 * 基础题目
 */
public class BaseDemo {

    /**
     * 1、斐波那契数
     * **斐波那契数** （通常用 `F(n)` 表示）形成的序列称为 **斐波那契数列** 。该数列由 `0` 和 `1` 开始，
     * 后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 `n` ，请计算 `F(n)` 。
     *
     * 输入：n = 2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     */
    //非压缩
    //时间复杂度：O(n)
    //空间复杂度：O(n)
    public int fib(int n){
        if(n <= 1){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int index=2; index <= n; index++){
            dp[index] = dp[index-1] + dp[index-2];
        }
        return dp[n];
    }

    //压缩
    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public int fib2(int n){
        if(n < 2){
            return n;
        }
        int a = 0;
        int b = 1;
        int c = 0;
        for(int i=1; i<n; i++){
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 2、使用最小花费爬楼梯
     * 给你一个整数数组 `cost` ，其中 `cost[i]` 是从楼梯第 `i` 个台阶向上爬需要支付的费用。一旦你支付此费用，
     * 即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 `0` 或下标为 `1` 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费
     *
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15
     *
     * 定义：到达第 i 个台阶所花费的最少体力为 dp[i]
     * 递推公式：dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i]
     */
    //时间复杂度：O(n)
    //空间复杂度：O(n)
    public int minCostClimbingStairs(int[] cost){
        if(cost == null || cost.length == 0){
            return 0;
        }
        if(cost.length == 1){
            return cost[0];
        }
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i=2; i<cost.length; i++){
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }

    /**
     * 3、 不同路径
     * 一个机器人位于一个 `m x n` 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * 输入：m = 3, n = 7
     * 输出：28
     */
    //动态规划
    //时间复杂度：O(n*m)
    //空间复杂度：O(n*m)
    public int uniquePaths(int m, int n){
        int[][] dp = new int[m][n];
        for(int i=0; i<n; i++){
            dp[0][i] = 1;
        }
        for(int i=0; i<m; i++){
            dp[i][0] = 1;
        }
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    //空间复杂度：O(n)
    public int uniquePaths2(int m, int n){
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                cur[j] += cur[j-1];
            }
        }
        return cur[n-1];
    }

    /**
     * 4、不同路径 II
     * 一个机器人位于一个 `m x n` 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 `1` 和 `0` 来表示。
     *
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     */
    /**
     * 思路
     * 定义：dp[i][j] 表示从（0,0）出发到（i,j）有 dp[i][j]条不同路径
     * 递推公式：当 obstacleGrid[i] [j] ==0 时， dp[i] [j] = dp[i-1] [j] + dp[i] [i-1]
     */
    //时间复杂度：O(n*m)
    //空间复杂度：O(n*m)
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        for(int i=0; i<m; i++){
            if(obstacleGrid[0][i] == 1){
                break;
            }
            dp[0][i] = 1;
        }
        for(int i=0; i<n; i++){
            if(obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(obstacleGrid[i][j] == 1){
                    continue;
                }
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[n-1][m-1];
    }

    //时间复杂度：O(m*n)
    //空间复杂度：O(m)
    public int uniquePathsWithObstacles2(int[][] obstacleGrid){
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[] dp = new int[m];
        dp[0] = (obstacleGrid[0][0] == 1) ? 0:1;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                //有障碍物的格子等于0
                if(obstacleGrid[i][j] == 1){
                    dp[j] = 0;
                }
                //否则 dp[j]的值由左方和上一次迭代的dp[j]累加而来
                else if(obstacleGrid[i][j] == 0 && j-1 >= 0){
                    dp[j] = dp[j] + dp[j-1];
                }
            }
        }
        return dp[m-1];
    }
    /**
     * 5、整数拆分
     * 给定一个正整数 `n` ，将其拆分为 `k` 个 **正整数** 的和（ `k >= 2` ），并使这些整数的乘积最大化。
     * 返回 *你可以获得的最大乘积*
     *
     * 输入: n = 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     */
    /**
     * 思路
     * 定义：dp[i] 表示 拆分数字i，可以得到最大乘积 dp[i]
     * 递推公式：dp[i] = Math.max(dp[i]，Math.max(j*(i-j)，dp[i-j] * j))
     */
    //时间复杂度：O(n^2)
    //空间复杂度：O(n)
    public int integerBreak(int n){
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i=3; i<=n; i++){
            for(int j=1; j<=i-j; j++){
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
            }
        }
        return dp[n];
    }

    /**
     * 6、最小路径和
     * 给定一个包含非负整数的 `*m* x *n*` 网格 `grid` ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * **说明：**每次只能向下或者向右移动一步。
     *
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     */
    //时间复杂度：O(m*n)
    //空间复杂度：O(m*n)
    public int minPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i=1; i<m; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i=1; i<n; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        int min = 0;
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
    //动态规划（空间优化）
    public int minPathSum2(int[][] grid){
        int len = grid[0].length;
        int[] dp = new int[len];
        dp[0] = grid[0][0];
        for(int i=1; i<len; i++){
            dp[i] = dp[i-1] + grid[0][i];
        }
        for(int i=1; i<grid.length; i++){
            dp[0] = dp[0] + grid[i][0];
            for(int j=1; j<len; j++){
                dp[j] = Math.min(dp[j-1] + grid[i][j], dp[j] + grid[i][j]);
            }
        }
        return dp[len-1];

    }

     /**
     * 7、不同的二叉搜索树
      * 给你一个整数 `n` ，求恰由 `n` 个节点组成且节点值从 `1` 到 `n` 互不相同的 **二叉搜索树** 有多少种？
      * 返回满足题意的二叉搜索树的种数
      *
      * 输入：n = 3
      * 输出：5
     */
     //时间复杂度：O(n^2)
     //空间复杂度：O(n)
     public int numTrees(int n){
         int[] G = new int[n+1];
         G[0] = 1;
         G[1] = 1;

         for(int i=2; i<=n; i++){
             for(int j=1; j<=i; j++){
                 G[i] += G[j-1] * G[i-j];
             }
         }
         return G[n];
     }

     /**
     * 8、最大正方形
      * 在一个由 `'0'` 和 `'1'` 组成的二维矩阵内，找到只包含 `'1'` 的最大正方形，并返回其面积。
      *
      * 输入：matrix = [
      * ["1","0","1","0","0"],
      * ["1","0","1","1","1"],
      * ["1","1","1","1","1"],
      * ["1","0","0","1","0"]
      * ]
      * 输出：4
     */
    /**
     * 思路
     * 定义：dp[i,j] 代表的是 以坐标点(i,j)为右下角的最大正方形
     * 递推公式：dp[i,j] = min(dp[i,j-1], dp[i-1, j-1], dp[i-1,j]) + 1
     */
    //时间复杂度：O(m*n)
    //空间复杂度：O(m*n)
    public int maximalSquare(char[][] matrix){
        int maxSide = 0;
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return maxSide;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if(matrix[i][j] == '1'){
                    if(i == 0 || j ==0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i-1][j-1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
    * 9、接雨水
     * 给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
     *
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
    */
    //时间复杂度：O(n)
    //空间复杂度：O(n)
    public int trap(int[] height){
        int n = height.length;
        if(n == 0){
            return 0;
        }
        //左边柱子最高
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for(int i=1; i<n; i++){
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        //右边柱子最高
        int[] rightMax = new int[n];
        rightMax[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--){
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        //相加
        int ans = 0;
        for(int i=0; i<n; i++){
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
    //双指针
    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public int trap2(int[] height){
        int ans = 0;
        int left = 0;
        int right = height.length-1;
        int leftMax = 0;
        int rightMax = 0;

        while(left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if(height[left] < height[right]){
                ans += leftMax - height[left];
                left++;
            }else{
                ans += right - height[right];
                right--;
            }
        }
        return ans;
    }

     /**
     * 10、最大子数组和
      * 给你一个整数数组 `nums` ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
      * **子数组** 是数组中的一个连续部分
      *
      * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
      * 输出：6
      * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
     public int maxSubArray(int[] nums){
         if(nums.length == 0){
             return 0;
         }
         int res = nums[0];
         int[] dp = new int[nums.length];
         dp[0] = nums[0];
         for(int i=1; i<nums.length; i++){
             dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
             res = Math.max(res, dp[i]);
         }
         return res;
     }

     public int maxSubArray2(int[] nums){
         int res = nums[0];
         int pre = nums[0];
         for(int i=1; i<nums.length; i++){
             pre = Math.max(pre + nums[i], nums[i]);
             res = Math.max(res, pre);
         }
         return res;
     }

     /**
     * 11、乘积最大子数组
      * 给你一个整数数组 `nums` ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
      * 测试用例的答案是一个 **32-位** 整数。
      * **子数组** 是数组的连续子序列。
      *
      * 输入: nums = [2,3,-2,4]
      * 输出: 6
      * 解释: 子数组 [2,3] 有最大乘积 6。
     */
     //动态规划
     //时间：O(n)
     //空间：O(n)
    public int maxProduct(int[] nums){
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);

        for(int i=1; i<length; i++){
            maxF[i] = Math.max(maxF[i-1] * nums[i], Math.max(nums[i], minF[i-1] * nums[i]));
            minF[i] = Math.min(minF[i-1] * nums[i], Math.min(nums[i], maxF[i-1] * nums[i]));
        }
        int ans = maxF[0];
        for(int i=1; i<length; i++){
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
    //优化空间
    public int maxProduct2(int[] nums){
        int maxF = nums[0];
        int minF = nums[0];
        int ans = nums[0];
        int length = nums.length;
        for(int i=1; i<length; i++){
            int mx = maxF;
            int mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}

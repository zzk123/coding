package com.zzk.algorithm.carl.dynamic;

import com.zzk.algorithm.carl.tree.TreeNode;

import java.util.HashMap;

/**
 * 动态规划
 * 打家劫舍
 */
public class DynamicDemo02 {

    /**
     * 1、打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警**。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 **不触动警报装置的情况下** ，一夜之内能够偷窃到的最高金额。
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     */
    public int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<length; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[length-1];
    }

    /**
     * 2、打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 **围成一圈** ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警** 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 **在不触动警报装置的情况下** ，今晚能够偷窃到的最高金额。
     *
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     */
    public int rob2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        return Math.max(rob2(nums, 0, nums.length-1), rob2(nums, 1, nums.length));
    }

    public int rob2(int[] nums, int start, int end){
        int x = 0, y = 0, z = 0;
        for(int i=start; i<end; i++){
            y = z;
            z = Math.max(y, x + nums[i]);
            x = y;
        }
        return z;
    }

    /**
     * 3、打家劫舍 III
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额
     *
     * 输入: root = [3,2,3,null,3,null,1]
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
     */
    //暴力递归，最优子结构
    public int rob3(TreeNode root){
        if(root == null){
            return 0;
        }
        int money = root.val;
        if(root.left != null){
            money += (rob3(root.left.left) + rob3(root.left.right));
        }
        if(root.right != null){
            money += (rob3(root.right.left) + rob3(root.right.right));
        }
        return Math.max(money, rob3(root.left) + rob3(root.right));
    }
    //记忆化 - 解决重复子问题
    public int rob4(TreeNode root){
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }
    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo){
        if(root == null){
            return 0;
        }
        if(memo.containsKey(root)){
            return memo.get(root);
        }
        int money = root.val;
        if(root.left != null){
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if(root.right != null){
            money += (robInternal(root.right.left, memo) + robInternal(root.right.left, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }
    //终极解法
    /**
     * 使用一个大小为2的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷
     * - 当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
     * - 当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
     *
     * root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) + Math.max(rob(root.right)[0], rob(root.right)[1])
     * root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val
     */
    public int rob5(TreeNode root){
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root){
        if(root == null){
            return new int[2];
        }
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }
}

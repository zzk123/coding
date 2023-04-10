package com.zzk.algorithm.carl.tree;

/**
 * 树
 */
public class TreeDemo {


    /**
     * 1、二叉树中的最大路径和
     * **路径** 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 **至多出现一次** 。该路径 **至少包含一个** 节点，且不一定经过根节点。
     * **路径和** 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 `root` ，返回其 **最大路径和**
     *
     * 输入：root = [1,2,3]
     * 输出：6
     * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
     */
    /**
     * 递归
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     */
    int maxSum = Integer.MIN_VALUE;
    public int maxGain(TreeNode node){
        if(node == null){
            return 0;
        }
        //计算左右节点最大值
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        //计算当前最大值
        int priceNewPath = node.val + leftGain + rightGain;
        //更新
        maxSum = Math.max(maxSum, priceNewPath);

        return node.val + Math.max(leftGain, rightGain);
    }
}

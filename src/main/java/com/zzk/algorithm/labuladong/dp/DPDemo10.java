package com.zzk.algorithm.labuladong.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-12 23:11
 */
public class DPDemo10 {

    /***
     * 线性排序
     *  街上有一排房屋，围成一棵树，用一个包含非负整数的数组nums表示，每个元素nums[i]代表i间房子中的现金树，
     *  可以从房子取钱，但是相邻的房子的钱不能被同时取出，在满足条件的情况能最多取出多少钱？
     *  房子在二叉树的节点上，不能同时从相连的房子中取钱
     **/

    //备忘录
    public Map<TreeNode, Integer> memo = new HashMap<>();

    /**
     * 时间复杂度为 O(N)，N为树节点数
     * @param root
     * @return
     */
    public int rob(TreeNode root){
        if(root == null){
            return 0;
        }
        //利用备忘录消除重复子问题
        if(memo.containsKey(root)){
            return memo.get(root);
        }
        //取，然后去下下家做选择
        int do_it = root.val
                + (root.left == null ?
                0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ?
                0 : rob(root.right.left) + rob(root.right.right));
        //不取，然后去下家做选择
        int not_do = rob(root.left) + rob(root.right);
        //找到收益更大的
        int res = Math.max(do_it, not_do);
        return res;
    }
    /**
     * 树节点
     */
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(TreeNode left, TreeNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }
}

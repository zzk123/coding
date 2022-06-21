package com.zzk.algorithm.leetcode.test;

import java.util.Stack;

/**
 @program: coding
 @description:
 @author: zzk
 @create: 2022-06-14 23:11
 */
public class Solution18 {

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    public void inOrderTraversal(TreeNode tree){
         Stack<TreeNode> stack = new Stack<>();
         while(tree != null || !stack.isEmpty()){
             while(tree != null){
                 stack.push(tree);
                 tree = tree.left;
             }
             if(!stack.isEmpty()){
                 tree = stack.pop();
                 System.out.println(tree.val);
                 tree = tree.right;
             }
         }
    }
}

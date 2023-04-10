package com.zzk.algorithm.carl.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 树的遍历
 */
public class TreeTraversalDemo2 {

    /**
     * 1、二叉树的前序遍历
     * 给你二叉树的根节点 `root` ，返回它节点值的 **前序** 遍历。
     *
     * 输入：root = [1,null,2,3]
     * 输出：[1,2,3]
     */
    /**
     * 递归
     */
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }
    public void preorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }
    //前序遍历顺序：中 - 左 - 右，入栈顺序：中 - 右 - 左
    public List<Integer> preorderTravelsal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 2、二叉树的后序遍历
     * 给你一棵二叉树的根节点 `root` ，返回其节点值的 **后序遍历** 。
     *
     * 输入：root = [1,null,2,3]
     * 输出：[3,2,1]
     */
    /**
     * 递归
     */
    public List<Integer> postorderTravelsal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        postorderTravelsal(root, list);
        return list;
    }

    public void postorderTravelsal(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        postorderTravelsal(root.left, list);
        postorderTravelsal(root.right, list);
        list.add(root.val);
    }
    //后序遍历顺序：左，右，中，入栈顺序：中 - 左 - 右。出栈顺序：中 - 右 - 左，最后翻转结果
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
    /**
     * 3、二叉树的中序遍历
     * 给定一个二叉树的根节点 `root` ，返回 *它的 **中序** 遍历* 。
     *
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     */
    //递归
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    public void inorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right);
    }

    //中序遍历：左 - 中 - 右，入栈顺序：左 - 右
    public List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }
}

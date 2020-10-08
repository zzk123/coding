package com.zzk.coding.binaryTree;

import java.util.*;

/**
 * @program: coding
 * @description: 23.统计和生成所有不同的二叉树
 * @author: zzk
 * @create: 2020-10-08 15:26
 */
public class GetNumTrees {

    /**
     * 节点定义
     */
    public class Node{

        public int value;

        public Node left;

        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 统计生成所有不同的二叉树
     * 时间复杂度为O(N^2)
     * @param n
     * @return
     */
    public int numTrees(int n){
        if(n < 2){
            return 1;
        }
        int[] num = new int[n+1];
        num[0] = 1;
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < i + 1; j++){
                num[i] += num[j - 1] * num[i - j];
            }
        }
        return num[n];
    }

    /**
     * N的含义不变，假设可能的二叉树结构有M种，请返回M个二叉树头节点，每一棵二叉树代表一种可能的结构
     * @param n
     * @return
     */
    public List<Node> generateTrees(int n){
        return generate(1, n);
    }

    public List<Node> generate(int start, int end){
        List<Node> res = new LinkedList<>();
        if(start > end){
            res.add(null);
        }
        Node head = null;
        for(int i = start; i < end + 1; i++){
            head = new Node(i);
            List<Node> lSubs = generate(start, i-1);
            List<Node> rSubs = generate(i+1, end);
            for(Node l : lSubs){
                for(Node r : rSubs){
                    head.left = l;
                    head.right = r;
                    res.add(cloneTree(head));
                }
            }
        }
        return res;
    }


    public Node cloneTree(Node head){
        if(head == null){
            return null;
        }
        Node res = new Node(head.value);
        res.left = cloneTree(head.left);
        res.right = cloneTree(head.right);
        return res;
    }
}

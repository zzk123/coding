package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description: 8.找到二叉树中符合搜索二叉树条件的最大拓扑结构
 * @author: zzk
 * @create: 2020-10-03 13:46
 */
public class BstTopoSize1 {

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
     * 返回搜索二叉树的最大拓扑结构节点数
     * 时间复杂度为O(N^2)
     * @param head
     * @return
     */
    public int bstTopoSize1(Node head){
        if(head == null){
            return 0;
        }
        int max = maxTopo(head, head);
        max = Math.max(bstTopoSize1(head.left), max);
        max = Math.max(bstTopoSize1(head.right), max);
        return max;
    }

    /**
     * 返回搜索二叉树的最大拓扑结构节点数
     * @param h
     * @param n
     * @return
     */
    public int maxTopo(Node h, Node n){
        if(h != null && n != null && isBSTNode(h, n, n.value)){
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }

    /**
     * 判断是否为搜索二叉树
     * @param h
     * @param n
     * @param value
     * @return
     */
    public boolean isBSTNode(Node h, Node n, int value){
        if(h == null){
            return false;
        }
        if(h == n){
            return true;
        }
        return  isBSTNode(h.value > value ? h.left : h.right, n , value);
    }
}

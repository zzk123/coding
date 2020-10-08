package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description: 20.二叉树节点间的最大距离问题
 * @author: zzk
 * @create: 2020-10-07 22:40
 */
public class MaxDIstance {

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


    public int maxDistance(Node head){
        int[] record = new int[1];
        return posOrder(head, record);
    }

    public int posOrder(Node head, int[] record){
        if(head == null){
            record[0] = 0;
            return 0;
        }
        int lMax = posOrder(head.left, record);
        int maxfromLeft = record[0];
        int rMax = posOrder(head.right, record);
        int maxFromRight = record[0];
        int curNodeMax = maxfromLeft + maxFromRight + 1;
        record[0] = Math.max(maxfromLeft, maxFromRight) + 1;
        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }
}

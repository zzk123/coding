package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description: 7.找到二叉树中的最大搜索二叉树
 * @author: zzk
 * @create: 2020-10-03 10:54
 */
public class BiggestSubBST {

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
     * 找到二叉树中的最大搜索二叉树
     * @param head
     * @return
     */
    public Node biggestSubBST(Node head){
        int[] record = new int[3];
        return posOrder(head, record);
    }

    /**
     * 迭代找到二叉树中的最大搜索二叉树
     * @param head
     * @param record
     * @return
     */
    public Node posOrder(Node head, int[] record){
        if(head == null){
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(lMin, value);
        record[2] = Math.max(rMax, value);
        //如果左子树是最小树，右子树是最大树，返回该头部节点，并且统计节点数
        if(left == lBST && rBST == rBST && lMax < value && value < rMin){
            record[0] = lSize + rSize + 1;
            return head;
        }
        //如果左子树不是最小树或者右子树不是最大树，则比较左子树和右子树的节点数
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }
}

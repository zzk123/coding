package com.zzk.algorithm.coding.binaryTree;

/**
 * @program: coding
 * @description: 24.统计完全二叉树的节点数
 * @author: zzk
 * @create: 2020-10-08 22:08
 */
public class getNodeNum {

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
     * 如果完全二叉树的层数为h，时间复杂度为O(h^2)
     * @param head
     * @return
     */
    public int nodeNum(Node head){
        if(head == null){
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    public int bs(Node node, int l, int h){
        if(l == h){
            return l;
        }
        if(mostLeftLevel(node.right, l+1) == h){
            return (l << (h - l)) + bs(node.right, l+1, h);
        }else{
            return (l << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }

    public int mostLeftLevel(Node node, int level){
        while(node != null){
            level++;
            node = node.left;
        }
        return level-1;
    }
}

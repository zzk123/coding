package com.zzk.algorithm.coding.binaryTree;

/**
 * @program: coding
 * @description: 3.如何较为直观的打印二叉树
 * @author: zzk
 * @create: 2020-09-27 23:04
 */
public class PrintTree {

    public class Node{

        public int value;

        public Node left;

        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public void printTree(Node head){
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public void printInOrder(Node head, int height, String to, int len){
        if(head == null){
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public String getSpace(int num){
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for(int i = 0; i < num; i++){
            buf.append(space);
        }
        return buf.toString();
    }
}

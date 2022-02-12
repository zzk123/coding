package com.zzk.algorithm.coding.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: coding
 * @description: 9.二叉树的按层打印与ZigZag打印 - 按层打印
 * @author: zzk
 * @create: 2020-10-03 14:39
 */
public class PrintByLevel {

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
     * 按层输出节点
     * @param head
     */
    public void printByLevel(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        int level = 1;
        Node last = head;
        Node nLast = null;
        queue.offer(head);
        System.out.println("Level " + (level++) + ":");
        while(!queue.isEmpty()){
            head = queue.poll();
            System.out.println(head.value + " ");
            if(head.left != null){
                queue.offer(head.left);
                nLast = head.left;
            }
            if(head.right != null){
                queue.offer(head.right);
                nLast = head.right;
            }
            if(head == last && !queue.isEmpty()){
                System.out.println("\nLevel " + (level++) + ":");
                last = nLast;
            }
        }
        System.out.println();
    }

}

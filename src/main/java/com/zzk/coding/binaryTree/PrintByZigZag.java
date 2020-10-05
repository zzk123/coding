package com.zzk.coding.binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: coding
 * @description: 9.二叉树的按层打印与ZigZag打印 - 按ZigZag打印
 * @author: zzk
 * @create: 2020-10-03 14:54
 */
public class PrintByZigZag {

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
     * 按ZigZag打印
     * 使用last，nlast标记每层的最后一个节点
     * @param head
     */
    public void printByZigZag(Node head){
        if(head == null){
            return;
        }
        Deque<Node> dq = new LinkedList<>();
        int level = 1;
        boolean lr = true;
        Node last = head;
        Node nLast = null;
        dq.offerFirst(head);
        pringLevelAndOrientation(level++, lr);
        while(!dq.isEmpty()){
            if(lr){
                head = dq.pollFirst();
                if(head.left != null){
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerLast(head.left);
                }
                if(head.right != null){
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerLast(head.right);
                }
            }else{
                head = dq.pollLast();
                if(head.right != null){
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerFirst(head.right);
                }
                if(head.left != null){
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");
            if(head == last && !dq.isEmpty()){
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();
                pringLevelAndOrientation(level++, lr);
            }
        }
        System.out.println();
    }
    public void pringLevelAndOrientation(int level, boolean lr){
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right : " :  "right to left : ");
    }
}

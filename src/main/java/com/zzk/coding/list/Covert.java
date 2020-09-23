package com.zzk.coding.list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: coding
 * @description: 15.将搜索二叉树转换成双向链表
 * @author: zzk
 * @create: 2020-09-23 22:13
 */
public class Covert {

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
     * 用队列等容器收集二叉树中序遍历结果的方法
     * 时间复杂度为O(N)，额外空间复杂度为O(N)
     * @param head
     * @return
     */
    public Node convert1(Node head){
        Queue<Node> queue = new LinkedList<>();
        inOrderToQueue(head, queue);
        if(queue.isEmpty()){
            return head;
        }
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while(!queue.isEmpty()){
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    public void inOrderToQueue(Node head, Queue<Node> queue){
        if(head == null){
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }


    /**
     * 利用递归函数，除此外 不适用任何容器的方法
     * 时间复杂度为O(N)，额外空间复杂度为O(h)，h为二叉树的高度
     * @param head
     * @return
     */
    public Node convert2(Node head){
        if(head == null){
            return null;
        }
        Node last = process(head);
        head = last.right;
        last.right = null;
        return head;
    }

    public Node process(Node head){
        if(head == null){
            return null;
        }
        Node leftE = process(head.left);
        Node rightE = process(head.right);
        Node leftS = leftE != null ? leftE.right : null;
        Node rightS = rightE != null ? rightE.right : null;
        if(leftE != null && rightE != null){
            leftE.right = head;
            head.left = leftE;
            head.right = rightS;
            rightS.left = head;
            rightE.right = leftS;
            return rightE;
        }else if(leftE != null){
            leftE.right = head;
            head.left = leftE;
            head.right = leftS;
            return head;
        }else if(leftE != null){
            head.right = rightS;
            rightS.left = head;
            rightE.right = head;
            return rightE;
        }else{
            head.right = head;
            return head;
        }
    }
}

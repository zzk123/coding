package com.zzk.algorithm.coding.list;

/**
 * @program: coding
 * @description: 4.反转单向和双向链表
 * @author: zzk
 * @create: 2020-09-16 22:25
 */
public class ReverSeLIst {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 反转单链表
     * @param head
     * @return
     */
    public Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public class DoubleNode{

        public int value;

        public DoubleNode last;

        public DoubleNode next;

        public DoubleNode(int data){
            this.value = data;
        }
    }

    /**
     * 反转双向链表
     * @param head
     * @return
     */
    public DoubleNode reverseList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}

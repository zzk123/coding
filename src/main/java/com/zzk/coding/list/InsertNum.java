package com.zzk.coding.list;

/**
 * @program: coding
 * @description: 18.向有序的环形单链表中插入新节点
 * @author: zzk
 * @create: 2020-09-23 23:31
 */
public class InsertNum {

    /**
     *节点定义
     */
    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 时间复杂度为O(N)，额外空间复杂度为O(1)
     * @param head
     * @param num
     * @return
     */
    public Node insertNum(Node head, int num){
        Node node = new Node(num);
        if(head == null){
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;
        while(cur != head){
            if(pre.value <= num && cur.value >= num){
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return head.value < num ? head : node;
    }
}

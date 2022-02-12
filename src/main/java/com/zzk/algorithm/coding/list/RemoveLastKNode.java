package com.zzk.algorithm.coding.list;

/**
 * @program: coding
 * @description: 2.在单链表和双链表中删除倒数第K个节点
 * @author: zzk
 * @create: 2020-09-14 22:58
 */
public class RemoveLastKNode {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 单链表删除第K个节点
     * @param head
     * @param lastKth
     * @return
     */
    public Node removeLastKthNode(Node head, int lastKth){
        if(head == null || lastKth < 1){
            return head;
        }
        Node cur = head;
        while(cur != null){
            lastKth--;
            cur = cur.next;
        }
        if(lastKth == 0){
            head = head.next;
        }
        if(lastKth < 0){
            cur = head;
            while(++lastKth != 0){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
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
     * 删除双链表的第K个节点
     * @param head
     * @param lastKth
     * @return
     */
    public DoubleNode removeLastKthNode(DoubleNode head, int lastKth){
        if(head == null || lastKth < 1){
            return head;
        }
        DoubleNode cur = head;
        while(cur != null){
            lastKth --;
            cur = cur.next;
        }
        if(lastKth == 0){
            head = head.next;
            head.last = null;
        }

        if(lastKth < 0){
            cur = head;
            while(++lastKth != 0){
                cur = cur.next;
            }
            DoubleNode newNext = cur.next.next;
            cur.next = newNext;
            if(newNext != null){
                newNext.last = cur;
            }
        }
        return head;
    }
}

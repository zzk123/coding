package com.zzk.coding.list;

/**
 * @program: coding
 * @description: 20.按照左右半区的方式重新组合单链表
 * @author: zzk
 * @create: 2020-09-23 23:51
 */
public class Relocate {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param head
     */
    public void relocate(Node head){
        if(head == null || head.next == null){
            return;
        }
        Node mid = head;
        Node right = head.next;
        while(right.next != null && right.next.next != null){
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);
    }

    public void mergeLR(Node left, Node right){
        Node next = null;
        while(left.next != null){
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }
}

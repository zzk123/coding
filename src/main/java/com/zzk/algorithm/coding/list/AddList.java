package com.zzk.algorithm.coding.list;

import java.util.Stack;

/**
 * @program: coding
 * @description: 10.两个单链表生成相加链表
 * @author: zzk
 * @create: 2020-09-21 22:24
 */
public class AddList {

    /**
     * 节点结构
     */
    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }

    }

    /**
     * 利用栈结构求解
     * @param head1
     * @param head2
     * @return
     */
    public Node addList1(Node head1, Node head2){
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while(head1 != null){
            s1.push(head1.value);
            head1 = head1.next;
        }

        while(head2 != null){
            s2.push(head2.value);
            head2 = head2.next;
        }

        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node node = null;
        Node pre = null;
        while(!s1.isEmpty() || !s2.isEmpty()){
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
        }
        if(ca == 1){
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    /**
     * 利用链表的逆袭求解
     * @param head1
     * @param head2
     * @return
     */
    public Node addList2(Node head1, Node head2){
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node c1 = head1;
        Node c2 = head2;
        Node node = null;
        Node pre = null;
        while(c1 != null || c2 != null){
            n1 = c1 != null ? c1.value : 0;
            n2 = c2 != null ? c2.value : 0;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }
        if(ca == 1){
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        reverseList(head1);
        reverseList(head2);
        return node;
    }

    /**
     * 逆序链表
     * @param head
     * @return
     */
    private Node reverseList(Node head){
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
}

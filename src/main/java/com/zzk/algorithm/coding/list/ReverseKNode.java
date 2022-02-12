package com.zzk.algorithm.coding.list;

import java.util.Stack;

/**
 * @program: coding
 * @description: 12.将单链表的每个K节点之间逆序
 * @author: zzk
 * @create: 2020-09-21 23:34
 */
public class ReverseKNode {

    /**
     * 节点
     */
    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 使用栈结构
     * @param head
     * @param K
     * @return
     */
    public Node reverseKNode1(Node head, int K){
        if(K < 2){
            return head;
        }
        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;
        Node next = null;
        while(cur != null){
            next = cur.next;
            stack.push(cur);
            if(stack.size() == K){
                pre = resign1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    public Node resign1(Stack<Node> stack, Node left, Node right){
        Node cur = stack.pop();
        if(left != null){
            left.next = cur;
        }
        Node next = null;
        while(!stack.isEmpty()){
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }


    /**
     * 不需要栈结构，在原链表中调整
     * @param head
     * @param K
     * @return
     */
    public Node reverseKNode2(Node head, int K){
        if(K < 2){
            return head;
        }
        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;
        int count = 1;
        while(cur != null){
            next = cur.next;
            if(count == K){
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    public void resign2(Node left, Node start, Node end, Node right){
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while(cur != right){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if(left != null){
            left.next = end;
        }
        start.next = right;
    }
}

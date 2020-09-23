package com.zzk.coding.list;

import java.util.Stack;

/**
 * @program: coding
 * @description: 14.在单链表中删除指定值的节点
 * @author: zzk
 * @create: 2020-09-22 22:41
 */
public class RemoveValue {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 利用栈或者其它容器收集节点，时间复杂度为O(N)，额外空间复杂度为O(N)
     * @param head
     * @param num
     * @return
     */
    public Node removeValue1(Node head, int num){
        Stack<Node> stack = new Stack<>();
        while(head != null){
            if(head.value != num){
                stack.push(head);
            }
            head = head.next;
        }
        while(!stack.isEmpty()){
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }


    /**
     * 不用任何容器而直接调整，时间复杂度为O(N)，额外空间复杂度为O(1)
     * @param head
     * @param num
     * @return
     */
    public Node removeValue2(Node head, int num){
        while(head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while(cur != null){
            if(cur.value == num){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}

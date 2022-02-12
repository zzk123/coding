package com.zzk.algorithm.labuladong;

/**
 * @program: coding
 * @description: 链表反转相关
 * @author: zzk
 * @create: 2021-12-19 21:21
 */
public class ReverseStudy {

    /**
     * 链表节点定义
     */
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }



    /**
     * 反转链表前N个节点
     */
    public static ListNode successor = null;
    public static ListNode reverseN(ListNode head, int n){
        if(n == 1){
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n -1 );
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * 反转链表区间 m - n 的节点
     */
    public static ListNode reverseBetween(ListNode head, int m, int n){
        if(m == 1){
            ListNode node = reverseN(head, n);
            System.out.println(node);
            return node;
        }
        head.next = reverseBetween(head.next, m-1, n-1);
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode write = head;
        for(int i=2; i<13; i++){
            write.next = new ListNode(i);
            write =  write.next;
        }
        System.out.println(head);
        System.out.println(reverseBetween(head, 5, 10));
    }

    public static void main2(String[] args) {
        ListNode head = new ListNode(1);
        ListNode write = head;
        for(int i=2; i<7; i++){
            write.next = new ListNode(i);
            write =  write.next;
        }
        System.out.println(head);
        ListNode newNode = reverseN(head, 4);
        System.out.println(newNode);
    }
}

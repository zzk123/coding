package com.zzk.coding.list;

/**
 * @program: coding
 * @description: 3.删除链表的中间节点和a/b处的节点
 * @author: zzk
 * @create: 2020-09-16 22:06
 */
public class RemoveMidNod {

    /**
     * 节点对象
     */
    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 删除链表的中间节点
     * @param head
     * @return
     */
    public Node removeMidNode(Node head){
        if(head == null || head.next == null){
            return head;
        }
        if(head.next.next == null){
            return head.next;
        }
        Node pre = head;
        Node cur = head.next.next;
        while(cur.next != null && cur.next.next != null){
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    /**
     * 删除 a/b处节点函数
     * @param head
     * @param a
     * @param b
     * @return
     */
    public Node removeByRatio(Node head, int a, int b){
        if(a < 1 || a > b){
            return head;
        }
        int n = 0;
        Node cur = head;
        while(cur != null){
            n++;
            cur = cur.next;
        }
        n = (int) Math.ceil((double) (a * n) / (double) b);
        if(n > 1){
            cur = head;
            while(--n != 1){
                cur =cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}

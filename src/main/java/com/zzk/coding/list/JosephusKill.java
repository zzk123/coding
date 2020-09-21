package com.zzk.coding.list;

/**
 * @program: coding
 * @description: 6.环形单链表的约瑟夫问题
 * @author: zzk
 * @create: 2020-09-16 23:36
 */
public class JosephusKill {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    public Node josephusKill(Node head, int m){
        if(head == null || head.next == head || m < 1){
            return head;
        }

        Node last = head;
        while(last.next != head){
            last = last.next;
        }
        int count = 0;
        while(head != last){
            if(++count == m){
                //删除操作
                last.next = head.next;
                count = 0;
            }else{
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    /**
     * 如果链表节点数为N，要求时间复杂度为O(N)
     * @param head
     * @param m
     * @return
     */
    public Node josephusKill2(Node head, int m){
        if(head == null || head.next == head || m < 1){
            return head;
        }
        Node cur = head.next;
        int temp = 1;
        while(cur != head){
            temp++;
            cur = cur.next;
        }
        temp = getLive(temp, m);
        while (--temp != 0){
            head = head.next;
        }
        head.next = head;
        return head;
    }

    /**
     * 单决策获取对应的节点
     * @param i
     * @param m
     * @return
     */
    public int getLive(int i, int m){
        if(i == 1){
            return 1;
        }
        return (getLive(i-1, m) + m - 1) % i + 1;
    }
}

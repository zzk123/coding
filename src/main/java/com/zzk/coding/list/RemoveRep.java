package com.zzk.coding.list;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @program: coding
 * @description: 13.删除无序单链表中值重复出现的节点
 * @author: zzk
 * @create: 2020-09-22 22:14
 */
public class RemoveRep {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 利用哈希表，时间复杂度为O(N)，空间复杂度为O(N)
     * @param head
     */
    public void removeReq1(Node head){
        if(head == null){
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.value);
        while(cur != null){
            if(set.contains(cur.value)){
                pre.next = cur.next;
            }else{
                set.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
    }

    /**
     * 时间复杂度为O(N^2)，额外空间复杂度为O(1)
     * @param head
     */
    public void removeRep2(Node head){
        Node cur = head;
        Node pre = null;
        Node next = null;
        while(cur != null){
            pre = null;
            next = cur.next;
            while(next != null){
                if(cur.value == next.value){
                    pre.next = next.next;
                }else{
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
    }
}

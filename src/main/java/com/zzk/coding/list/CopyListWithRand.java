package com.zzk.coding.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2020-09-18 00:18
 */
public class CopyListWithRand {

    public class Node{
        public int value;
        public Node next;
        public Node rand;

        public Node(int data){
            this.value = value;
        }
    }

    /**
     * 用hashMap来复制，哈希表的增删改查的操作时间复杂度都是O(1)
     * 遍历链表2次，时间复杂度为O(N)，用hashMap保存对应关系，空间复杂度为O(B)
     * @param head
     * @return
     */
    public Node CopyListWithRand1(Node head){
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while(cur != head){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 时间复杂度为O(N)完成
     * @param head
     * @return
     */
    public Node copyListWithRand2(Node head){
        if(head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        //复制并链接每个节点
        while(cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        //拆分
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}

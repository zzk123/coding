package com.zzk.coding.list;

/**
 * @program: coding
 * @description: 16. 单链表的选择排序
 * @author: zzk
 * @create: 2020-09-23 23:06
 */
public class SelectionSort {

    /**
     * 节点定义
     */
    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }


    /**
     * 如果链表长度为N，时间复杂度为O(N^2)，额外空间复杂度为O(1)
     * @param head
     * @return
     */
    public Node selectionSort(Node head){
        Node tail = null;     //排序部分尾部
        Node cur = head;      //未排序部分头部
        Node smallPre = null; //最小节点的前一节点
        Node small = null;    //最小的节点
        while(cur != null){
            small = null;
            smallPre = getSmallestPreNode(cur);
            if(smallPre != null){
                small = smallPre.next;
                smallPre.next = small.next;
            }
            cur = cur == small ? cur.next : cur;
            if(tail == null){
                head = small;
            }else{
                tail.next = small;
            }
            tail = small;
        }
        return head;
    }

    /**
     * 查找最小值
     * @param head
     * @return
     */
    public Node getSmallestPreNode(Node head){
        Node smallPre = null;
        Node small = head;
        Node pre = head;
        Node cur = head.next;
        while(cur != null){
            if(cur.value < small.value){
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }
}

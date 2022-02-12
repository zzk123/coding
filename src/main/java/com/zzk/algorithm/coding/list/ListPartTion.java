package com.zzk.algorithm.coding.list;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2020-09-17 23:28
 */
public class ListPartTion {

    public class Node{

        public int value;

        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 将单向链表按某值划分成左边小，中间相等，右边大的形式
     * @param head
     * @param pivot
     * @return
     */
    public Node listParttion1(Node head, int pivot){
        if(head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while(cur != null){
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[1];
        i = 0;
        cur = head;
        for(i = 0; i != nodeArr.length; i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);

        for(i = 1; i != nodeArr.length; i++){
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    /**
     * 排序数组
     * @param nodeArr
     * @param pivot
     */
    public void arrPartition(Node[] nodeArr, int pivot){
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while(index != big){
            if(nodeArr[index].value < pivot){
                swap(nodeArr, ++small, index++);
            }else if(nodeArr[index].value == pivot){
                index++;
            }else {
                swap(nodeArr, --big, index);
            }
        }
    }

    /**
     * 交换两个数
     * @param nodeArr
     * @param a
     * @param b
     */
    public void swap(Node[] nodeArr, int a, int b){
        Node temp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = temp;
    }


    public Node listParttion2(Node head, int pivot){
        Node sH = null; //小的头
        Node sT = null; //小的尾
        Node eH = null; //相等的头
        Node eT = null; //相等的尾
        Node bH = null; //大的头
        Node bT = null; //大的尾
        Node next = null; //保存下一个节点
        //所有的节点分进三个链表中
        while(head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else{
                    eT.next = head;
                    eT = head;
                }
            }else {
                if(bH == null){
                    bH = head;
                    bT = head;
                }else{
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        //小的和相等的重新连接
        if(sT != null){
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        //所有的重新连接
        if(eT != null){
            eT.next = bH;
        }

        return sH != null ? sH : eH != null ? eH : bH;
    }
}

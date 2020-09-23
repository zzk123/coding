package com.zzk.coding.list;

/**
 * @program: coding
 * @description: 17.一种怪异的节点删除方式
 * @author: zzk
 * @create: 2020-09-23 23:21
 */
public class RemoveNodeWirte {

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
     * 删除某个节点
     * 时间复杂度为O(1)
     * @param node
     */
    public void removeNodeWirted(Node node){
        if(node == null){
            return;
        }
        Node next = node.next;
        if(node == null){
            throw new RuntimeException("can not reomve last node");
        }
        node.value = next.value;
        node.next = next.next;
    }
}

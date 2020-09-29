package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description: 5.遍历二叉树的神级方法 - morris算法
 * @author: zzk
 * https://zhuanlan.zhihu.com/p/101321696
 * @create: 2020-09-28 00:14
 */

public class MorrisTree {

    /**
     * 节点定义
     */
    public class Node{

        public int value;

        public Node left;

        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * Morris中序遍历
     * @param head
     */
    public void morrisIn(Node head){
        if(head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right !=cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.right;
                    continue;
                }else{
                    cur2.right = null;
                }
            }
            System.out.println(cur1.value + " ");
            cur1 = cur1.right;
        }
        System.out.println();
    }

    /**
     * Morris 先序遍历
     * @param head
     */
    public void morrisPrs(Node head){
        if(head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    System.out.println(cur1.value + " ");
                    cur1 = cur1.left;
                    continue;
                }else{
                    cur2.right = null;
                }
            }else{
                System.out.println(cur1.value + " ");
            }
            cur1 = cur1.right;
        }
        System.out.println();
    }

    /**
     * 后序遍历
     * @param head
     */
    public void morrisPos(Node head){
        if(head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else{
                    cur2.right = null;

                }
            }
        }
    }

    public void printEdge(Node head){
        Node tail = reverseEdge(head);
        Node cur = tail;
        while(cur != null){
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public Node reverseEdge(Node from){
        Node pre = null;
        Node next = null;
        while(from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }
}

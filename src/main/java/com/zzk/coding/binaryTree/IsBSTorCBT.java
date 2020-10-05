package com.zzk.coding.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: coding
 * @description:  15.判断一棵二叉树是否为搜索二叉树和完全二叉树
 * @author: zzk
 * @create: 2020-10-05 16:37
 */
public class IsBSTorCBT {

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
     * 判断是否为搜索二叉树，使用Morris遍历
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param head
     * @return
     */
    public boolean isBST(Node head){
        if(head == null){
            return true;
        }
        boolean res = true;
        Node pre = null;
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
            if(pre != null && pre.value > cur1.value){
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }


    /**
     * 判断是否是完全二叉树
     * 1.按层遍历二叉树，从每层的左边向右边依次遍历所有的节点
     * 2.如果当前节点有右孩子，但没有左孩子，直接返回false
     * 3.如果当前节点并不是左右孩子全有，那之后的节点必须都为叶节点，否则返回false
     * 4.遍历过程中如果不返回false，遍历结束后返回true
     * @param head
     * @return
     */
    public boolean isCBT(Node head){
        if(head == null){
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            if((leaf && (l != null || r != null)) || (l == null && r != null)){
                return false;
            }
            if(l != null){
                queue.offer(l);
            }
            if(r != null){
                queue.offer(r);
            }else{
                leaf = true;
            }
        }
        return true;
    }
}

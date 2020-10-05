package com.zzk.coding.binaryTree;

import java.util.Stack;

/**
 * @program: coding
 * @description: 10.调整搜索二叉树中两个错误的节点
 * @author: zzk
 * @create: 2020-10-04 09:20
 */
public class GetTwoErrNodes {

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
     * 查找两个错误的节点 - 原问题
     * @param head
     * @return
     */
    public Node[] getTwoErrNodes(Node head){
        Node[] errs = new Node[2];
        if(head == null){
            return errs;
        }
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while(!stack.isEmpty() || head != null){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                if(pre != null && pre.value > head.value){
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
    }

    /*
     * ===============查找两个错误的节点 - 进阶问题
     */
    /**
     * 查找两个错误的节点的父结点
     * @param head
     * @param e1
     * @param e2
     * @return
     */
    public Node[] getTwoErrParents(Node head, Node e1, Node e2){
        Node[] parents = new Node[2];
        if(head == null){
            return parents;
        }
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || head != null){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                if(head.left == e1 || head.right == e1){
                    parents[0] = head;
                }
                if(head.left == e2 || head.right == e2){
                    parents[1] = head;
                }
                head = head.right;
            }
        }
        return parents;
    }

    /**
     * 
     * @param head
     * @return
     */
    public Node recoverTree(Node head){
        Node[] errs = getTwoErrNodes(head);
        Node[] parents = getTwoErrParents(head, errs[0], errs[1]);
        Node e1 = errs[0];
        Node e1P = parents[0];
        Node e1L = e1.left;
        Node e1R = e1.right;
        Node e2 = errs[1];
        Node e2P = parents[1];
        Node e2L = e2.left;
        Node e2R = e2.right;
        if(e1 == head){
            if(e1 == e2P){ // 情况1
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            }else if(e2P.left == e2){ //情况2
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }else{ //情况3
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        }else if(e2 == head){
            if(e2 == e1P){//情况4
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            }else if(e1P.left == e1){ //情况5
                e1P.left = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }else{//情况6
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }
            head = e1;
        }else{
            if(e1 == e2){
                if(e1P.left == e1){//情况7
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }else{//情况8
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            }else if(e2 == e1P){
                if(e2P.left == e2){ // 情况9
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }else{ //情况10
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            }else{
                if (e1P.left == e1) {
                    if(e2P.left == e2){ //情况11
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.left = e1;
                    }else{ //情况12
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.right = e1;
                    }
                }else{
                    if(e2P.left == e2){ //情况13
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.left = e1;
                    }else{ //情况14
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.right = e1;
                    }
                }
            }
        }
        return head;
    }
}

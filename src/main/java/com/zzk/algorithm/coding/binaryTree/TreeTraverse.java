package com.zzk.algorithm.coding.binaryTree;

import java.util.Stack;

/**
 * @program: coding
 * @description: 1.分别用递归和非递归方式实现二叉树先序、中序和后序遍历
 * @author: zzk
 * @create: 2020-09-24 23:28
 */
public class TreeTraverse {

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
     * 递归 - 先序遍历
     * @param head
     */
    public void preOrderRecur(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 递归 - 中序遍历
     * @param head
     */
    public void inOrderRecur(Node head){
        if(head == null){
            return;
        }
        inOrderRecur(head.right);
        System.out.println(head.value + " ");
        inOrderRecur(head.left);
    }

    /**
     * 递归 - 后序遍历
     * @param head
     */
    public void posOrderRecur(Node head){
        if(head == null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.value + " ");
    }


    /**
     * 非递归 - 先序遍历
     * @param head
     */
    public void preOrderUnRecur(Node head){
        System.out.println("pre-order:");
        if(head == null){
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value + " ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }


    /**
     * 非递归 - 中序遍历
     * @param head
     */
    public void inOrderUnRecur(Node head){
        System.out.println("in-order:");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
    }

    /**
     * 非递归 - 后序遍历
     * @param head
     */
    public void posOrderUnRecur1(Node head){
        System.out.println("pos-order:");
        if(head != null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while(!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if(head.left != null){
                    s1.push(head.left);
                }
                if(head.right != null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.println(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 非递归 - 后序遍历
     * @param h
     */
    public void posOrderUnRecur2(Node h){
        System.out.println("pos-order:");
        if(h != null){
            Stack<Node> stack = new Stack<>();
            stack.push(h);
            Node c = null;
            while(!stack.isEmpty()){
                c = stack.peek();
                if(c.left != null && h != c.left && h != c.right){
                    stack.push(c.left);
                }else if(c.right != null && h != c.right){
                    stack.push(c.right);
                }else{
                    System.out.println(stack.pop().value + " ");
                    h = c;
                }
            }
        }
    }
}

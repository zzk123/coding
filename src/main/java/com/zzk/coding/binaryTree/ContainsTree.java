package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description: 11.判断t1树是否包含t2树全部的拓扑结构
 * @author: zzk
 * @create: 2020-10-04 22:32
 */
public class ContainsTree {

    public class Node{

        public int value;

        public Node left;

        public Node right;

        /**
         * 如果t1节点数为N，t2节点数为M，时间复杂度为O(N*M)
         * @param t1
         * @param t2
         * @return
         */
        public boolean contains(Node t1, Node t2){
            return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
        }

        public boolean check(Node h, Node t2){
            if(t2 == null){
                return true;
            }
            if(h == null || h.value != t2.value){
                return false;
            }
            return check(h.left, t2.left) && check(h.right, t2.right);
        }
    }
}

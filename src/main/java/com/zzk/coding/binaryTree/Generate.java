package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description: 16.通过有序数组生成平衡搜索二叉树
 * @author: zzk
 * @create: 2020-10-05 16:56
 */
public class Generate {

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

    public Node generateTree(int[] sortArr){
        if(sortArr == null){
            return null;
        }
        return generate(sortArr, 0, sortArr.length - 1);
    }

    public Node generate(int[] sortArr, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (start + end) / 2;
        Node head = new Node(sortArr[mid]);
        head.left = generate(sortArr, start, mid - 1);
        head.right = generate(sortArr, mid + 1, end);
        return head;
    }
}

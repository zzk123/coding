package com.zzk.algorithm.coding.binaryTree;

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

    /**
     * 数组迭代生成节点，最中间的数生成头节点，左边的数生成左子树，右边的数生成右子树
     * @param sortArr
     * @return
     */
    public Node generateTree(int[] sortArr){
        if(sortArr == null){
            return null;
        }
        return generate(sortArr, 0, sortArr.length - 1);
    }

    /**
     * 迭代数组生成节点
     * @param sortArr
     * @param start
     * @param end
     * @return
     */
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

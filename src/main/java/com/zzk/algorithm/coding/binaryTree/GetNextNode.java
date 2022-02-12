package com.zzk.algorithm.coding.binaryTree;

/**
 * @program: coding
 * @description: 17.在二叉树中找到一个节点的后继节点
 * @author: zzk
 * @create: 2020-10-06 11:01
 */
public class GetNextNode {

    /**
     * 节点组成
     */
    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 在二叉树中找到一个节点的后继节点
     * 情况1：如果node没有右子树，那么后继节点就是右子树上最左边的节点
     * 情况2：如果node没有右子树，
     * 那么先看node是不是node父结点的左孩子，如果是，那么此时node的父结点就是node的后继节点；
     * 如果是右孩子，就向上寻找node的后继节点，假设向上移动到的节点记为s，s的父结点记为p，如果发现s是p的左孩子，那么节点p就是node节点的后继节点，否则一直向上移动
     * 情况3：如果在情况2中一直向上寻找，都移动到空节点时还是没有发现node的后继节点，说明node根本不存在后继节点
     *
     * 如果node节点和node后继节点之间的实际距离为L,最优解法只用走过L个节点，时间复杂度为O(L)，额外空间复杂度为O(1)
     * @param node
     * @return
     */
    public Node getNextNode(Node node){
        if(node == null){
            return node;
        }
        if(node.right != null){
            return getLeftMost(node.right);
        }else{
            Node parent = node.parent;
            while(parent != null && parent.left != null){
                node = node.left;
            }
        }
        return node;
    }

    /**
     * 查找左子树
     * @param node
     * @return
     */
    public Node getLeftMost(Node node){
        if(node == null){
            return node;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
}

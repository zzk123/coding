package com.zzk.algorithm.coding.binaryTree;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2020-10-05 15:47
 */
public class IsBalance {

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
     * 判断是否为平衡树
     * @param head
     * @return
     */
    public boolean isBalance(Node head){
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    /**
     * 迭代，比较左右子树的高度
     * @param head
     * @param level
     * @param res
     * @return
     */
    public int getHeight(Node head, int level, boolean[] res){
        if(head == null){
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);
        if(!res[0]){
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if(!res[0]){
            return level;
        }
        if(Math.abs(lH - rH) > 1){
            res[0] = false;
        }
        return Math.max(lH, rH);
    }
}

package com.zzk.algorithm.coding.binaryTree;

import java.util.HashMap;

/**
 * @program: coding
 * @description: 6.在二叉树中找到累加和为指定值的最长路径长度
 * @author: zzk
 * @create: 2020-10-02 17:24
 */
public class GetMaxLength {

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
     * 最大长度计算
     * 假设二叉树的节点数为N，则时间复杂度为O(N)，额外空间复杂度为O(h)，其中h二叉树的高度
     * @param head
     * @param sum
     * @return
     */
    public int getMaxLength(Node head, int sum){
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0); // 重要
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    /**
     * 计算最大长度
     * @param head
     * @param sum
     * @param preSum
     * @param level
     * @param maxLen
     * @param sumMap
     * @return
     */
    public int preOrder(Node head, int sum, int preSum, int level,
                        int maxLen, HashMap<Integer, Integer> sumMap){
        if(head == null){
            return maxLen;
        }
        int curSum = preSum + head.value;
        if(!sumMap.containsKey(curSum)){
            sumMap.put(curSum, level);
        }
        if(sumMap.containsKey(curSum-sum)){
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);
        //为了不影响其他分支
        if(level == sumMap.get(curSum)){
            sumMap.remove(curSum);
        }
        return maxLen;
    }
}

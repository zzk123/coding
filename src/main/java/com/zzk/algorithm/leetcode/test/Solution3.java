package com.zzk.algorithm.leetcode.test;

import java.util.*;
/**
 * @program: coding
 * @description: 广度优先
 * @author: zzk
 * @create: 2022-04-03 16:50
 */
public class Solution3 {

    static class  Node {
        /**
         * 字符串
         */
        private String res;

        /**
         * 剩余左括号
         */
        private int left;

        /**
         * 剩余右括号
         */
        private int right;

        public Node(String res, int left, int right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }
    }


    public static List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        if(n == 0){
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            if(curNode.left == 0 && curNode.right == 0){
                res.add(curNode.res);
            }
            if(curNode.left > 0){
                queue.offer(new Node(curNode.res + "(", curNode.left-1, curNode.right));
            }
            if(curNode.right > 0 && curNode.left < curNode.right){
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right-1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(generateParenthesis(n));
    }
}

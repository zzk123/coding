package com.zzk.algorithm.leetcode.test;

import java.util.*;

/**
 * @program: coding
 * @description: 回溯算法 - 组合
 * @author: zzk
 * @create: 2022-06-21 22:23
 */
public class Solution19 {

    public static List<List<Integer>> combine(int n, int k){
        List<List<Integer>> res = new ArrayList<>();
        if(k <= 0 || n < k){
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res){
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = begin; i <= n; i++){
            path.addLast(i);
            dfs(n, k, i+1, path, res);
            path.removeLast();
        }
    }

    /**
     * 优化后的剪枝
     * @param n
     * @param k
     * @param begin
     * @param path
     * @param res
     */
    private static void dfs2(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res){
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = begin; i <= n-( k - path.size()) + 1; i++){
            path.addLast(i);
            dfs(n, k, i+1, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int n = 5, k = 3;
        System.out.println(combine(n ,k));
    }
}

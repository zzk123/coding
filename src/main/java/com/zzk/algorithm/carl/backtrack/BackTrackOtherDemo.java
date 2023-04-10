package com.zzk.algorithm.carl.backtrack;

import java.util.*;

/**
 * 其他问题
 * 回溯算法
 */
public class BackTrackOtherDemo {

    /**
     * 1、递增子序列
     * 给你一个整数数组 `nums` ，找出并返回所有该数组中不同的递增子序列，递增子序列中 **至少有两个元素** 。
     * 你可以按 **任意顺序** 返回答案。
     * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
     *
     * 输入：nums = [4,6,7,7]
     * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     */
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums){
        backtracking(nums, 0);
        return res;
    }
    private void backtracking(int[] nums, int start){
        if(path.size() > 1){
            res.add(new ArrayList<>(path));
        }

        int[] used = new int[201];
        for(int i=start; i<nums.length; i++){
            if(!path.isEmpty()
                    && nums[i] < path.get(path.size()-1)
                    || (used[nums[i] + 100] == 1)) {
                continue;
            }
            used[nums[i]+100] = 1;
            path.add(nums[i]);
            backtracking(nums, i+1);
            path.remove(path.size()-1);
        }
    }
    //使用map
    //结果集合
    List<List<Integer>> res2 = new ArrayList<>();
    //路径集合
    LinkedList<Integer> path2 = new LinkedList<>();
    public List<List<Integer>> findSubsequences2(int[] nums){
        getSubsequences2(nums, 0);
        return res2;
    }

    private void getSubsequences2(int[] nums, int start){
        if(path2.size() > 1){
            res2.add(new ArrayList<>(path2));
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=start; i<nums.length; i++){
            if(!path2.isEmpty() && nums[i] < path2.getLast()){
                continue;
            }
            //同一层用过的数字跳过
            if(map.getOrDefault(nums[i], 0) >= 1){
                continue;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            path2.add(nums[i]);
            getSubsequences2(nums, i+1);
            path2.removeLast();
        }
    }

    /**
     * 2、重新安排行程
     * 给你一份航线列表 `tickets` ，其中 `tickets[i] = [fromi, toi]` 表示飞机出发和降落的机场地点。
     * 请你对该行程进行重新规划排序。
     * 所有这些机票都属于一个从 `JFK`（肯尼迪国际机场）出发的先生，所以该行程必须从 `JFK` 开始。
     * 如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
     * - 例如，行程 `["JFK", "LGA"]` 与 `["JFK", "LGB"]` 相比就更小，排序更靠前。
     * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
     *
     * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
     * 输出：["JFK","MUC","LHR","SFO","SJC"]
     */
    Deque<String> res3;
    Map<String, Map<String, Integer>> map;
    public List<String> findItinerary(List<List<String>> tickets){
        map = new HashMap<>();
        res3 = new LinkedList<>();
        for(List<String> t : tickets){
            Map<String, Integer> temp;
            if(map.containsKey(t.get(0))){
                temp = map.get(t.get(0));
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
            }else{
                temp = new TreeMap<>();
                temp.put(t.get(1), 1);
            }
            map.put(t.get(0), temp);
        }
        res3.add("JFK");
        backtracking3(tickets.size());
        return new ArrayList<>(res3);
    }

    public boolean backtracking3(int ticketNum){
        if(res3.size() == ticketNum + 1){
            return true;
        }
        String last = res3.getLast();
        if(map.containsKey(last)){
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    res3.add(target.getKey());
                    target.setValue(count-1);
                    if(backtracking3(ticketNum)){
                        return true;
                    }
                    res3.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }

    /**
     * 3、括号生成
     * 数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     */
    //1、深度优先遍历
    public List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        if(n == 0){
            return res;
        }
        dfs("", n, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, List<String> res){
        if(left == 0 && right == 0){
            res.add(curStr);
            return;
        }

        if(left > right){
            return;
        }

        if(left > 0){
            dfs(curStr + "(", left-1, right, res);
        }

        if(right > 0){
            dfs(curStr + ")", left, right-1, res);
        }
    }
    //2、深度优先遍历
    public List<String> generateParenthesis2(int n){
        List<String> res = new ArrayList<>();
        if(n == 0){
            return res;
        }
        dfs2("", 0, 0, n, res);
        return res;
    }

    private void dfs2(String curStr, int left, int right, int n, List<String> res){
        if(left == n && right == n){
            res.add(curStr);
            return;
        }

        if(left < right){
            return;
        }

        if(left < n){
            dfs2(curStr + "(", left+1, right, n, res);
        }

        if(right < n){
            dfs2(curStr + ")", left, right+1, n, res);
        }
    }

}

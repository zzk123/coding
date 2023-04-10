package com.zzk.algorithm.carl.backtrack;

import java.util.*;

/**
 * 回溯算法
 * 组合demo
 */
public class BackTrackCombinationDemo {

    /**
     * 1、组合
     * 给定两个整数 `n` 和 `k`，返回范围 `[1, n]` 中所有可能的 `k` 个数的组合。
     * 你可以按 **任何顺序** 返回答案。
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    public List<List<Integer>> combine(int n, int k){
        List<List<Integer>> res = new ArrayList<>();
        if(k <= 0 || n < k){
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res){
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        //减枝
        for(int i=begin; i<=n-(k-path.size())+1; i++){
            //处理节点
            path.addLast(i);
            //递归，下层搜素
            dfs(n, k, i+1, path, res);
            //回溯，撤回处理的节点
            path.removeLast();
        }
    }

    /**
     * 2、组合总和 III
     * 找出所有相加之和为 `n` 的 `k` 个数的组合，且满足下列条件：
     * - 只使用数字1到9
     * - 每个数字 **最多使用一次**
     * 返回 *所有可能的有效组合的列表* 。该列表不能包含相同的组合两次，组合可以以任何顺序返回
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     */
    public List<List<Integer>> combinationSum2(int k, int n){
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs2(n, k, 1, path, res);
        return res;
    }

    public void dfs2(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res){
        if(n < 0){
            return;
        }
        if(k == 0){
            if(n == 0){
                res.add(new ArrayList<>(path));
                return;
            }
            return;
        }
        for(int i=begin; i<=9; i++){
            path.addLast(i);
            dfs2(n-i, k-1, i+1, path, res);
            path.removeLast();
        }
    }

    /**
     * 3、电话号码的字母组合
     * 给定一个仅包含数字 `2-9` 的字符串，返回所有它能表示的字母组合。答案可以按 **任意顺序** 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */
    List<String> list = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    public List<String> letterCombinations(String digits){
        if(digits == null || digits.length() == 0){
            return list;
        }
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTracking(digits, numString, 0);
        return list;
    }

    private void backTracking(String digits, String[] numString, int num){
        if(num == digits.length()){
            list.add(temp.toString());
            return;
        }

        String str = numString[digits.charAt(num) - '0'];
        for(int i=0; i<str.length(); i++){
            temp.append(str.charAt(i));
            backTracking(digits, numString, num+1);
            temp.deleteCharAt(temp.length()-1);
        }
    }

    /**
     * 4、组合总和
     * 给你一个 **无重复元素** 的整数数组 `candidates` 和一个目标整数 `target` ，找出 `candidates`
     * 中可以使数字和为目标数 `target` 的 *所有* **不同组合** ，并以列表形式返回。你可以按 **任意顺序** 返回这些组合。
     *
     * `candidates` 中的 **同一个** 数字可以 **无限制重复被选取** 。
     * 如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 `target` 的不同组合数少于 `150` 个。
     *
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len == 0){
            return res;
        }
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs3(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs3(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = begin; i < len; i++){
            if(target - candidates[i] < 0){
                break;
            }

            path.addLast(candidates[i]);
            //不用 i+1，表示可以重复读取当前的数
            dfs3(candidates, i, len, target-candidates[i], path, res);
            path.removeLast();
        }
    }

    /**
     * 5、组合总和 II
     * 给定一个候选人编号的集合 `candidates` 和一个目标数 `target` ，找出 `candidates`
     * 中所有可以使数字和为 `target` 的组合。
     * `candidates` 中的每个数字在每个组合中只能使用 **一次** 。
     * **注意：**解集不能包含重复的组合。
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     */
    public List<List<Integer>> combinationSum2(int[] candiates, int target){
        int len = candiates.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len == 0){
            return res;
        }
        Arrays.sort(candiates);

        Deque<Integer> path = new ArrayDeque<>();
        dfs4(candiates, len, 0, target, path, res);
        return res;
    }

    private void dfs4(int[] candidates, int len, int begin, int target, Deque<Integer> path, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin; i < len; i++){
            if(target - candidates[i] < 0){
                break;
            }
            //同一层相同数值的节点跳过
            if(i > begin && candidates[i] == candidates[i-1]){
                continue;
            }
            path.addLast(candidates[i]);
            dfs4(candidates, len, i+1, target-candidates[i], path, res);
            path.removeLast();
        }
    }
}

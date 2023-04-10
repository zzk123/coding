package com.zzk.algorithm.carl.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排序
 * 回溯算法
 */
public class BackTrackSortDemo {

    /**
     * 1、全排列
     * 给定一个不含重复数字的数组 `nums` ，返回其 *所有可能的全排列* 。
     * 你可以 **按任意顺序** 返回答案
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    //1、使用 path 来判断
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums){
        if(nums.length == 0){
            return result;
        }
        backtrack(nums, path);
        return result;
    }
    public void backtrack(int[] nums, LinkedList<Integer> path){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
        }
        for(int i=0; i<nums.length; i++){
            if(path.contains(nums[i])){
                continue;
            }
            path.add(nums[i]);
            backtrack(nums, path);
            path.removeLast();
        }
    }
    //2、使用 used 来判断是否已经排序过
    List<List<Integer>> result1 = new ArrayList<>();
    LinkedList<Integer> path1 = new LinkedList<>();
    //而used数组，其实就是记录此时path里都有哪些元素使用了，一个排列里一个元素只能使用一次
    boolean[] used;
    public List<List<Integer>> permute1(int[] nums){
        if(nums.length == 0){
            return result1;
        }
        used = new boolean[nums.length];
        permuteHelper(nums);
        return result1;
    }
    private void permuteHelper(int[] nums){
        if(path.size() == nums.length){
            result1.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(used[i]){
                continue;
            }

            used[i] = true;
            path.add(nums[i]);

            permuteHelper(nums);

            used[i] = false;
            path.removeLast();
        }
    }
    /**
     * 2、全排列 II
     * 给定一个可包含重复数字的序列 `nums` ，***按任意顺序*** 返回所有不重复的全排列。
     *
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     */
    List<List<Integer>> result2 = new ArrayList<>();
    List<Integer> path2 = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums){
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backtrack2(nums, used);
        return result2;
    }

    private void backtrack2(int[] nums, boolean[] used){
        if(path2.size() == nums.length){
            result2.add(new ArrayList<>(path2));
            return;
        }

        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i] == nums[i-1] && used[i-1] == false){
                continue;
            }
            if(used[i] == false){
                used[i] = true;
                path2.add(nums[i]);
                backtrack2(nums, used);

                path2.remove(path2.size()-1);
                used[i] = false;
            }
        }
    }
}

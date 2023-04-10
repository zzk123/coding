package com.zzk.algorithm.carl.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法
 * 子集demo
 */
public class BackTrackChildDemo {

    /**
     * 1、子集
     * 给你一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。
     * 解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集
     *
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums){
        int len = nums.length;
        res = new ArrayList<>();
        if(len == 0){
            return res;
        }
        List<Integer> pre = new ArrayList<>();
        find(nums, 0, pre);
        return res;
    }

    private void find(int[] nums, int begin, List<Integer> pre){
        res.add(new ArrayList<>(pre));
        for(int i=begin; i<nums.length; i++){
            pre.add(nums[i]);
            find(nums, i+1, pre);
            pre.remove(pre.size()-1);
        }
    }

    /**
     * 2、子集 II
     * 给你一个整数数组 `nums` ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 **不能** 包含重复的子集。返回的解集中，子集可以按 **任意顺序** 排列。
     *
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     */
    List<List<Integer>> res1 = new ArrayList<>();
    LinkedList<Integer> path1 = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums){
        Arrays.sort(nums);
        subsetsWithDupHelper(nums, 0);
        return res1;
    }

    private void subsetsWithDupHelper(int[] nums, int start){
        res.add(new ArrayList<>(path1));
        for(int i=start; i<nums.length; i++){
            //跳过当前层用的相同的元素
            if(i>start && nums[i-1] == nums[i]){
                continue;
            }
            path1.add(nums[i]);
            subsetsWithDupHelper(nums, i+1);
            path1.removeLast();
        }
    }
}

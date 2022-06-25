package com.zzk.algorithm.leetcode.test;

import org.junit.Test;

import java.util.*;

/**
 * @program: coding
 * @description: 全排序2
 * @author: zzk
 * @create: 2022-06-25 14:39
 */
public class Solution20 {

    public List<List<Integer>> result = new ArrayList<>();

    public List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums){
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backTrack(nums, used);
        return result;
    }

    private void backTrack(int[] nums, boolean[] used){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i] == nums[i-1] && used[i-1] == false){
                continue;
            }
            if(used[i] == false){
                used[i] = true;
                path.add(nums[i]);
                backTrack(nums, used);
                path.remove(path.size()-1);
                used[i] = false;
            }
        }
    }
    @Test
    public void testPermuteUnique(){
        int[] nums = new int[] {1,2,3,4};
        System.out.println(permuteUnique(nums));
    }
}

package com.zzk.algorithm.leetcode.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-29 22:55
 */
public class Solution {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = 0;
        int max = nums[0];
        int[] result = new int[nums.length-k+1];
        int j =0;
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], i);
            len++;
            max = Math.max(nums[i], max);
            if(len == k){
                result[j] = max;
                j++;
                if(map.get(max) != null && map.get(max)  == i-len+1){
                    max = getMaxValue(map);
                    map.remove(max);
                    max = getMaxValue(map);
                }else{
                    if(map.size() == k) {
                        map.remove(nums[i - len + 1]);
                    }
                }
                len--;
            }
        }
        return result;
    }


    public static int getMaxValue(Map<Integer, Integer> map){
        Integer max = Integer.MIN_VALUE;
        for(Integer key : map.keySet()){
            max = Math.max(key, max);
        }
        return max;
    }

    public static void main(String[] args) {
        /*int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;*/
        /*int[] nums = {1,-1};
        int k = 1;*/
        /*int[] nums = {7, 2, 4};
        int k = 2;*/
        /*int[] nums = {1,3,1,2,0,5};
        int k = 3;*/
        int[] nums = {9,10,9,-7,-4,-8,2,-6};
        int k = 5;
        maxSlidingWindow(nums, k);
    }
}
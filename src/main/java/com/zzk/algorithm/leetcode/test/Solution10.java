package com.zzk.algorithm.leetcode.test;

import com.google.gson.Gson;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-05-06 21:59
 */
public class Solution10 {

    public static int[] searchRange(int[] nums, int target){
        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);
        // 情况一
        if (leftBorder == -2 || rightBorder == -2) return new int[]{-1, -1};
        // 情况三
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        // 情况二
        return new int[]{-1, -1};
    }

    public static int getRightBorder(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -2; // 记录一下rightBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] > target) {
                right = middle - 1;
            } else { // 寻找右边界，nums[middle] == target的时候更新left
                left = middle + 1;
                rightBorder = left;
            }
        }
        return rightBorder;
    }

    public static int getLeftBorder(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -2;
        while(left <= right){
            int mid = left + ((right - left) / 2);
            if(nums[mid] >= target){ // 寻找左边界，nums[middle] == target的时候更新right
                right = mid - 1;
                leftBorder = right;
            }else{
                left = mid + 1;
            }
        }
        return leftBorder;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        System.out.println(new Gson().toJson(searchRange(nums, target)));
    }
}

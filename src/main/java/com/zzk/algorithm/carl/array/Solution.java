package com.zzk.algorithm.carl.array;

/**
 * 搜索
 */
public class Solution {

    /**
     * 1、二分查找
     * 给定一个 `n` 个元素有序的（升序）整型数组 `nums` 和一个目标值 `target` ，写一个函数搜索 `nums` 中的 `target`，如果目标值存在返回下标，否则返回 `-1`
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target){
        if(target < nums[0] || target > nums[nums.length - 1]){
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 2、搜索插入位置
     * 给定一个排序数组和一个目标值，在数 组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target){
        if(target < nums[0]){
            return 0;
        }

        if(target > nums[nums.length-1]){
            return nums.length;
        }

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while(left <= right){
            mid = left + ((right - left) >> 1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        return right + 1;
    }
    /**
     * 3、在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 `nums`，和一个目标值 `target`。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 `target`，返回 `[-1, -1]`。
     */
   public int[] searchRange(int[] nums, int target){
       int index = binarySearch(nums, target);

       if(index == -1){
           return new int[]{-1, -1};
       }
       int left = index;
       int right = index;
       while(left - 1 >= 0 && nums[left - 1] == nums[index]){
           left--;
       }
       while(right + 1 < nums.length && nums[right + 1] == nums[index]){
           right++;
       }
       return new int[]{left, right};
   }

    /**
     * 二分法查找值
     * @param nums
     * @param target
     * @return
     */
   public int binarySearch(int[] nums, int target){
       int left = 0;
       int right = nums.length - 1;
       while(left <= right){
           int mid = left + (right - left) / 2;
           if(nums[mid] == target){
               return mid;
           }else if(nums[mid] < target){
               left = mid + 1;
           }else{
               right = mid + 1;
           }
       }
       return -1;
   }

    /**
     * 4、x 的平方根
     * 给你一个非负整数 `x` ，计算并返回 `x` 的算术平方根
     * 时间 O(logn)
     * 空间 O(1)
     */
   public int mySqrt(int x){
       if(x == 0){
           return 0;
       }
       if(x == 1){
           return 1;
       }
       int left = 0, right = x, ans = -1;
       while(left <= right){
           int mid = left + (right - left) / 2;
           if((long) mid * mid <= x){
               left = mid + 1;
               ans = mid;
           }else{
               right = mid - 1;
           }
       }
       return ans;
   }


    /**
     * 5、有效的完全平方数
     * 给定一个 **正整数** `num` ，编写一个函数，如果 `num` 是一个完全平方数，则返回 `true` ，否则返回 `false`
     * @param num
     * @return
     */
   public boolean isPerfectSquare(int num){
       int left = 0, right = num;
       while(left <= right){
           int mid = left + (right - left) / 2;
           long midValue = (long) mid * mid;
           if(midValue == num){
               return true;
           }else if(midValue < num){
               left = mid + 1;
           }else{
               right = mid - 1;
           }
       }
       return false;
   }
}

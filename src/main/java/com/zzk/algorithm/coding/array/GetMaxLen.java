package com.zzk.algorithm.coding.array;

/**
 * @ClassName GetMaxLen
 * @Description 10.未排序正数数组中累加和为给定值的最长子数组长度
 * @Author zzk
 * @Date 2021/1/4 23:55
 **/
public class GetMaxLen {

    /**
     * 时间复杂度为O(N)，额外空间复杂度为O(1)
     * @param arr
     * @param k
     * @return
     */
    public int getMaxLength(int[] arr, int k){
        if(arr == null || arr.length == 0 || k <= 0){
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while(right < arr.length){
            if(sum == k){
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            }else if(sum < k){
                right++;
                if(right == arr.length){
                    break;
                }
                sum += arr[right];
            }else{
                sum -= arr[left++];
            }
        }
        return len;
    }
}

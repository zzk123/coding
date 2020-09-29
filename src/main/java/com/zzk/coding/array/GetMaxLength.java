package com.zzk.coding.array;

/**
 * @program: coding
 * @description: 10.未排序正数数组中累加和为给定值的最长子数组长度
 * @author: zzk
 * @create: 2020-09-29 22:51
 */
public class GetMaxLength {

    /**
     * 时间复杂度为O(N),空间复杂度为O(1)
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
        return sum;
    }
}

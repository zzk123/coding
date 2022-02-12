package com.zzk.algorithm.coding.array;

/**
 * @ClassName GetLessMaxLength
 * @Description 12.未排序数组中累加和小于或者等于给定值的最长子数组长度
 * @Author zzk
 * @Date 2021/1/11 23:17
 **/
public class GetLessMaxLength {

    /**
     * 时间复杂度为O(NlogN)，空间复杂度为O(N)
     * @param arr
     * @param k
     * @return
     */
    public int maxLength(int[] arr,int k){
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for(int i = 0; i != arr.length; i++){
            sum += arr[i];
            h[i+1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for(int i = 0; i != arr.length; i++){
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public int getLessIndex(int[] arr, int num){
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while(low <= high){
            mid = (low + high) / 2;
            if(arr[mid] >= num){
                res = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return res;
    }
}

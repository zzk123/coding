package com.zzk.coding.array;

/**
 * @ClassName GetLessIndex
 * @Description 18.在数组中找到一个局部最小的位置
 * @Author zzk
 * @Date 2021/2/1 22:01
 **/
public class GetLessIndex {

    /**
     * 二分查找法
     * 时间复杂度为O(logN)，空间复杂度为O(1)
     * @param arr
     * @return
     */
    public int getLessIndex(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;//不存在
        }
        if(arr.length == 1 || arr[0] < arr[1]){
            return 0;
        }
        if(arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while(left < right){
            mid = (left + right)/2;
            if(arr[mid] > arr[mid - 1]){
                right = mid - 1;
            }else if(arr[mid] > arr[mid + 1]){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return  left;
    }
}

package com.zzk.coding.array;

/**
 * @ClassName Modify
 * @Description 15.奇数下标都是奇数或者偶数下标都是偶数
 * @Author zzk
 * @Date 2021/1/25 21:56
 **/
public class Modify {

    /**
     * 时间复杂度为O(N)，额外空间复杂度为O(1)
     * @param arr
     */
    public void modify(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int event = 0;
        int odd = 1;
        int end = arr.length - 1;
        while(event <= end && odd <= end){
            if((arr[end] & 1) == 0){
                swap(arr, end, event);
                event += 2;
            }else{
               swap(arr, end, odd);
               odd += 2;
            }
        }
    }

    public void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}

package com.zzk.algorithm.coding.array;

/**
 * @ClassName Sort
 * @Description 14.自然数数组的排序
 * @Author zzk
 * @Date 2021/1/17 23:03
 **/
public class Sort {

    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param arr
     */
    public void sort1(int[] arr){
        int tmp = 0;
        int next = 0;
        for(int i = 0; i != arr.length; i++){
            tmp = arr[i];
            while(arr[i] != i+1){
                next = arr[tmp - 1];
                arr[tmp - 1] = tmp;
                tmp = next;
            }
        }
    }


    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param arr
     */
    public void sort2(int[] arr){
        int tmp = 0;
        for(int i = 0; i != arr.length; i++){
            while(arr[i] != i+1){
                tmp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}

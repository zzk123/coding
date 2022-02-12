package com.zzk.algorithm.sort;

/**
 * @ClassName BinaryInsertion
 * @Description 折半插入排序
 * @Author zzk
 * @Date 2021/4/1 0:13
 **/
public class BinaryInsertion extends BaseSort {

    /**
     * 折半插入排序
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int low, high, m;
        Comparable temp;
        for(int i = 1; i < arr.length; i++){
            //折半查找应该插入的位置
            low = 0;
            high = i - 1;
            while(low <= high){
                m = (low + high) / 2;
                if(less(arr[i], arr[m])){
                    high = m - 1;
                }else{
                    low = m + 1;
                }
            }
            //统一移动元素，然后将这个元素插入到正确的位置
            temp = arr[i];
            for(int j = i; j > high + 1; j--){
                arr[j] = arr[j-1];
            }
            arr[high + 1] = temp;
        }
    }
}

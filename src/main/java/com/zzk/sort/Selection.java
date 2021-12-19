package com.zzk.sort;

/**
 * @ClassName Selection
 * @Description 选择排序
 * @Author zzk
 * @Date 2021/3/31 23:53
 **/
public class Selection extends BaseSort {

    /**
     * 用了N次交换 - 交换次数和数组的大小是线性排序的
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int N = arr.length;
        for(int i = 0; i < N; i++){
            int min = i;
            //查找最小的
            for(int j = i + 1; j < N; j++){
                if(less(arr[j], arr[min])){
                    min = j;
                }
            }
            //交换元素
            exch(arr, i, min);
        }
    }
}

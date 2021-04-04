package com.zzk.sort;

/**
 * @program: alogrithms
 * @description: 冒泡排序
 * @author: zzk
 * @create: 2021-04-01
 */
public class BubbleSort extends BaseSort {

    /**
     * 冒泡排序 - 最优 O(n)，最差O(n^2)
     * @param arr
     */
    public static void sort(Integer[] arr){
        if(arr.length <= 1){
            return;
        }

        boolean flag = false;
        int n = arr.length;
        //n个元素，n次冒泡
        for(int i = 0; i < n; i++){
            flag = false;
            //每次冒泡比较交换元素
            for(int j = 0; j < n-i-1; j++){
                if(less(arr[j+1], arr[j])){
                    exch(arr, j, j+1);
                    flag = true;
                }
            }
            //一次冒泡结束，检查是否发生数据交换
            //如果没有，表示已经有序，不需要继续冒泡
            if(!flag){
                break;
            }
        }
    }
}

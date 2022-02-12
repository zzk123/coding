package com.zzk.algorithm.sort;

/**
 * @program: alogrithms
 * @description: 基数排序 - 桶排序
 * @author: zzk
 * @create: 2021-04-01
 */
public class RadixSort extends BaseSort {

    /**
     * 基数排序 - 桶排序
     * 时间复杂度 O(k*N) k指排序数中的最大数的位数
     * @param arr
     */
    public static void sort(Integer[] arr){
        //最大值查找
        int max = findMax(arr, 0, arr.length - 1);
        //最大值的位数来决定循环次数
        for(int i = 1; max / i > 0; i *= 10){
            int[][] buckets = new int[arr.length][10];
            //获取每个数字（个十百千）
            for(int j = 0; j < arr.length; j++){
                int num = (arr[j]/i) % 10;
                //放入桶
                buckets[j][num] = arr[j];
            }
            //回收桶元素
            int k = 0;
            for(int j = 0; j < 10; j++){
                for(int l = 0; l < arr.length; l++){
                    if(buckets[l][j] != 0){
                        arr[k++] = buckets[l][j];
                    }
                }
            }
            show(arr);
        }
    }

    /**
     * 递归，找出数组中最大的值
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int findMax(Integer[] arr, int L, int R){
        //如果该数组中只有一个值直接返回
        if(L == R){
            return arr[L];
        }
        int a = arr[L];
        //找出整体最大的
        int b = findMax(arr, L+1, R);
        if(a > b){
            return a;
        }
        return b;
    }


    public static void main(String[] args) {
        Integer[] arr = {66,811111118,98889,1,2,2255,7,42224};
        sort(arr);
        show(arr);
    }
}

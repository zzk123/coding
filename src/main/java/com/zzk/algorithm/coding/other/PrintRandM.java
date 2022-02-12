package com.zzk.algorithm.coding.other;

/**
 * @ClassName PrintRandM
 * @Description 17.从N个数中等概率打印M个
 * @Author zzk
 * @Date 2021/3/13 23:59
 **/
public class PrintRandM {

    public void printRandM(int[] arr, int m){
        if(arr == null || arr.length == 0 || m < 0){
            return;
        }
        m = Math.min(arr.length, m);
        int count = 0;
        int i = 0;
        while(count < m){
            i = (int) (Math.random() * (arr.length - count));
            System.out.println(arr[i]);
            swap(arr, arr.length - count++ - 1, i);
        }
    }

    public void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}

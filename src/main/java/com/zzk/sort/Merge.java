package com.zzk.sort;

import java.util.Comparator;

/**
 * @ClassName Merge
 * @Description 归并排序
 * @Author zzk
 * @Date 2021/4/1 0:35
 **/
public class Merge extends BaseSort {

    /**
     * 辅助数组
     */
    private static Comparable[] aux;

    public static void sort(Comparable[] arr){
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length-1);
    }

    private static void sort(Comparable[] arr, int lo, int hi){
        if(hi <= lo){
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);

    }

    public static void merge(Comparable[] arr, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++){
            aux[k] = arr[k];
        }
        for(int k = lo; k <= hi; k++){
            if(i > mid){
                arr[k] = aux[j++];
            }else if(j > hi){
                arr[k] = aux[i++];
            }else if(less(aux[j], aux[i])){
                arr[k] = aux[j++];
            }else{
                arr[k] = aux[i++];
            }
        }
    }
}

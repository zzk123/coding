package com.zzk.algorithm.sort;

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
        //排序左部分
        sort(arr, lo, mid);
        //排序右部分
        sort(arr, mid + 1, hi);
        //合并整个数组
        merge(arr, lo, mid, hi);
    }

    /**
     * 归并数组
     * @param arr
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(Comparable[] arr, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++){
            aux[k] = arr[k];
        }
        for(int k = lo; k <= hi; k++){
            //左部分交换完，开始交换右部分
            if(i > mid){
                arr[k] = aux[j++];
            }
            //右部分数据交换完，开始交换左部分
            else if(j > hi){
                arr[k] = aux[i++];
            }
            //比较左部分和右部分元素大小，进行交换
            else if(less(aux[j], aux[i])){
                arr[k] = aux[j++];
            }else{
                arr[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {1,4,6,8,2,3,4,0};
        sort(arr);
        show(arr);
    }
}

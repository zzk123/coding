package com.zzk.algorithm.sort;

/**
 * @ClassName Shell
 * @Description 希尔排序
 * @Author zzk
 * @Date 2021/4/1 0:21
 **/
public class Shell extends BaseSort {


    /**
     * 希尔排序
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int N = arr.length;
        int h = 1;
        //加入一个外循环将h按照递增序列递减
        //增幅h的初始值是数组长度乘以一个常数因子，最小为1
        while(h < N/3){
            h = h * 3 + 1;
        }
        while(h >= 1){
            //插入排序
            for(int i = h; i < N; i++){
                for(int j = i; j >= h && less(arr[j], arr[j-h]); j-=h){
                    exch(arr, j, j-h);
                }
            }
            h = h / 3;
        }
    }


    public static void main(String[] args) {
        Comparable[] arr = {5,8,9,2,1,0};
        sort(arr);
        show(arr);
    }

}

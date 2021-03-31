package com.zzk.sort;

/**
 * @ClassName Insertion
 * @Description 直接插入排序
 * @Author zzk
 * @Date 2021/3/31 23:59
 **/
public class Insertion extends BaseSort{

    /**
     * 升序
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int N = arr.length;
        for(int i = 1; i < N; i++){
            //用一个变量记录arr[i],逐一去跟arr[j]，arr[j-1]比较，如果大于就向右
            Comparable temp = arr[i];
            int j = i;
            for(; j > 0 & less(temp, arr[j-1]); j--){
                arr[j] = arr[j-1];
            }
            temp = arr[j];
        }
    }
}

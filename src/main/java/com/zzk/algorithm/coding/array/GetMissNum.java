package com.zzk.algorithm.coding.array;

/**
 * @ClassName GetMissNum
 * @Description 25.数组中未出现的最小正整数
 * @Author zzk
 * @Date 2021/2/6 16:49
 **/
public class GetMissNum {

    public int missNum(int[] arr){
        int l = 0;
        int r = arr.length;
        while(l < r){
            if(arr[l] == l + 1){
                l++;
            }else if(arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]){
                arr[l] = arr[--r];
            }else{
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public void swap(int[] arr, int u, int j){
        int tmp = arr[u];
        arr[u] = arr[j];
        arr[j] = tmp;
    }

}

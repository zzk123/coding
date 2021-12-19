package com.zzk.coding.array;

/**
 * @ClassName UniqueSort
 * @Description 23.数组的partition调整
 * @Author zzk
 * @Date 2021/2/6 16:14
 **/
public class UniqueSort {

    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param arr
     */
    public void leftUnique(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int u = 0;
        int i = 1;
        while(i != arr.length){
            if(arr[i++] != arr[u]){
                swap(arr, ++u, i - 1);
            }
        }
    }

    public void swap(int[] arr, int u, int j){
        int tmp = arr[u];
        arr[u] = arr[j];
        arr[j] = tmp;
    }


    /**
     * 补充题目
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param arr
     */
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int left = -1;
        int index = 0;
        int right = arr.length;
        while(index < right){
            if(arr[index] == 0){
                swap(arr, ++left, index++);
            }else if(arr[index] == 2){
                swap(arr, index, --right);
            }else{
                index++;
            }
        }
    }
}

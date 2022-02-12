package com.zzk.algorithm.coding.array;

/**
 * @ClassName PrintUnique
 * @Description 9.不重复打印排序数组中相加和为给定值的所有二元组和三元组
 * @Author zzk
 * @Date 2021/1/4 23:06
 **/
public class PrintUnique {

    /**
     * 二元组
     * 时间复杂度为O(N)
     */
    public void printUniquePair(int[] arr, int k){
        if(arr == null || arr.length < 2){
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            if(arr[left] + arr[right] < k){
                left++;
            }else if(arr[left] + arr[right] > k){
                right--;
            }else{
                if(left == 0 || arr[left - 1] != arr[left]){
                    System.out.print(arr[left] + "," + arr[right]);
                }
                left++;
                right--;
            }
        }
    }

    /**
     * 三元组
     * 时间复杂度为O(N^2)
     */
    public void printUniqueTriad(int[] arr, int k){
        if(arr == null || arr.length < 3){
            return;
        }
        for(int i = 0; i < arr.length - 2; i++){
            if(i == 0 || arr[i] != arr[i-1]){
                printRest(arr, i, i+1, arr.length-1, k-arr[i]);
            }
        }
    }

    public void printRest(int[] arr, int f, int l, int r, int k){
        while(l < r){
            if(arr[l] + arr[r] < k){
                l++;
            }else if(arr[l] + arr[r] > k){
                r--;
            }else{
                if(l == f + 1 || arr[l-1] != arr[l]){
                    System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
                }
                l++;
                r--;
            }
        }
    }
}

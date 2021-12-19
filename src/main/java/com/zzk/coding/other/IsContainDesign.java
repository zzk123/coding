package com.zzk.coding.other;

/**
 * @ClassName IsContainDesign
 * @Description 20.在有序旋转数组中找到一个数
 * @Author zzk
 * @Date 2021/3/17 23:07
 **/
public class IsContainDesign {

    public boolean isContains(int[] arr, int num){
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while(low <= high){
            mid = (low + high) / 2;
            if(arr[mid] == num){
                return true;
            }
            if(arr[low] == arr[mid] && arr[mid] == arr[high]){
                while(low != mid && arr[low] == arr[mid]){
                    low++;
                }
                if(low == mid){
                    low = mid + 1;
                    continue;
                }
            }
            if(arr[low] != arr[mid]){
                if(arr[mid] > arr[low]){
                    if(num >= arr[low] && num < arr[mid]){
                        high = mid - 1;
                    }else{
                        low = mid + 1;
                    }
                }else {
                    if(num > arr[mid] && num <= arr[high]){
                        low = mid + 1;
                    }else{
                        high = mid - 1;
                    }
                }
            }else{
                if(arr[mid] < arr[high]){
                    if(num > arr[mid] && num <= arr[high]){
                        low = mid + 1;
                    }else{
                        high = mid - 1;
                    }
                }else{
                    if(num >= arr[low] && num < arr[mid]){
                        high = mid - 1;
                    }else{
                        low = mid + 1;
                    }
                }
            }
        }
        return false;
    }
}

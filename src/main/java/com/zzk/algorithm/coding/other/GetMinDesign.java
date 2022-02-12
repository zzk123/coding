package com.zzk.algorithm.coding.other;

/**
 * @ClassName GetMinDesign
 * @Description 19.在有序旋转数组中找到最小值
 * @Author zzk
 * @Date 2021/3/17 23:14
 **/
public class GetMinDesign {

    public static int getMin(int[] arr){
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while(low < high){
            if(low == high - 1){
                break;
            }
            if(arr[low] < arr[high]){
                return arr[low];
            }
            mid = (low + high)/2;
            if(arr[low] > arr[mid]){
                high = mid;
                continue;
            }
            if(arr[mid] > arr[high]){
                low = mid;
                continue;
            }
            while(low < mid){
                if(arr[low] == arr[mid]){
                    low++;
                }else if(arr[low] < arr[mid]){
                    return arr[low];
                }else{
                    high = mid;
                    break;
                }
            }
        }
        return Math.min(arr[low], arr[high]);
    }

    public static void main(String[] args) {
        int[] ints = {4,5,6,1,2,3};
        getMin(ints);
    }
}

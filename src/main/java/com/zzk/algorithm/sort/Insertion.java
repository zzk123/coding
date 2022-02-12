package com.zzk.algorithm.sort;

/**
 * @ClassName Insertion
 * @Description 直接插入排序
 * @Author zzk
 * @Date 2021/3/31 23:59
 **/
public class Insertion extends BaseSort{

    /**
     * 直接插入排序
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int N = arr.length;
        Comparable temp;
        show(arr);
        for(int i = 1; i < N; i++){
            //用一个变量记录arr[i],逐一去跟arr[j]，arr[j-1]比较，如果大于就向右
            temp = arr[i];
            int j = i;
            System.out.println("循环开始：temp:" + temp);
            for(; j > 0 && less(temp, arr[j-1]); j--){
                arr[j] = arr[j-1];
                show(arr);
            }
            //交换对应的位置
            arr[j] = temp;
            show(arr);
        }
    }


    /**
     * 折半插入排序
     * @param arr
     */
    public static void binarySort(Comparable[] arr){
        int N = arr.length;
        Comparable temp;
        int j, low, high, mid;
        for(int i = 1; i < N; i++){
            //二分法比较大小，找到下标
            low = 0;
            high = i;
            while(low <= high){
                mid = (low + high)/2;
                if(less(arr[i], arr[mid])){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }
            //开始移动位置
            temp = arr[i];
            j = i;
            for(; j > high+1; j--){
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {2,4,5,3,1,0};
        //sort(arr);
        binarySort(arr);
        show(arr);
    }
}

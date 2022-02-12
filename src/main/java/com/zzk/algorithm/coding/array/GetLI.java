package com.zzk.algorithm.coding.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName GetLI
 * @Description 8.最长的可整合子数组的长度
 * @Author zzk
 * @Date 2021/1/4 22:36
 **/
public class GetLI {

    /**
     * 实现1：时间复杂度为 O(N^2) * O(NlogN) -> O(N^3 * logN)
     */
    public int getLIL1(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int len = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    public boolean isIntegerated(int[] arr, int left, int right){
        //O(N)
        int[] newArr = Arrays.copyOfRange(arr, left, right+1);
        //O(N * logN)
        Arrays.sort(newArr);
        for(int i = 1; i < newArr.length; i++){
            if(newArr[i-1] != newArr[i] - 1){
                return false;
            }
        }
        return true;
    }


    /**
     * 核心思路在于 一个数组没有重复数值，且最大值减去最小值加1的结果等于元素个数，那么该数组就是可整合数组
     * 时间复杂度为O(N^2)，空间复杂度为O(1)
     * @param arr
     * @return
     */
    public int getLIL2(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int len = 0;
        int max = 0;
        int min = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < arr.length; i++){
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for(int j = i; j < arr.length; j++){
                if(set.contains(arr[j])){
                    break;
                }
                set.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if(max - min == j - i){
                    len = Math.max(len, j - i + 1);
                }
            }
            set.clear();
        }
        return len;
    }
}

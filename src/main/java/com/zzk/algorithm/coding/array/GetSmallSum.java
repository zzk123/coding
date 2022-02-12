package com.zzk.algorithm.coding.array;

/**
 * @ClassName GetSmallSum
 * @Description 13.计算数组的小和
 * @Author zzk
 * @Date 2021/1/17 22:34
 **/
public class GetSmallSum {

    public int getSmallSum(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return func(arr, 0, arr.length - 1);
    }

    public int func(int[] s, int l, int r){
        if(l == r){
            return 0;
        }
        int mid = (l + r) / 2;
        return func(s, l, mid) + func(s, mid + 1, r) + merge(s, l, mid, r);
    }

    public int merge(int[] s, int left, int mid, int right){
        int[] h = new int[right - left + 1];
        int hi = 0;
        int i = left;
        int j = mid + 1;
        int smallSum = 0;
        while(i <= mid && j <= right){
            if(s[i] <= s[j]){
                smallSum += s[i] * (right - j + 1);
                h[hi++] = s[i++];
            }else{
                h[hi++] = s[j++];
            }
        }
        for(; (j < right + 1) || (i < mid + 1); j++, i++){
            h[hi++] = i > mid ? s[j] : s[i];
        }
        for(int k = 0; k != h.length; k++){
            s[left++] = h[k];
        }
        return smallSum;
    }
}

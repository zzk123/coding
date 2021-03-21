package com.zzk.coding.other;

/**
 * @ClassName test
 * @Description 27.在两个排序数组中找到第k小的数
 * @Author zzk
 * @Date 2021/3/21 15:20
 **/
public class GetUpMedianDesign {

    public int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2, int e2){
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while(s1 < e1){
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;
            if(a1[mid1] > a2[mid2]){
                e1 = mid1;
                s2 = mid2 + offset;
            }else if(a1[mid1] < a2[mid2]){
                s1 = mid1 + offset;
                e2 = mid2;
            }else{
                return a1[mid1];
            }
        }
        return Math.min(a1[s1], a2[s2]);
    }


    public int findKthNum(int[] arr1, int[] arr2, int kth){
        if(arr1 == null || arr2 == null){
            throw new RuntimeException();
        }
        if(kth < 1 || kth > arr1.length + arr2.length){
            throw new RuntimeException();
        }
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if(kth <= s){
            return getUpMedian(shorts, 0, kth-1, longs, 0, kth-1);
        }
        if(kth > l){
            if(shorts[kth - l - 1] >= longs[l - 1]){
                return shorts[kth - l - 1];
            }
            if(longs[kth - s - 1] >= shorts[s - 1]){
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth-1, s-1, longs, kth-s, l-1);
        }
        if(longs[kth - s - 1] >= shorts[s - 1]){
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s-1, longs, kth - s, kth - 1);
    }
}

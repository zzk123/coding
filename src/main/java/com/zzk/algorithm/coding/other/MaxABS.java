package com.zzk.algorithm.coding.other;

/**
 * @ClassName MaxABS
 * @Description 9.最大的leftMax与rightMax之差的绝对值
 * @Author zzk
 * @Date 2021/3/4 23:59
 **/
public class MaxABS {

    /**
     * 时间复杂度为O(N^2)，空间复杂度为O(1)
     * @param arr
     * @return
     */
    public int maxABS1(int[] arr){
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for(int i = 0; i != arr.length-1 ; i++){
            maxLeft = Integer.MIN_VALUE;
            for(int j = 0; j != i + 1; j++){
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;
            for(int j = i + 1; j != arr.length; j++){
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    /**
     * 时间复杂度为O(N)，空间复杂度为O(N)
     * @param arr
     * @return
     */
    public int maxABS2(int[] arr){
        int[] lArr = new int[arr.length];
        int[] rArr = new int[arr.length];
        lArr[0] = arr[0];
        rArr[arr.length - 1] = arr[arr.length - 1];
        for(int i = 1; i < arr.length; i++){
            lArr[i] = Math.max(lArr[i-1], arr[i]);
        }
        for(int i = arr.length - 2; i > -1; i--){
            rArr[i] = Math.max(rArr[i + 1], arr[i]);
        }
        int max = 0;
        for(int i = 0; i < arr.length - 1; i++){
            max = Math.max(max, Math.abs(lArr[i] - rArr[i+1]));
        }
        return max;
    }


    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param arr
     * @return
     */
    public int maxABS3(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }
}

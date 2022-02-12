package com.zzk.algorithm.coding.array;

/**
 * @ClassName GetMaxProduct
 * @Description TODO
 * @Author zzk
 * @Date 2021/2/1 22:19
 **/
public class GetMaxProduct {

    /**
     * 时间复杂度为O(N)，额外空间复杂度为O(1)
     * @param arr
     * @return
     */
    public double maxProduct(double[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        double max = arr[0];
        double min = arr[0];
        double res = arr[0];
        double maxEnd = 0;
        double minEnd = 0;
        for(int i = 1; i < arr.length; ++i){
            maxEnd = max * arr[i];
            minEnd = min * arr[i];
            max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
            min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }

}

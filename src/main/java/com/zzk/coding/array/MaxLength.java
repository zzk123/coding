package com.zzk.coding.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: coding
 * @description: 11.未排序数组中累加和为给定值的最长子数组系列问题
 * @author: zzk
 * @create: 2020-09-29 23:33
 */
public class MaxLength {

    /**
     * 时间复杂度为O(N)，空间复杂度为O(N)
     * 思路在于
     *  假设  s[j+1 ...i]  = k
     *  则有： s[j+1 ...i] = sum[0...i] - sum[0...j]
     *  即 k = sum - (sum -k)
     * @param arr
     * @param k
     * @return
     */
    public int maxLength(int[] arr, int k){
        if(arr == null || arr.length == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 重要
        int len = 0;
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(map.containsKey(sum -k)){
                len = Math.max(i-map.get(sum-k), len);
            }
            if(!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return len;
    }
}

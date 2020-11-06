package com.zzk.coding.recursion;

import java.util.HashMap;

/**
 * @program: coding
 * @description: 16.数组中的最长连续序列
 * @author: zzk
 * @create: 2020-11-06 22:33
 */
public class LongestConsecutive {

    /**
     * 时间复杂度为O(N)、额外空间复杂度为O(N-)
     * @param arr
     * @return
     */
    public int LongestConsecutive(int[]  arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], 1);
                if(map.containsKey(arr[i] - 1)){
                    max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
                }
                if(map.containsKey(arr[i] + 1)){
                    max = Math.max(max, merge(map, arr[i], arr[i] + 1));
                }
            }
        }
        return max;
    }

    public int merge(HashMap<Integer, Integer>map, int less, int more){
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);
        return len;
    }
}

package com.zzk.coding.array;

/**
 * @ClassName GetMaxGap
 * @Description TODO
 * @Author zzk
 * @Date 2021/2/6 16:55
 **/
public class GetMaxGap {

    public int maxGap(int[] nums){
        if(nums == null || nums.length < 2){
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if(min == max){
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for(int i = 0; i < len; i++){
            bid = bucket(nums[i], len, min, max);   //算出桶数
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = 0;
        int i = 0;
        while(i <= len){
            if(hasNum[i++]){
                lastMax = maxs[i-1];
                break;
            }
        }
        for(; i <= len; i++){
            if(hasNum[i]){
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
     }

    public int bucket(long num, long len, long min, long max){
        return (int) ((num - min) * len / (max - min));
    }
}

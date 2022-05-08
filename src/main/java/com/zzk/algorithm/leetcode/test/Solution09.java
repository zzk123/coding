package com.zzk.algorithm.leetcode.test;

import java.util.Arrays;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-04-21 23:41
 */
public class Solution09 {

    public static int eraseOverlapIntervals(int[][] intervals){
        Arrays.sort(intervals, (a, b) ->{
            return Integer.compare(a[0], b[0]);
        });
        int remove = 0;
        int pre = intervals[0][1];
        for(int i=1; i<intervals.length; i++){
            if(pre > intervals[i][0]){
                remove++;
                pre = Math.min(pre, intervals[i][1]);
            }else{
                pre = intervals[i][1];
            }
        }
        return remove;
    }
}

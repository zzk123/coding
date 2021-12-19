package com.zzk.coding.array;

/**
 * @ClassName GetMaxSum
 * @Description  17.子矩阵的最大累加和问题
 * @Author zzk
 * @Date 2021/2/1 21:41
 **/
public class GetMaxSum {

    /**
     * 时间复杂度为O(N^3)
     * @param m
     * @return
     */
    public int maxSum(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;
        for(int i = 0; i != m.length; i++){
            s = new int[m[0].length];
            for(int j = i; j != m.length; j++){
                cur = 0;
                for(int k = 0; k != s.length; k++){
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }
}

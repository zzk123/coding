package com.zzk.algorithm.coding.recursion;

/**
 * @program: coding
 * @description: 17.N皇后问题
 * @author: zzk
 * @create: 2020-11-06 23:06
 */
public class QueueNum {

    /**
     * 如果在(i,j)位置放置了一个皇后，那么接下来哪些位置不能放置皇后呢？
     * 1.整个第i行的位置都不能放置
     * 2.整个第j列的位置都不能放置
     * 3.如果位置(a，b)满足|a-i|==|b-j|，说明(a,b)与(i,j)处在同一条斜线上，也不能放置
     */

    public int num1(int n){
        if(n < 1){
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    public int process1(int i, int[] record, int n){
        if(i == n){
            return 1;
        }
        int res = 0;
        for(int j = 0; j < n; j++){
            if(isValid(record, i, j)){
                record[i] = j;
                res += process1(i+1, record, n);
            }
        }
        return res;
    }

    public boolean isValid(int[] record, int i, int j){
        for(int k = 0; k < i; k++){
            if(j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }
}

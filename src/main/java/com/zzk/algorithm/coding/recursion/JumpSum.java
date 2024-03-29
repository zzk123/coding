package com.zzk.algorithm.coding.recursion;

/**
 * @program: coding
 * @description: 15.跳跃游戏
 * @author: zzk
 * @create: 2020-11-05 23:30
 */
public class JumpSum {

    public int jump(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int jump = 0;
        int cur = 0;
        int next = 0;
        for(int i = 0; i < arr.length; i++){
            if(cur < i){
                jump++;
                cur = next;
            }
            next = Math.max(next, i+arr[i]);
        }
        return jump;
    }
}

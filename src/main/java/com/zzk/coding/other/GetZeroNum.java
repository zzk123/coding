package com.zzk.coding.other;

/**
 * @ClassName GetZeroNum
 * @Description 3.有关阶乘的两个问题
 * @Author zzk
 * @Date 2021/2/21 16:37
 **/
public class GetZeroNum {

    /**
     * 原题目
     */
    /**
     * 查找多少个因子5
     * 时间复杂度为O(NlogN)
     * @param num
     * @return
     */
    public int zeroNum1(int num){
        if(num < 0){
            return 0;
        }
        int res = 0;
        int cur = 0;
        for(int  i = 5; i < num + 1; i = i + 5){
            cur = i;
            while(cur  % 5 == 0){
                res ++;
                cur /= 5;
            }
        }
        return res;
    }

    /**
     *
     * 时间复杂度为O(logN)
     * @param num
     * @return
     */
    public int zeroNum2(int num){
        if(num < 0){
            return 0;
        }
        int res = 0;
        while(num != 0){
            res += num / 5;
            num /= 5;
        }
        return res;
    }

    /**
     * 进阶问题
     */

    /**
     *
     * @param num
     * @return
     */
    public int rightOne1(int num){
        if(num < 1){
            return -1;
        }
        int res = 0;
        while(num != 0){
            num >>>= 1;
            res += num;
        }
        return res;
    }

    public int rightOn2(int num){
        if(num < 1){
            return -1;
        }
        int ones = 0;
        int tmp = num;
        while(tmp != 0){
            ones += (tmp & 1) != 0 ? 1 : 0;
            tmp >>>= 1;
        }
        return num - ones;
    }

}

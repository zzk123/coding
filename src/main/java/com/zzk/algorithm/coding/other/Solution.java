package com.zzk.algorithm.coding.other;

/**
 * @ClassName Solution
 * @Description 16.1到n中1出现的次数
 * @Author zzk
 * @Date 2021/3/14 21:41
 **/
public class Solution {

    /**
     * 方法1
     * 时间复杂度为O(NlogN(以10为底))
     * @param num
     * @return
     */
    public int solution1(int num){
        if(num < 1){
            return 0;
        }
        int count = 0;
        for(int i = 1; i != num + 1; i++){
            count += get1Nums(i);
        }
        return count;
    }

    public int get1Nums(int num){
        int res = 0;
        while(num != 0){
            if(num % 10 == 1){
                res++;
            }
            num /=  10;
        }
        return res;
    }

    /**
     * 方法2
     * @param num
     * @return
     */
    public int solution2(int num){
        if(num < 1){
            return 0;
        }
        int len = getLenOfNum(num);
        if(len == 1){
            return 1;
        }
        int tmp1 = powerBaseIf10(len - 1);
        int first = num / tmp1;
        int firstOneNum = first == 1 ? num % tmp1 + 1 : tmp1;
        int otherOneNum = first * (len - 1) * (tmp1 / 10);
        return firstOneNum + otherOneNum + solution2(num % tmp1);
    }

    public int getLenOfNum(int num){
        int len  = 0;
        while(num != 0){
            len++;
            num /= 10;
        }
        return len;
    }

    public int powerBaseIf10(int base){
        return (int) Math.pow(10, base);
    }
}

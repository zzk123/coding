package com.zzk.algorithm.coding.other;

/**
 * @ClassName isPalindrome
 * @Description 18.判断一个数是否是回文数
 * @Author zzk
 * @Date 2021/3/13 23:55
 **/
public class isPalindrome {

    public boolean isPalindrome(int n){
        if(n == Integer.MAX_VALUE){
            return false;
        }
        n = Math.abs(n);
        int help = 1;
        while(n / help >= 10) {//防止help溢出
            help *= 10;
        }
        while(n != 0){
            if(n / help != n % 10){
                return false;
            }
            n = (n % help) / 10;
            help /= 100;
        }
        return true;
    }
}

package com.zzk.algorithm.coding.other;

/**
 * @ClassName Gcd
 * @Description 2.一行代码求两个数的最大公约数
 * @Author zzk
 * @Date 2021/2/21 16:20
 **/
public class Gcd {

    /**
     * 辗转相除法
     * 如果q和r分别是m除以n的商以及余数，即m=nq+r,那么m和n的最大公约数等于n和r的最大公约数
     * @param m
     * @param n
     * @return
     */
    public int gcd(int m, int n){
        return n == 0 ? m : gcd(n, m%n);
    }
}

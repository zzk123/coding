package com.zzk.algorithm.coding.bit;

/**
 * @ClassName FindMaxValue
 * @Description 2.不用任何比较判断找出两个数中较大的数
 * @Author zzk
 * @Date 2020/12/6 15:17
 **/
public class FindMaxValue {

    /**
     * 如果n为1，返回0.如果n为0，返回0
     * @param n
     * @return
     */
    public int flip(int n){
        return n^1;
    }

    /**
     * 返回整数n的符号。正数和0返回1，负数则返回0
     * @param n
     * @return
     */
    public int sign(int n){
        return flip((n >> 31) & 1);
    }

    /**
     * 有局限性，如果a-b的值出现溢出，返回结果不正确
     * @param a
     * @param b
     * @return
     */
    public int getMax1(int a, int b){
        int c = a - b;
        int scA = sign(c);
        int scB = flip(scA);
        return a * scA + b * scB;
    }

    /**
     * 如果a的符号与b的符号不同（difSab == 1， sameSab == 0）,则有
     * - 如果a为0或者正，那么b为负（sa == 1， sb == 0），应该返回a
     * - 如果a为负，那么b为0或正（sa == 0， sb == 1），应该返回b
     * 如果a的符号与b的符号相同（difSab == 0，sameSab == 1），这种情况下。a-b的值绝对不会溢出
     * - 如果a-b为0或者正（sc == 1），返回a
     * - 如果a-b为负（sc == 0），返回b
     * @param a
     * @param b
     * @return
     */
    public int getMax2(int a, int b){
        int c = a- b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }
}

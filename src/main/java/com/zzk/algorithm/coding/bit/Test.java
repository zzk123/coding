package com.zzk.algorithm.coding.bit;

/**
 * @ClassName Test
 * @Description 1.不用额外变量交换两个整数的值
 * @Author zzk
 * @Date 2020/12/6 13:57
 **/
public class Test {

    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(a);
        System.out.println(b);
    }
}

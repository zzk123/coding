package com.zzk.concurrent;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-18 00:23
 */
public class FinalTest {

    public static void main(String[] args) {
        final byte a = 1;
        final byte b = 2;
        byte c = a + b;
    }
}

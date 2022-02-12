package com.zzk.jvm.classloading;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-09 22:20
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init！");
    }

    public static int value = 123;

    public final static int value2 = 123;
}

package com.zzk.jvm.classloading;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-09 22:22
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init！");
    }
}

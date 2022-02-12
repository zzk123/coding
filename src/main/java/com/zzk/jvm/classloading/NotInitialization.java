package com.zzk.jvm.classloading;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-09 22:23
 */
public class NotInitialization {

    /**
     * 非主动使用类演示
     * 不会触发子类的初始化
     * @param args
     */
    public static void main1(String[] args) {
        System.out.println(SubClass.value);
    }

    public static void main2(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }

    public static void main(String[] args) {
        System.out.println(SubClass.value2);

    }
}

package com.zzk.jvm.dispatch;

import java.io.Serializable;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-01-27 21:55
 */
public class Overload {

    public static void sayHello(Object arg){
        System.out.println("hello Object");
    }

    public static void sayHello(int arg){
        System.out.println("hello int");
    }

    public static void sayHello(Character arg){
        System.out.println("hello Character");
    }

    public static void sayHello(char arg){
        System.out.println("hello char");
    }

    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }

    public static void sayHello(Serializable arg){
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}

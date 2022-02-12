package com.zzk.jvm.dispatch;

/**
 * @program: coding
 * @description: 方法静态分派演示
 * @author: zzk
 * @create: 2022-01-27 21:47
 */
public class StaticDispatch {

    static abstract class Human{

    }

    static class Man extends Human{

    }

    static class Woman extends Human{

    }

    public void sayHello(Human guy){
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy){
        System.out.println("hello, Man!");
    }

    public void sayHello(Woman guy){
        System.out.println("hello, Woman!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }

}

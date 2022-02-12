package com.zzk.jvm.dispatch;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-02-12 15:57
 */
public class FieldHasNoPolymorphic {

    static class Father {
        public int money = 1;

        public Father(){
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney(){
            System.out.println("I am Father, i has $" + money);
        }
    }

    static class Son extends Father{
        public int money = 3;

        public Son(){
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney(){
            System.out.println("I am Son, i has $" + money);
        }

    }

    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("This guy has $" + guy.money);
    }
}

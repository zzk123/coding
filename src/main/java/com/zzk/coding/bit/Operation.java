package com.zzk.coding.bit;

/**
 * @ClassName Operation
 * @Description 3.只用位运算不用算术运算实现整数的加减乘除运算
 * @Author zzk
 * @Date 2020/12/6 16:01
 **/
public class Operation {

    /**
     * 加法
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b){
        int sum = a;
        while(b != 0){ //进位为0，表示成功，不然需要循环相加进位
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * 获取一个数的相反数，也就是负数
     * 二进制数表达取反加1（补码）
     * @param n
     * @return
     */
    public static int negNum(int n){
        return add(~n, 1);
    }

    /**
     * 减法
     * a-b = a+(-b)
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b){
        return add(a, negNum(b));
    }

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    public static int multi(int a, int b){
        int res = 0;
        while(b != 0){
            if((b & 1) != 0){
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n){
        return n < 0;
    }


    public static int div(int a, int b){
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for(int i = 31; i > -1; i = minus(i, 1)){
            if((x >> i) >= y){
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }


    /**
     * 除法
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b){
        if(b == 0){
            throw new RuntimeException("divisor is 0");
        }
        if(a == Integer.MIN_VALUE && b == Integer.MAX_VALUE){
            return 1;
        }else if(b == Integer.MIN_VALUE){
            return 0;
        }else if(a == Integer.MIN_VALUE){
            int res = div(add(a, 1), b);
            return add(res, div(minus(a, minus(res, b)), b));
        }else{
            return div(a, b);
        }
    }

    public static void main(String[] args) {
        int a = 100;
        int b = 200;
        add(a, b);
    }
}


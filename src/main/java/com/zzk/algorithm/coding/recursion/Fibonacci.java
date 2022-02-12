package com.zzk.algorithm.coding.recursion;

/**
 * @program: coding
 * @description: 1.斐波那契系列问题的递归和动态规划
 * @author: zzk
 * @create: 2020-10-10 22:35
 */
public class Fibonacci {

    /**
     * ==========================================
     * 斐波那契问题：斐波那契
     */
    /**
     * 暴力递归法
     * 时间复杂度 O(2^N)
     * @param n
     * @return
     */
    public int f1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        return f1(n-1) + f1(n-2);
    }

    /**
     * 从左到右依次求出每一项的值
     * 时间复杂度为O(N)
     * @param n
     * @return
     */
    public int f2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for(int i = 3; i <= n; i++){
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * 用矩阵乘法求斐波那契数列第N项
     * 时间复杂度为O(logN)
     * @param n
     * @return
     */
    public int f3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }


    public int[][] matrixPower(int[][] m, int p){
        int[][] res = new int[m.length][m[0].length];
        //先把res设为单位矩阵，相当于整数中的1
        for(int i = 0; i < res.length; i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for(; p != 0; p >>=1){
            if((p & 1) != 0){
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    public int[][] muliMatrix(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m2[0].length; j++){
                for(int k = 0; k < m2.length; k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }


    /**
     * 补充问题1，台阶问题
     */

    /**
     * 暴力递归法
     * 时间复杂度 O(2^N)
     * @param n
     * @return
     */
    public int s1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return n;
        }
        return s1(n - 1) + s1(n -2);
    }

    /**
     * 从左到右依次求出每一项的值
     * 时间复杂度为O(N)
     * @param n
     * @return
     */
    public int s2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for(int i = 3; i <= n; i++){
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }


    /**
     * 用矩阵乘法求斐波那契数列第N项
     * 时间复杂度为O(logN)
     * @param n
     * @return
     */
    public int s3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n== 2){
            return n;
        }
        int[][] base = { { 1, 1}, {1 , 0} };
        int[][] res = matrixPower(base, n-2);
        return 2*res[0][0] + res[1][0];
    }

    /**
     * 暴力递归法
     * 时间复杂度 O(2^N)
     * @param n
     * @return
     */
    public int c1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2 || n == 3){
            return n;
        }
        return c1(n-1) + c1(n - 3);
    }
    /**
     * 从左到右依次求出每一项的值
     * 时间复杂度为O(N)
     * @param n
     * @return
     */
    public int c2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2 || n == 3){
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for(int i = 4; i <= n; i++){
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }
    /**
     * 用矩阵乘法求斐波那契数列第N项
     * 时间复杂度为O(logN)
     * @param n
     * @return
     */
    public int c3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2 || n == 3){
            return n;
        }
        int[][] base = { { 1, 1, 0}, {0, 0, 1}, { 1, 0, 0}};
        int[][] res = matrixPower(base, n-3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }
}

package com.zzk.coding.str;

/**
 * @ClassName GetNum
 * @Description 16.0左边必有1的二进制字符串数量
 * @Author zzk
 * @Date 2020/11/21 17:39
 **/
public class GetNum {

    /**
     * 时间复杂度为O(2^N)的递归方法
     *  i < N 时，p(i) = p(i+1) + p(i+2)
     *  i = N - 1 时，p(i) = 2
     *  i = N 时，p(i) = 1
     * @param n
     * @return
     */
    public int getNum1(int n){
        if(n < 1){
            return 0;
        }
        return process(1, n);
    }

    public int process(int i, int n){
        if(i == n-1){
            return 2;
        }
        if(i == n){
            return 1;
        }
        return process(i+1, n) + process(i+2, n);
    }

    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * 根据getNum1方法可以得出，当N为1,2,3,4,5,6,7,8时，结算的结果为1,2,3,5,8,13,21,34
     * 可以看出是一个形如斐波那契数列的结果
     * @param n
     * @return
     */
    public int getNum2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for(int i = 2; i < n+1; i++){
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }


    /**
     * 用矩阵乘法的办法求解斐波那契数列
     * 时间复杂度为O(logN)
     * @param n
     * @return
     */
    public int getNum3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public int[][] matrixPower(int[][] m, int p){
        int[][] res = new int[m.length][m[0].length];
        for(int i = 0; i < res.length; i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for( ; p != 0; p >>= 1){
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
}

package com.zzk.coding.recursion;

/**
 * @program: coding
 * @description: 13.表达式得到期望结果的组成种数
 * @author: zzk
 * @create: 2020-10-31 22:42
 */
public class NumExpress {

    /**
     * 判断express是否合乎题目要求
     * 1. 表达式的长度必须是奇数
     * 2. 表达式下标为偶数位置的字符一定是'0'或者'1'
     * 3. 表达式下标为奇数位置的字符一定是'&'或'|'或'^'
     *
     * @param exp
     * @return
     */
    public boolean isVaild(char[] exp) {
        if ((exp.length & 1) == 0) {
            return false;
        }
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时间复杂度为O(N!)
     * 空间复杂度为O(N)
     * @param express
     * @param desired
     * @return
     */
    public int num1(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isVaild(exp)) {
            return 0;
        }
        return p(exp,desired, 0, exp.length - 1);
    }

    public int p(char[] exp, boolean desired, int l, int r){
        if(l == r){
            if(exp[l] == '1'){
                return desired ? 1 : 0;
            }else{
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        if(desired){
            for(int i = l + 1; i < r; i += 2){
                switch (exp[i]){
                    case '&':
                        res += p(exp, true, l, i - 1) * p(exp, true, i+1, r);
                        break;
                    case '|':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                }
            }
        }else{
            for(int i = l + 1; i < r; i += 2){
                switch (exp[i]){
                    case '&':
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '|':
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                }
            }
        }
        return res;
    }

    /**
     * 动态规划。
     * 时间复杂度为O(N^3)
     * 空间复杂度为O(N^2)
     * @param express
     * @param desired
     * @return
     */
    public int num2(String express, boolean desired){
        if(express == null || express.equals("")){
            return 0;
        }
        char[] exp = express.toCharArray();
        if(!isVaild(exp)){
            return 0;
        }
        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];
        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;
        for(int i = 2; i < exp.length; i+=2){
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '1' ? 0 : 1;
            for(int j = i -2; j >= 0; j -= 2){
                for(int k = j; k < i; k +=2){
                    if(exp[k + 1] == '&'){
                        t[j][i] += t[j][k] * t[k+2][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k+2][i] + f[j][k] * t[k+2][i];
                    }else if(exp[k+1] == '|'){
                        t[j][i] += (f[j][k] + t[j][k]) * t[k+2][i] + t[j][k] * f[k+2][i];
                        f[j][i] += f[j][k] * f[k+2][i];
                    }else{
                        t[j][i] += f[j][k] * t[k+2][i] + t[j][k] * f[k+2][i];
                        f[j][i] += f[j][k] * f[k+2][i] + t[j][k] * t[k+2][i];
                    }
                }
            }
        }
        return desired? t[0][t.length-1] : f[0][f.length-1];
    }
}

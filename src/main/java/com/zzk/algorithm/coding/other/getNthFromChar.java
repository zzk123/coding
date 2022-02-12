package com.zzk.algorithm.coding.other;

/**
 * @ClassName getNthFromChar
 * @Description 15.一种字符串和数字的对应关系
 * @Author zzk
 * @Date 2021/3/14 0:23
 **/
public class getNthFromChar {

    public String getString(char[] chs, int n){
        if(chs == null || chs.length == 0 || n < 1){
            return "";
        }
        int cur = 1;
        int base = chs.length;
        int len = 0;
        while(n >= cur){
            len++;
            n-=cur;
            cur*=base;
        }
        char[] res = new char[len];
        int index = 0;
        int nCur = 0;
        do{
            cur /= base;
            nCur = n / cur;
            res[index++] = getKthCharAtChs(chs, nCur + 1);
            n %= cur;
        }while(index != res.length);
        return String.valueOf(res);
    }

    public char getKthCharAtChs(char[] chs, int k) {
        if(k < 1 || k > chs.length){
            return 0;
        }
        return chs[k - 1];
    }

    public int getNum(char[] chs, String str){
        if(chs == null || chs.length == 0){
            return 0;
        }
        char[] strc = str.toCharArray();
        int base = chs.length;
        int cur = 1;
        int res = 0;
        for(int i = strc.length - 1; i != -1; i--){
            res += getNthFromChar(chs, strc[i]) * cur;
            cur *= base;
        }
        return res;
    }

    public int getNthFromChar(char[] chs, char ch){
        int res = -1;
        for(int i = 0; i != chs.length; i++){
            if(chs[i] == ch){
                res = i + 1;
                break;
            }
        }
        return res;
    }
}

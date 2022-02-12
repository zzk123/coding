package com.zzk.algorithm.coding.str;

/**
 * @ClassName PoinNewChar
 * @Description 19.找到被指的新类型字符
 * @Author zzk
 * @Date 2020/11/22 16:04
 **/
public class PointNewChar {

    public String pointNewChar(String s, int k){
        if(s == null || s.equals("") || k < 0 || k >= s.length()){
            return "";
        }
        char[] chas = s.toCharArray();
        int uNum = 0;
        for(int i = k - 1; i >= 0; i--){
            if(!Character.isUpperCase(chas[i])){
                break;
            }
            uNum++;
        }
        if((uNum & 1) == 1){
            return s.substring(k-1, k+1);
        }
        if(Character.isUpperCase(chas[k])){
            return s.substring(k, k+2);
        }
        return String.valueOf(chas[k]);
    }
}

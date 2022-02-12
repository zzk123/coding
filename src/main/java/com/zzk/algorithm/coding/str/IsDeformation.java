package com.zzk.algorithm.coding.str;

/**
 * @program: coding
 * @description: 1.判断两个字符串是否互为变形词
 * @author: zzk
 * @create: 2020-11-10 21:48
 */
public class IsDeformation {

    /**
     * 字符种类为M,str1和str2的长度为N
     * 那么该方法的时间复杂度为O(N),空间复杂度为O(M)
     * @param str1
     * @param str2
     * @return
     */
    public boolean isDeformation(String str1, String str2){
        if(str1 == null || str2 == null || str1.length() != str2.length()){
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for(int i = 0; i < chas1.length; i++){
            map[chas1[i]]++;
        }
        for(int i = 0; i < chas2.length; i++){
            if(map[chas2[i]]-- == 0){
                return false;
            }
        }
        return true;
    }
}

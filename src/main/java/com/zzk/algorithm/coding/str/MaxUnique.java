package com.zzk.algorithm.coding.str;

/**
 * @ClassName MaxUnique
 * @Description 18.找到字符串的最长无重复字符子串
 * @Author zzk
 * @Date 2020/11/22 15:44
 **/
public class MaxUnique {

    /**
     * str的长度为N，字符编码的范围为M
     * 时间复杂度为O(N)，额外空间复杂度为O(M)
     * @param str
     * @return
     */
    public int maxUnique(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for(int i = 0; i < 256; i++){
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for(int i = 0; i != chas.length; i++){
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i;
        }
        return len;
    }
}

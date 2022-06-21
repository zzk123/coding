package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description: KMP算法
 * @author: zzk
 * @create: 2022-05-28 16:27
 */
public class Solution17 {

    /**
     * KMP算法
     * @param ss
     * @param pp
     * @return
     */
    public static int strStr(String ss, String pp){
        if(pp.isEmpty()){
            return 0;
        }
        int n = ss.length(), m = pp.length();
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        int[] next = new int[m+1];
        //构建 next 数组，数组长度为匹配串的长度
        //从i=2，j=0开始，i小于等于匹配串长度
        for(int i=2, j=0; i<=m; i++){
            //匹配不成功，j = next[j]
            while(j > 0 && p[i] != p[j+1]){
                j = next[j];
            }
            //匹配成功，先 j++
            if(p[i] == p[j+1]){
                j++;
            }
            //更新next[i]，结束循环，i++
            next[i] = j;
        }
        //匹配过程，i=1，j=0开始
        for(int i = 1, j = 0; i <= n; i++){
            //匹配不成功 j = next[j]
            while(j > 0 && s[i] != p[j+1]){
                j = next[j];
            }
            //匹配成功，j++
            if(s[i] == p[j+1]){
                j++;
            }
            //整段匹配成功，直接返回下标
            if(j == m){
                return i-m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String ss = "abcdecf", pp = "decf";
        System.out.println(strStr(ss, pp));
    }
}

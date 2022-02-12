package com.zzk.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * @program: coding
 * @description: 455. 分发饼干：https://leetcode-cn.com/problems/assign-cookies/
 * @author: zzk
 * @create: 2021-08-10 22:51
 */
public class FindContentChildren {

    /**
     * d
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        int i = 0;
        int j = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        if(s.length <= 0){
            return i;
        }
        while(i < g.length && j < s.length){
            if(g[i] <= s[j]){
                i++;
                j++;
                continue;
            }
            j++;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] g = {10, 9, 8, 7};
        int[] s = {5, 6, 7, 8};
        System.out.println(findContentChildren(g, s));
    }
}

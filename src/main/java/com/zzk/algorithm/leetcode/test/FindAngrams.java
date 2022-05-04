package com.zzk.algorithm.leetcode.test;

import java.util.*;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-31 22:22
 */
public class FindAngrams {

    public static List<Integer> findAngrams(String s, String p){
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if(n < m){
            return res;
        }
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for(int i = 0; i < m; i++){
            pCnt[p.charAt(i)-'a']++;
        }
        int left = 0;
        for(int right = 0; right < n; right++){
            int curRight = s.charAt(right)-'a';
            sCnt[curRight]++;
            while(sCnt[curRight] > pCnt[curRight]){
                int curLeft = s.charAt(left)-'a';
                sCnt[curLeft]--;
                left++;
            }
            if(right - left + 1 == m){
                res.add(left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "cbeaebabacd", p = "abc";
        findAngrams(s, p);
    }
}

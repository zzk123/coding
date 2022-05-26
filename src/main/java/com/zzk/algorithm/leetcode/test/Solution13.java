package com.zzk.algorithm.leetcode.test;

import java.util.Arrays;

/**
 * @program: coding
 * @description: https://leetcode.cn/problems/permutation-in-string/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-7k7u/
 * @author: zzk
 * @create: 2022-05-12 22:36
 */
public class Solution13 {

    public static boolean checkInclusion2(String s1, String s2){
        int n = s1.length(), m = s2.length();
        if(n > m){
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for(int i=0; i<n; i++){
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if(Arrays.equals(cnt1, cnt2)){
            return true;
        }
        for(int i=n; i<m; i++){
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i-n) - 'a'];
            if(Arrays.equals(cnt1, cnt2)){
                return true;
            }
        }
        return false;
    }

    /**
     * 双指针
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2){
        int n = s1.length(), m = s2.length();
        if(n > m){
            return false;
        }
        int[] cnt = new int[26];
        for(int i = 0; i < n; i++){
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for(int right = 0; right < m; right++){
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while(cnt[x] > 0){
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if(right - left + 1 == n){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "asldhaklsdjaskldba";
        checkInclusion(s1, s2);
    }
}

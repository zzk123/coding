package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-05-26 22:20
 */
public class Solution16 {

    public static StringBuilder removeSpace(String s){
        int start = 0;
        int end = s.length() - 1;
        while(s.charAt(start) == ' '){
            start++;
        }
        while(s.charAt(end) == ' '){
            end--;
        }
        StringBuilder sb = new StringBuilder();
        while(start <= end){
            char c = s.charAt(start);
            if(c != ' ' || sb.charAt(sb.length() - 1) != ' '){
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    public static void main(String[] args) {
        String str = " key is bule word!! ";
        System.out.println(removeSpace(str).toString());
    }
}

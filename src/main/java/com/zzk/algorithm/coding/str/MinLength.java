package com.zzk.algorithm.coding.str;

/**
 * @ClassName MinLength
 * @Description 20.最小包含子串的长度
 * @Author zzk
 * @Date 2020/11/22 17:16
 **/
public class MinLength {

    /**
     * str1的长度为N，str2的长度为M
     * 时间复杂度为O(N)
     * @param str1
     * @param str2
     * @return
     */
    public int minLength(String str1, String str2){
        if(str1 == null || str2 == null || str1.length() < str2.length()){
            return 0;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for(int i = 0; i != chas2.length; i++){
            map[chas2[i]]++;
        }
        int left = 0;
        int right = 0;
        int match = chas2.length;
        int minLen = Integer.MAX_VALUE;
        while(right != chas1.length){
            map[chas1[right]]--;
            if(map[chas1[right]] >= 0){
                match--;
            }
            if(match == 0){
                while(map[chas1[left]] < 0){
                    map[chas1[left++]]++;
                }
                minLen = Math.min(minLen, right - left + 1);
                match++;
                map[chas1[left++]]++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}

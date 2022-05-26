package com.zzk.algorithm.leetcode.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: coding
 * @description: https://leetcode-cn.com/problems/fruit-into-baskets/solution/tao-mo-ban-hua-dong-chuang-kou-qiu-zui-c-pner/
 * @author: zzk
 * @create: 2022-05-08 21:56
 */
public class Solution12 {

    public static int totalFruit(int[] tree){
        if(tree == null || tree.length == 0){
            return 0;
        }
        int n = tree.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0, left = 0;
        for(int i=0; i<n; i++){
            map.put(tree[i], map.getOrDefault(tree[i], 0) + 1);
            while (map.size() > 2){
                map.put(tree[left], map.get(tree[left])-1);
                if(map.get(tree[left]) == 0){
                    map.remove(tree[left]);
                }
                left++;
            }
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] f = new int[]{3,3,3,1,2,1,1,1,2,3,3,3,4};
        totalFruit(f);
    }
}

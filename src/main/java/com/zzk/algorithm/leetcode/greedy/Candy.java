package com.zzk.algorithm.leetcode.greedy;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2021-08-10 23:33
 */
public class Candy {

    public static int candy(int[] ratings) {
        int[] s = new int[ratings.length];
        for(int i = 0; i < ratings.length; i++){
            s[i] = 1;
        }
        int j = 0;
        while(j < ratings.length-1){
            if(ratings[j] > ratings[j+1]){
                s[j] = s[j] + 1;
            }
            if(ratings[j] == ratings[j+1] && j > 1 && ratings[j] > ratings[j-1]){
                s[j] ++;
                s[j+1] = s[j];
            }
            j++;
        }
        j = ratings.length -1;
        while(j > 0){
            if(ratings[j] >= ratings[j-1] && s[j] <= s[j-1]){
                if(ratings[j] == ratings[j-1] && s[j] < s[j-1]){
                    s[j] = s[j-1];
                }else {
                    s[j] = s[j - 1] + 1;
                }
            }
            j--;
        }
        int count = 0;
        for(int h = 0; h < s.length; h++){
            count += s[h];
        }
        return count;
    }

    public static void main(String[] args) {
        int[] g = {1, 2, 87, 87, 87, 2, 1};
        System.out.println(candy(g));
    }
}

package com.zzk.coding.recursion;

/**
 * @program: coding
 * @description: 14.排成一条线的纸牌博弈问题
 * @author: zzk
 * @create: 2020-11-01 22:54
 */
public class WinGame {

    /**
     * 整体的时间复杂度为O(2^N)，额外空间复杂度为O(N)
     * @param arr
     * @return
     */
    public int win1(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 最优情况获取，一般对手会拿最好的牌子，剩下的就是最差的
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public int f(int[] arr, int i, int j){
        if(i == j){
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    /**
     * 最差情况获取
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public int s(int[] arr, int i, int j){
        if(i == j){
            return 0;
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }


    /**
     * 时间复杂度为O(N^2)，额外空间复杂度为O(N^2)
     * @param arr
     * @return
     */
    public int win2(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
         }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for(int j = 0; j < arr.length; j++){
            f[j][j] = arr[j];
            for(int i = j - 1; i >= 0; i--){
                //最好情况
                f[i][j] = Math.max(arr[i] + s[i+1][j], arr[j] + s[i][j-1]);
                //最差情况
                s[i][j] = Math.min(f[i+1][j], f[i][j-1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length-1]);
    }
}

package com.zzk.coding.recursion;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: coding
 * @description: 4.换钱的方法数
 * @author: zzk
 * @create: 2020-10-15 22:22
 */
public class GetCurencyNum {

    public static  final Logger logger = LoggerFactory.getLogger(GetCurencyNum.class);

    /**
     * 暴力递归
     * 时间复杂度最差情况为 O(aim^N)
     * @param arr
     * @param aim
     * @return
     */
    public static int coins1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return process1(arr, 0, aim);
    }

    public static int process1(int[] arr, int index, int aim){
        int res = 0;
        if(index == arr.length){
            return res = aim == 0 ? 1 : 0;
        }else{
            for(int i = 0; arr[index] * i <= aim; i++){
                res += process1(arr, index+1, aim -  arr[index]*i);
            }
        }
        return res;
    }


    /**
     * 记忆搜索方法，对于上面暴力递归的优化
     * 时间复杂度为 O(N * aim^2)
     * @param arr
     * @param aim
     * @return
     */
    public static int coins2(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1]; 
        int result = process2(arr, 0, aim, map);
        logger.info(new Gson().toJson(map));
        return result;
    }


    public static int process2(int[] arr, int index, int aim, int[][] map){
        int res = 0;
        if(index == arr.length){
            res = aim == 0 ? 1 : 0;
        }else{
            int mapValue = 0;
            for(int i = 0; arr[index] * i <= aim; i++){
                mapValue = map[index + 1][aim - arr[index] * i];
                if(mapValue != 0){
                    res += mapValue == -1 ? 0 : mapValue;
                }else{
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    /**
     * 动态规划法
     * 时间复杂度为 O(N * aim^2)
     * @param arr
     * @param aim
     * @return
     */
    public static int coins3(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for(int i = 0; i < arr.length; i++){
            dp[i][0] = 1;
        }
        for(int j = 1; arr[0] * j <= aim; j++){
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for(int i =1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                num = 0;
                for(int k = 0; j - arr[i] * k >= 0; k++){
                    num += dp[i-1][j-arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length-1][aim];
    }


    /**
     * 优化后的动态规划算法
     * 时间复杂度为O(N * aim)
     * @param arr
     * @param aim
     * @return
     */
    public static int coins4(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for(int i = 0; i < arr.length; i++){
            dp[i][0] = 1;
        }
        for(int j = 1; arr[0] * j <= aim; j++){
            dp[0][arr[0] * j] = 1;
        }
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                dp[i][j] = dp[i-1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j-arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 动态规划算法 - 经过空间压缩后的算法
     * 时间复杂度为 O(N * aim)，额外空间复杂度为O(aim)
     * @param arr
     * @param aim
     * @return
     */
    public static int coins5(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[] dp = new int[aim + 1];
        for(int j = 0; arr[0] * j <= aim; j++){
            dp[arr[0] * j] = 1;
        }
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                dp[j] += j - arr[i] >= 0 ? dp[j -arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int arr[] = {5,10,25,5};
        int aim = 15;
        coins2(arr, aim);
    }

}

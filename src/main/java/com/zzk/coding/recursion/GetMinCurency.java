package com.zzk.coding.recursion;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: coding
 * @description: 3.换钱的最少货币数
 * @author: zzk
 * @create: 2020-10-13 22:30
 */
public class GetMinCurency {

    public static  final Logger logger = LoggerFactory.getLogger(GetMinCurency.class);

    public static final Integer MAX_VALUE = Integer.MAX_VALUE;

    /**
     * 暴力求解最小货币数
     * @param arr
     * @param i
     * @param rest
     * @return
     */
    public static int process(int[] arr, int i, int rest){
        if(i == arr.length){
            return rest == 0 ? 0 : -1;
        }
        int res = -1;
        for(int k = 0; arr[i] * k <= rest; k++){
            int next = process(arr, i+1, rest - arr[i] * k);
            if(next != -1){
                res = res == -1 ? k + next : Math.min(res, k + next);
            }
        }
        return res;
    }


    /**
     * 动态规划算法
     * 时间复杂度和空间复杂度为O(N * aim)，N为arr的长度
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int n = arr.length;
        int max = MAX_VALUE;
        int [][] dp = new int[n][aim+1];
        //循环定义查找第一行的数据
        for(int j = 1; j <= aim; j++){
            dp[0][j] = max;
            if(j - arr[0] >= 0 && dp[0][j-arr[0]] != max){
                dp[0][j] = dp[0][j-arr[0]] + 1;
            }
        }
        logger.info(new Gson().toJson(dp));
        int left = 0;
        for(int i = 1; i < n; i++){
            //aim从1到最后依次比较
            for(int j = 1; j <= aim; j++){
                left = max;
                //判断之前是否有值，有值则加1
                if(j - arr[i] >= 0 && dp[i][j-arr[i]] != max){
                    left = dp[i][j-arr[i]] + 1;
                }
                //对比前一行同一列的值，取最小值
                dp[i][j] = Math.min(left, dp[i-1][j]);
            }
        }
        logger.info(new Gson().toJson(dp));
        //最后的一个值正好是最小的结果
        return dp[n-1][aim] != max ? dp[n-1][aim] : -1;
    }


    /**
     * 动态规划，对于minCoins1进行数组优化，空间压缩
     * 时间复杂度为O(N * aim)，空间复杂度为O(aim)
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins2(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int n = arr.length;
        int max = MAX_VALUE;
        int[] dp = new int[aim + 1];
        for(int j = 1; j <= aim; j++){
            dp[j] = max;
            if(j - arr[0] >= 0  && dp[j-arr[0]] != max){
                dp[j] = dp[j-arr[0]] + 1;
            }
        }
        int left = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= aim; j++){
                left = max;
                if(j - arr[i] >= 0 && dp[j - arr[i]] != max){
                    left = dp[j-arr[i]] + 1;
                }
                dp[j] = Math.min(left, dp[j]);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }

    /**
     * ===========================================
     * 补充问题
     */
    /***
     * 时间复杂度和空间复杂度 O(N * aim)，N为arr的长度
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins3(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int n = arr.length;
        int max = MAX_VALUE;
        int[][] dp = new int[n][aim+1];
        for(int j = 1;  j <= aim; j++){
            dp[0][j] = max;
        }
        if(arr[0] <= aim){
            dp[0][arr[0]] = 1;
        }
        logger.info(new Gson().toJson(dp));
        int leftup = 0; // 左上角某个位置的值
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= aim; j++){
                leftup = max;
                if(j - arr[i] > 0 && dp[i-1][j-arr[i]] != max){
                    leftup = dp[i-1][j-arr[i]] + 1;
                }
                dp[i][j] = Math.min(leftup, dp[i-1][j]);
            }
        }
        logger.info(new Gson().toJson(dp));
        return dp[n-1][aim] != max ? dp[n-1][aim] : -1;
    }

    /**
     * 对minCoins3进行空间压缩优化
     * 时间复杂度为O(N * aim)，额外空间复杂度为 O(aim)
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins4(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int n = arr.length;
        int max = MAX_VALUE;
        int[] dp = new int[aim + 1];
        for(int j = 1; j <= aim; j++){
            dp[j] = max;
        }
        if(arr[0] <= aim){
            dp[arr[0]] = 1;
        }
        int leftup = 0;
        for(int i = 1; i < n; i++){
            for(int j = aim; j > 0; j--){
                leftup = max;
                if(j - arr[i] >= 0 && dp[j - arr[i]] != max){
                    leftup = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(leftup, dp[j]);
            }
        }
        return dp[aim] != max ? dp[aim] :   -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3};
        int aim = 20;
        System.out.println(minCoins1(arr, aim));
        System.out.println(minCoins2(arr, aim));

        int[] arr2 = {5, 2, 5, 3};
        int aim2 = 10;
        System.out.println(minCoins3(arr2, aim2));
        System.out.println(minCoins4(arr2, aim2));
    }
}

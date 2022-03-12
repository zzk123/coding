package com.zzk.algorithm.labuladong.dp;

/**
 * @program: coding
 * @description: 扔鸡蛋问题
 * @author: zzk
 * @create: 2022-03-08 23:19
 */
public class DPDemo5 {


    public static int superEggDrop2(int K, int N){
        int[][] f = new int[K+1][N+1];

        for(int i = 0; i <= N; i++){
            f[1][i] = 1;
        }
        for(int i = 1; i <= K; i++){
            f[i][1] = 1;
        }

        for(int j = 2; j <= K; j++){
            int cur = 1;
            for(int i = 1; i <= N; i++){
                // 当前楼层与下一个楼层进行比较，如果当前楼层的次数更小，说明当前楼层是最优位置
                while(cur < i && Math.max(f[j-1][cur-1], f[j][i-cur]) > Math.max(f[j - 1][cur], f[j][i - cur - 1])){
                    cur++;
                }
                f[j][i] = 1 + Math.max(f[j - 1][cur - 1], f[j][i - cur]);
            }
        }
        return f[K][N];
    }

    /**
     * 首先是状态表示，利用一个二维数组，两个维度分别表示鸡蛋的数量和楼层数。
     * 然后是状态转转移，从低到高，鸡蛋从少到多，遍历每一种楼层和鸡蛋数量的情况。在求每一种组合情况的时候，再从1遍历楼层，求得对优解。
     * 鸡蛋丢在每一层的情况都是碎或者没有碎。
     * 如果碎了，那么临界点就在下面的楼层，同时鸡蛋数减一。
     * 如果没碎，那么临界点就在上面的楼层，同时鸡蛋数量不变。
     * 然后取上面二者最坏的情况，在加上本次丢的次数1。
     * 先枚举鸡蛋或者先枚举楼层都是可以的。
     * @param K
     * @param N
     * @return
     */
    public static int supperEggDrop(int K, int N){
        int[][] f = new int[K+1][N+1];

        for(int i = 1; i <= K; i++){
            f[i][1] = 1;
        }

        for(int i = 1; i <= N; i++){
            f[1][i] = i;
        }
        //枚举鸡蛋
        for(int i = 2; i <= K; i++){
            //枚举楼层
            for(int j = 2; j <= N; j++){
                f[i][j] = f[i-1][j];
                //状态转移
                for(int x = 1; x < j; x++){
                    f[i][j] = Math.min(f[i][j], 1+Math.max(f[i-1][x-1], f[i][j-x]));
                }
            }
        }
        return f[K][N];
    }

    public static void main(String[] args) {
        int K = 2;
        int N = 100;
        System.out.println(supperEggDrop(K, N));
    }
}

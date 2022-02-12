package com.zzk.algorithm.coding.other;

/**
 * @ClassName GetKNumsRand
 * @Description 7.蓄水池算法
 * @Author zzk
 * @Date 2021/3/3 23:05
 **/
public class GetKNumsRand {

    /**
     * 一个简单的随机函数，决定事情做还是不做
     * @param max
     * @return
     */
    public int rand(int max){
        return (int) (Math.random() * max) + 1;
    }

    public int[] getKNumsRand(int k, int max){
        if(max < 1 || k < 1){
            return null;
        }
        int[] res = new int[Math.min(k, max)];
        for(int i = 0; i != res.length; i++){
            res[i] = i + 1;  //前k个数直接进袋子
        }
        for(int i = k + 1; i < max + 1; i++){
            if(rand(i) <= k){      // 决定i进不进袋子
                res[rand(k)-1] = i; //i随机替换袋子中的一个
            }
        }
        return res;
    }
}

package com.zzk.algorithm.coding.other;

/**
 * @ClassName RandXPowerK
 * @Description 12.调整[0,x]区间上的数出现的概率
 * @Author zzk
 * @Date 2021/3/11 0:16
 **/
public class RandXPowerK {


    public double randXPower2(){
        return Math.max(Math.random(), Math.random());
    }

    public double randXPowerK(int k){
        if(k < 1){
            return 0;
        }
        double res = -1;
        for(int i = 0; i != k; i++){
            res = Math.max(res, Math.random());
        }
        return res;
    }

}

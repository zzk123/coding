package com.zzk.coding.bit;

/**
 * @ClassName OddNumberCount
 * @Description 5.在其他数都出现偶数次的数组中找到出现奇数次的数
 * @Author zzk
 * @Date 2020/12/13 15:17
 **/
public class OddNumberCount {

    public int onceNum(int[] arr, int k){
        int[] e0 = new int[32];
        for(int i = 0; i != arr.length; i++){
            setExclusiveOr(e0, arr[i], k);
        }
        int res = getNumFromKSysNum(e0, k);
        return res;
    }

    public void setExclusiveOr(int[] eO, int value, int k){
        int [] curKSysNum = getKSysNumFromNum(value, k);
        for(int i = 0; i != eO.length; i++){
            eO[i] = (eO[i] + curKSysNum[i]) % k;
        }
    }

    public int[] getKSysNumFromNum(int value, int k){
        int[] res = new int[32];
        int index = 0;
        while(value != 0){
            res[index++] = value%k;
            value = value / k;
        }
        return res;
    }

    public int getNumFromKSysNum(int[] eO, int k){
        int res = 0;
        for(int i = eO.length-1; i != -1; i--){
            res = res * k + eO[i];
        }
        return res;
    }
}

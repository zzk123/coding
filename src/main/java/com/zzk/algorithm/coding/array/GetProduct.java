package com.zzk.algorithm.coding.array;

/**
 * @ClassName GetProduct
 * @Description 22.不包含本位置值的累乘数组
 * @Author zzk
 * @Date 2021/2/6 15:44
 **/
public class GetProduct {

    /**
     * 问题1：使用除法
     * 时间复杂度为O(N)，除需要返回的结果数组外，额外空间复杂度为O(1)
     * @param arr
     * @return
     */
    public static int[] product1(int[] arr){
        if(arr == null || arr.length < 2){
            return null;
        }
        int count = 0;
        int all = 1;
        for(int i = 0; i != arr.length; i++){
            if(arr[i] != 0){
                all *= arr[i];
            }else{
                count++;
            }
        }
        int[] res = new int[arr.length];
        if(count == 0){
            for(int i = 0; i != arr.length; i++){
                res[i] = all / arr[i];
            }
        }
        if(count == 1){
            for(int i = 0; i != arr.length; i++){
                if(arr[i] == 0){
                    res[i] = all;
                }
            }
        }
        return res;
    }


    /**
     * 问题2：不使用除法
     * 时间复杂度为O(N)，除需要返回的结果数组外，额外空间复杂度为O(1)
     * @param arr
     * @return
     */
    public static int[] product2(int[] arr){
        if(arr == null || arr.length < 2){
            return null;
        }
        int[] res = new int[arr.length];
        res[0] = arr[0];
        for(int i = 1; i < arr.length; i++){
            res[i] = res[i-1] * arr[i];
        }
        int tmp = 1;
        for(int i = arr.length - 1; i > 0; i--){
            res[i] = res[i-1] * tmp;
            tmp *= arr[i];
        }
        res[0] = tmp;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4};
         product1(arr);
    }
}

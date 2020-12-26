package com.zzk.coding.array;

/**
 * @ClassName GetMinLength
 * @Description 5.需要排序的最短子数组长度
 * @Author zzk
 * @Date 2020/12/26 13:39
 **/
public class GetMinLength {

    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param arr
     * @return
     */
    public int getMinLength(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        /**
         * 从右到左遍历，比较min，记录没有排序的值
         */
        int min = arr[arr.length-1];
        int noMinIndex = -1;
        for(int i = arr.length-2; i != -1; i--){
            if(arr[i] > min){
                noMinIndex = i;
            }else{
                min = Math.min(min, arr[i]);
            }
        }
        if(noMinIndex == -1){
            return 0;
        }
        /**
         * 从左到右排序，比较max，记录没有排序的值
         */
        int max = arr[0];
        int noMaxIndex = -1;
        for(int i = 1; i != arr.length; i++){
            if(arr[i] < max){
                noMaxIndex = i;
            }else{
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }
}

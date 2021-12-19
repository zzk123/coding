package com.zzk.sort;

/**
 * @program: alogrithms
 * @description: 计数排序 - https://blog.csdn.net/csdnnews/article/details/83005778
 * @author: zzk
 * @create: 2021-04-01
 */
public class CountSort extends BaseSort{

    /**
     * 计数排序
     * @param arr
     */
    public static Integer[] sort(Integer[] arr){
        //1.得到数组的最大值和最小值。并算出差值d
        int max = arr[0];
        int min = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
            if(arr[i] < min){
                min = arr[i];
            }
        }
        int d = max - min;
        //2.创建统计数组并统计对应元素个数
        int[] countArr = new int[d+1];
        for(int i = 0; i < arr.length; i++){
            countArr[arr[i]-min]++;
        }
        show(countArr);
        //3.统计数组做变形，后面的元素等于前面元素之和（优化变形）
        //countArr存储的值也相当于该元素在新数组中的索引值 -> newArr[countArr[i]] = i
        int sum = 0;
        for(int i = 0; i < countArr.length; i++){
            sum += countArr[i];
            countArr[i] = sum;
        }
        show(countArr);
        //4.倒序遍历原始数组，从统计数组找到正确位置，输出到结果数组
        Integer[] sortedArr = new Integer[arr.length];
        for(int i = arr.length - 1; i >= 0; i--){
            sortedArr[countArr[arr[i]-min]-1] = arr[i];
            countArr[arr[i]-min]--;
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        Integer[] arr = {10,9,8,7,6,5,8,3,0};
        arr = sort(arr);
        show(arr);
    }
}

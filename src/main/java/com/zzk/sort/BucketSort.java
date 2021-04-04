package com.zzk.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: alogrithms
 * @description: 桶排序 - https://blog.csdn.net/csdnsevenn/article/details/83218431
 * @author: zzk
 * @create: 2021-04-01
 */
public class BucketSort extends BaseSort {

    /**
     * 桶排序
     * @param arr
     * @return
     */
    public static double[] bucketSort(double[] arr){
        double max = arr[0];
        double min = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(less(arr[i], max)){
                max = arr[i];
            }
            if(less(min, arr[i])){
                min = arr[i];
            }
        }
        double d = max - min;
        //初始化桶
        int bucketedNum = arr.length;
        List<LinkedList<Double>> bucketList = new ArrayList<>(bucketedNum);
        for(int i = 0; i < bucketedNum; i++){
            bucketList.add(new LinkedList<>());
        }
        //遍历将每个元素放入桶中
        for(int i = 0; i < arr.length; i++){
            int num = (int)((arr[i] - min) * (bucketedNum - 1) / d);
            bucketList.get(num).add(arr[i]);
        }
        //桶内排序
        for(int i = 0; i < bucketList.size(); i++){
            Collections.sort(bucketList.get(i));
        }
        //输出全部元素
        double[] sortedArray = new double[arr.length];
        int index = 0;
        for(LinkedList<Double> list : bucketList){
            for(double element : list){
                sortedArray[index] = element;
                index++;
            }
        }

        return sortedArray;
    }
}

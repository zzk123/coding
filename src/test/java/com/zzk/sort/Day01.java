package com.zzk.sort;

import com.google.gson.Gson;

/**
 * @Description TODO
 * @Author zzk
 * @Dare 2023/4/17
 **/
public class Day01 {

    /**
     * 冒泡算法
     * 稳定算法
     * 时间：O(N^2)
     * 空间：O(N)
     */
    public void bubbleSort(int[] a){
        int n = a.length;
        int flag = 0;
        for(int i=n-1; i>0; i--){
            flag = 0;
            for (int j=0; j<i; j++){
                if(a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    flag = 1;
                }
            }

            if(flag == 0){
                break;
            }
        }
    }

    /**
     * 快速排序
     * 不稳定算法
     * 时间复杂度：最坏 O(N^2) 平均 O(NlogN) 最少 O(logN)
     * @param a
     */
    public void quickSort(int[] a, int l, int r){
        if(l >= r){
            return;
        }
        int i = l;
        int j = r;
        int x = a[i];
        while(i < j){
            //从右到左查询
            while(i < j && a[j] > x){
                j--;
            }
            if(i < j){
                a[i++] = a[j];
            }
            //从左到右查询
            while(i < j && a[i] < x){
                i++;
            }
            if(i < j){
                a[j--] = a[i];
            }
        }
        a[i] = x;
        //左边数据排序
        quickSort(a, l, i-1);
        //右边数据排序
        quickSort(a, i+1, j);
    }

    /**
     * 插入排序 - 稳定排序
     * 时间复杂度：O(N^2)
     * @param a
     */
    public void insertionSort(int[] a){
        int n = a.length;
        int j = 0;
        for(int i=1; i<n; i++){
            for(j=i-1; j>=0; j--){
                if(a[j] < a[i]){
                    break;
                }
            }
            if(j != i-1){
                int tmp = a[i];
                int k;
                for(k=i-1; k>j; k--){
                    a[k+1]=a[k];
                }
                a[k+1] = tmp;
            }
        }
    }

    /**
     * 希尔排序 - 不稳定排序
     * 时间复杂度跟增量(步长gap)有关
     * 当增量为1，退化为直接插入排序，时间复杂度 O(N²)
     * 而Hibbard增量的希尔排序的时间复杂度为 O(N^3/2)
     * @param a
     */
    public void shellSort(int[] a){
        int n = a.length;
        for(int gap=n/2; gap>0; gap/=2){
            for(int i=0; i<gap; i++){
                for(int j=i+gap; j<n; j+=gap){
                    if(a[j] < a[j-gap]){
                        int tmp = a[j];
                        int k = j-gap;
                        while(k >=0 && a[k] > tmp){
                            a[k+gap] = a[k];
                            k -= gap;
                        }
                        a[k+gap] = tmp;
                    }
                }
            }
        }
    }

    /**
     * 选择排序 - 不稳定排序
     * 时间复杂度 O(N^2)
     * @param a
     */
    public void selectionSort(int[] a){
       int n = a.length;
       int min;
       for(int i=0; i<n; i++){
           min = i;
           for(int j=i+1; j<n; j++){
               if(a[j] < a[min]){
                   min = j;
               }
           }
           if(min != i){
               int tmp = a[i];
               a[i] = a[min];
               a[min] = tmp;
           }
       }
    }

}

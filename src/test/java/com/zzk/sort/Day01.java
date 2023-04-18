package com.zzk.sort;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * 堆排序 - 不稳定排序
     * 时间复杂度 O(N*logN)
     * N个数，遍历一趟时间复杂度 O(N)，二叉堆是一棵树，深度介于 lg(N+1) 和 lg(2N)之间，时间复杂度为 O(N*logN)
     * 从小到大
     * @param a
     * @param n
     */
    public void heapSortAsc(int[] a, int n){
       for(int i=n/2-1; i>=0; i--){
           maxHeapDown(a, i, n-1);
       }

       for(int i=n-1; i>=0; i--){
           int tmp = a[0];
           a[0] = a[i];
           a[i] = tmp;

           maxHeapDown(a, 0,i-1);
       }
    }

    /**
     * 初始化堆，最大的在上面
     */
    private void maxHeapDown(int[] a, int start, int end){
        int c = start;
        int l = 2 * c +1;
        int tmp = a[c];

        for(; l<=end; c=l, l=2*l+1){
            if(l < end && a[l] < a[l+1]){
                l++;
            }
            if(tmp >= a[l]){
                break;
            }
            a[c] = a[l];
            a[l] = tmp;

        }
    }

    /**
     * 从大到小排序
     * 降序
     */
    public void heapSortDesc(int[] a){
        int n = a.length;
        for(int i=n/2-1; i>=0; i--){
            minHeapDown(a, i, n-1);
        }

        for(int i=n-1; i>=0; i--){
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;

            minHeapDown(a, 0, i-1);
        }
    }

    private void minHeapDown(int[] a, int start, int end){
        int c = start;
        int l = 2*c+1;
        int tmp = a[c];

        for(; l<=end; c=l, l=2*l+1){
            if(l < end && a[l] > a[l+1]){
                l++;
            }
            if(tmp <= a[l]){
                break;
            }
            a[c] = a[l];
            a[l] = tmp;
        }
    }

    /**
     * 归并算法 - 稳定算法
     * 时间复杂度：O(N*logN)
     * 遍历一趟 O(N)，遍历次数就是二叉树的深度，所以时间复杂度是 O(N*logN)
     */
    public void mergeSortUp2Down(int[] a, int start, int end){
        if(a == null || start >= end){
            return;
        }
        int mid = (start + end)/2;
        mergeSortUp2Down(a, start, mid);
        mergeSortUp2Down(a, mid+1, end);
        merge(a, start, mid, end);
    }

    private void merge(int[] a, int start, int mid, int end){
        int[] tmp = new int[end-start+1];
        int i = start;
        int j = mid+1;
        int k = 0;

        while(i <= mid && j <= end){
            if(a[i] < a[j]){
                a[k++] = a[i++];
            }else{
                a[k++] = a[j++];
            }
        }

        while(i <= mid){
            a[k++] = a[i++];
        }

        while(j <= end){
            a[k++] = a[j++];
        }

        for(i=0; i<k; i++){
            a[start+i] = tmp[i];
        }
        tmp = null;
    }

    /**
     * 桶排序 - 稳定排序
     * 平均时间复杂度 O(n+k)
     * 最佳时间复杂度 O(n+k)
     * 最差时间复杂度 O(n^2)
     * 空间复杂度 O(n*k)
     */
    public void bucketSort(int[] a, int max){
        if(a == null || max < 1){
            return;
        }
        int[] bucket = new int[max];
        for(int i=0; i<a.length; i++){
            bucket[a[i]]++;
        }

        for(int i=0, j=0; i<max; i++){
            while(bucket[i]-- > 0){
                a[j++] = i;
            }
        }
    }

    /**
     * 计数排序 稳定排序
     * 时间复杂度 - O(n+k)
     * k为临时数组的大小
     * 非原地排序
     */
    public int[] inPlaceSort(int[] arr){
        if(arr == null || arr.length < 2){
            return arr;
        }
        int n = arr.length;
        int max = arr[0];
        for(int i=1; i<n; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }

        int[] tmp = new int[max+1];
        for(int i=0; i<n; i++){
            tmp[arr[i]]++;
        }

        int k=0;
        for(int i=0; i<=max; i++){
            for(int j=tmp[i]; j>0; j--){
                arr[k++]=i;
            }
        }
        return arr;
    }


    /**
     * 基数排序
     * 时间复杂度 O(kn)
     * 空间复杂度 O(k+n)
     * 稳定排序
     * 非原地排序
     */
    public int[] radixSort(int[] arr){
        if(arr == null || arr.length < 2){
            return arr;
        }
        int n = arr.length;
        int max = arr[0];

        for(int i=1; i<n; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }

        int num = 1;
        while(max/10 > 0){
            num++;
            max = max/10;
        }

        List<LinkedList<Integer>> bucketList = new ArrayList<>();
        for(int i=0; i<10; i++){
            bucketList.add(new LinkedList<>());
        }

        for(int i=1; i<=num; i++){
            //将数值分组
            for(int j=0; j<n; j++){
                //获取每个数最后第i位是哪个数组的
                int radio = (arr[j] / (int) Math.pow(10, i-1) % 10);
                bucketList.get(radio).add(arr[j]);
            }
            //合并回原来的数组
            int k=0;
            for(int j=1; j<10; j++){
                for(Integer t : bucketList.get(j)){
                    arr[k++] = t;
                }
                bucketList.get(j).clear();
            }
        }
        return arr;

    }
}

package com.zzk.algorithm.sort;

/**
 * @program: alogrithms
 * @description: 堆排序
 * @author: zzk
 * @create: 2021-04-02
 */
public class HeapSort extends BaseSort {


    /**
     * 堆排序 - 使用下沉操作原因是移动的元素个数比上浮的元素个数少一半
     * @param arr
     */
    public static void sort(Comparable[] arr){
        //堆的构造阶段
        int N = arr.length;
        //取的节点总数
        for(int k = N/2; k >= 1; k--){
            //对所有的父节点。从最后一个父节点到根节点。依次下沉排序，完成堆的有序
            sink(arr, k, N);
        }
        //下沉排序阶段
        while(N > 1){
            //将最大元素放到数组后面
            exch(arr, 1, N);
            //将最大元素移除堆
            N--;
            //下沉操作。再次实现堆有序
            sink(arr, 1, N);
        }
    }

    /**
     * 下沉
     * @param arr
     * @param k
     * @param n
     */
    private static void sink(Comparable[] arr, int k, int n){
        System.out.println("下沉开始：");
        show(arr);
        while(2*k <= n){
            System.out.printf("k=%s", k);
            System.out.println();
            int j = 2 * k;
            if(j < n && less(arr, j, j+1)){
                j++;
            }
            if(!less(arr, k, j)){
                break;
            }
            exch(arr, k, j);
            k = j;
            System.out.println("交换后：");
            show(arr);
        }
    }
    /**
     * 比较大小
     * @return
     */
    public static boolean less(Comparable[] arr, int i, int j){
        return less(arr[i-1], arr[j-1]);
    }
    /**
     * 调换位置
     * @param a
     * @param i
     * @aram j
     */
    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    public static void main(String[] args) {
        Comparable[] arr = {666,55,44,2222,445,67,89};
        sort(arr);
        show(arr);
    }
}

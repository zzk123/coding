package com.zzk.algorithm.carl.sort;

import com.google.gson.Gson;

/**
 * 排序算法 - Demo
 */
public class SortDemo {

    /**
     * 相关知识：
     * - 稳定的排序算法：就是能保证排序前两个相等的数在其序列的前后位置顺序与排序后它们两个数在序列的前后位置保持不变，
     * 比如a = b ，排序前a在b的前面，排序后a还是在b的前面。
     * - 不稳定的排序算法： 两个相等的数排序前后位置顺序发生改变，比如a = b，排序前a在b的前面，排序后b在a的前面
     */

    /**
     * 冒泡算法
     * 稳定算法
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N)
     */
    public void bubbleSort(int[] a){
        int n = a.length;
        int flag = 0;
        for(int i=n-1; i>0; i--){
            flag = 0;
            //将 a[0...i]中最大的数据放到末尾
            for(int j=0; j<i; j++){
                if(a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    flag = 1;
                }
            }
            //如果没有发送交换。说明有序
            if(flag == 0){
                break;
            }
        }
    }


    /**
     * 快速排序
     * 不稳定算法
     * 时间复杂度：最坏 O(N^2) 平均 O(N * lgN) 最少 O(lgN)
     */
    public void quickSort(int[] a, int l, int r){
        if(l >= r){
            return;
        }
        int i = l;
        int j = r;
        int x = a[i];
        while(i < j){
            //从右到左查找小于x的数据
            while(i < j && a[j] > x){
                j--;
            }
            if(i < j){
                a[i++] = a[j];
            }
            //从左到右查找大于x的数
            while(i < j && a[i] < x){
                i++;
            }
            if(i < j){
                a[j--] = a[i];
            }
        }
        a[i] = x;
        //左边数据
        quickSort(a, l, i-1);
        //右边数据
        quickSort(a, i+1, r);
    }

    /**
     * 插入排序 - 稳定排序
     * 时间复杂度：O(N^2)
     * 假设被排序的数列中有N个数。遍历一趟的时间复杂度是O(N)，
     * 需要遍历N-1次，因此，直接插入排序的时间复杂度是O(N^2)
     */
    public void insertionSort(int[] a){
        int n = a.length;
        int j = 0;
        for(int i=1; i<n; i++){
            //为 a[i] 在前面 a[0..i-1] 有序区间查找一个合适的位置
            for(j=i-1; j>=0; j--){
                if(a[j] < a[i]){
                    break;
                }
            }

            //找到合适的位置
            if(j != i-1){
                //将比a[i] 大的数据后移
                int tmp = a[i];
                int k;
                for(k = i-1; k > j; j--){
                    a[k+1] = a[k];
                }
                //将a[i] 放到正确的位置
                a[k+1] = tmp;
            }
        }
    }


    /**
     * 希尔排序 - 不稳定排序
     * 希尔排序的时间复杂度与增量(即步长gap)的选取有关。
     * 例如，当增量为1时，希尔排序退化成了直接插入排序，此时的时间复杂度为O(N²)，
     * 而Hibbard增量的希尔排序的时间复杂度为O(N^3/2)
     */
    public void shellSort(int[] a){
        int n = a.length;
        //gap为步长，每次减少为原来的一半
        for(int gap = n/2; gap > 0; gap /= 2){
            //共gap个组。对每一个组进行插入排序
            for(int i=0; i<gap; i++){

                for(int j=i+gap; j<n; j+=gap){
                    //如果a[j] < a[j-gap]，则查找 a[j]位置，并将后面的数据都后移
                    if(a[j] < a[j-gap]){
                        int tmp = a[j];
                        int k = j - gap;
                        while (k >= 0 && a[k] > tmp){
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
     * 选择排序
     * 时间复杂度：O(N^2)
     */
    public void selectionSort(int[] a){
        int n = a.length;
        int min;
        for(int i=0; i<n; i++){
            min = i;
            //查找最小元素
            for(int j=i+1; j<n; j++){
                if(a[j] < a[min]){
                    min = j;
                }
            }
            //i跟min调换位置
            if(min != i){
                int tmp = a[i];
                a[i] = a[min];
                a[min] = tmp;
            }
        }
    }

    /**
     * 堆排序 - 不稳定排序
     * 最大堆升序排序
     * 1、初始化堆：将数列 a[1...n]构造成最大堆
     * 2、交换数据：将a[1]和a[n]交换，使a[n]是a[1...n]中的最大值；
     *           然后将a[1...n-1]重新调整为最大堆。
     *           接着，将a[1]和a[n-1]交换，使a[n-1]是a[1...n-1]中的最大值；
     *           然后将a[1...n-2]重新调整为最大值。
     *           依次类推，直到整个数列都是有序的
     *
     * 堆排序的时间复杂度 O(N*logN)
     * 假设被排序的数列中有N个数，遍历一趟的时间复杂度是 O(N)，需要遍历几次呢？堆排序是采用的二叉堆进行排序的，
     * 二叉堆就是一棵二叉树，他需要遍历的次数就是二叉树的深度，而根据完全二叉树的定义，它的深度至少是 lg(N+1)，最多是多少呢？
     * 由于二叉堆是完全二叉树，因此，它的深度最多不会超过 lg(2N)，因此遍历一趟的时间复杂度是 O(N)，
     * 而遍历次数介于 lg(N+1) 和 lg(2N)之间，所以时间复杂度为 O(N*lgN)
     */
    /**
     * 排序从小到大
     */
    public void heapSortAsc(int[] a, int n){
        //从 (n/2-1 -> 0)初始化，得到一个最大堆
        for(int i=n/2-1; i>=0; i--){
            //从下的父节点到最上面的父节点
            maxHeapDown(a, i, n-1);
        }
        //最后一个元素开始调整
        for(int i=n-1; i>0; i--){
            //交换头部和底部元素。底部为最大元素
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            //重新构建最大堆（0，i-1）
            maxHeapDown(a, 0, i-1);
        }
    }

    /**
     * 初始化堆 - 大的值向上调整
     */
    private void maxHeapDown(int[] a, int start, int end){
        //当前节点
        int c = start;
        //左孩子的位置
        int l = 2 * c + 1;
        //当前节点的值
        int tmp = a[c];

        for(; l<=end; c=l, l=2*l+1){
            //比较左右孩子
            if(l < end && a[l] < a[l+1]){
                l++;
            }
            if(tmp >= a[l]){
                break;
            }
            //交换
            a[c] = a[l];
            a[l] = tmp;
        }
    }

    /**
     * 从大到小排序
     * 降序
     * @param a
     */
    public void heapSortDesc(int[] a){
        int n = a.length;
        //构建最小堆。a[0]为最小值
        for(int i=n/2-1; i>=0; i--){
            minHeapDown(a, i, n-1);
        }

        for(int i=n-1; i>0; i--){
            //交换开头底部元素，使得a[i]最小值
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            //调整堆元素，使得a[0]为 a[0....i-1]中的最小值
            minHeapDown(a, 0, i-1);
        }
    }

    /**
     * 构建最小堆
     * @param a
     * @param start
     * @param end
     */
    private void minHeapDown(int[] a, int start, int end){
        int c = start;
        int l = 2 * c + 1;
        int tmp = a[c];

        for(; l<=end; c=l, l=2*l+1){
            //查找左右节点小的值
            if(l < end && a[l] > a[l+1]){
                l++;
            }
            //比较父节点跟子节点的值，父节点大于子节点就交换
            if(tmp <= a[l]){
                break;
            }
            //交换数据
            a[c] = a[l];
            a[l] = tmp;
        }
    }

    public static void main(String[] args) {
       //堆
        /*int[] ints = {20,30,90,40,70,110,60,10,100,50,80};
        System.out.println(new Gson().toJson(ints));
        int n = ints.length;
        int j=1;
        for(int i=n/2-1; i>=0; i--){
            maxHeapDown(ints, i, n-1);
            System.out.println("第"+ (j++) +"结束");
        }*/
    }

    /**
     * 归并算法 - 稳定算法
     * 时间复杂度：O(N*logN)
     * 假设被排序的数列中有N个数，遍历一趟的时间复杂度是O(N)，需要遍历多少次呢?
     * 归并算法的形式就是一棵二叉树，他需要遍历的次数就是二叉树的深度，而根据完全二叉树可以得出它的时间复杂度是 O(N * logN)
     */
    /**
     * 从上到下
     */
    public void mergeSortUp2Down(int[] a, int start, int end){
        if(a == null || start >= end){
            return;
        }
        int mid = (end + start) / 2;
        //递归排序 a[start....mid]
        mergeSortUp2Down(a, start, mid);
        //递归排序 a[mid+1....end]
        mergeSortUp2Down(a, mid+1, end);
        //合并
        merge(a, start, mid, mid);
    }

    /**
     * 将一个数组中的两个相邻区间合并成一个
     */
    private void merge(int[] a, int start, int mid, int end){
        int[] tmp = new int[end-start+1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        while(i <= mid && j <= end){
            if(a[i] <= a[j]){
                tmp[k++] = a[i++];
            }else{
                tmp[k++] = a[j++];
            }
        }

        while (i <= mid){
            tmp[k++] = a[i++];
        }

        while (j <= end){
            tmp[k++] = a[j++];
        }

        //重新整合到数组a
        for(i=0; i<k; i++){
            a[start+i] = tmp[i];
        }
        tmp = null;
    }
    /**
     * 从下到上
     */
    public void mergeSortDown2Up(int[] a){
        if(a == null){
            return;
        }
        for(int n=1; n<a.length; n*=2){
            mergeGroups(a, a.length, n);
        }
    }

    public void mergeGroups(int[] a, int len, int gap){
        int towlen = 2 * gap;
        int i;
        for(i=0; i+2*gap-1<len; i+=(2*gap)){
            merge(a, i, i+gap-1, i+2*gap-1);
        }

        if(i+gap-1 < len-1){
            merge(a, i, i+gap-1, len-1);
        }
    }
}

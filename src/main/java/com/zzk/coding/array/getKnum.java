package com.zzk.coding.array;

/**
 * @ClassName getKnum
 * @Description 4.找到无序数组中最小的k个数
 * @Author zzk
 * @Date 2020/12/18 23:07
 **/
public class getKnum {

    /**
     * O(Nlogk)的方法=====================
     * 使用堆排序
     */
    public int[] getMinKNumsByHeap(int[] arr, int k){
        if(k < 1 || k > arr.length){
            return arr;
        }
        int[] kHeap = new int[k];
        for(int i = 0; i != k; i++){
            heapInsert(kHeap, arr[i], i);
        }
        for(int i = k; i != arr.length; i++){
            if(arr[i] < kHeap[0]){
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    /**
     * 建堆
     * @param arr
     * @param value
     * @param index
     */
    public void heapInsert(int[] arr, int value, int index){
        arr[index] = value;
        while(index != 0){
            int parent = (index - 1) / 2;
            if(arr[parent] < arr[index]){
                swap(arr, parent, index);
                index = parent;
            }else{
                break;
            }
        }
    }

    /**
     * 调整堆
     * @param arr
     * @param index
     * @param heapSize
     */
    public void heapify(int[] arr, int index, int heapSize){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while(left < heapSize){
            if(arr[left] > arr[index]){
                largest = left;
            }
            if(right < heapSize && arr[right] > arr[largest]){
                largest = right;
            }
            if(largest != index){
                swap(arr, largest, index);
            }else{
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    public void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    /**
     * O(N)的方法
     * 使用BFPRT算法
     * select(arr, k)的过程如下
     *  1.将arr中的n个元素划分成n/5组，每组5个元素，如果最后的组不够5个元素，那么最后剩下的元素组成一组（n%5个元素）
     *  2.对每个组进行插入排序，只针对每个组最多5个元素之间的组内排序，组与组之间并不排序，排序后找到每组的中位数，如果组
     *  的元素个数为偶数，这里规定找下中位数
     *  3.步骤2中一共会找到n/5个中位数，让这些中位数组成一个新的数组，记为mArr。递归调用select(mArr, mArr.length/2)，意义
     *  是找到mArr这个数组中的中位数，即mArr中的第（mArr.length/2）小的数
     *  4.假设步骤3中递归调用select(mArr, mArr.length/2)后，返回的数为x。跟这个x划分整个arr数组
     *    划分过程为：在arr中，比x小的数都在x的左边，大于x的数都在x的右边，x在中间。假设划分完成后，x在arr中的位置记为i
     *  5.如果i==k，说明x为整个数组中第k小的数，直接返回
     *   - 如果i < k，说明x处于第k小的数的左边，应该在x的右边寻找第k小的数，所以递归调用select函数，在右半区寻找第k-i小的数
     *   - 如果i > k,说明x处于第k小的数的右边，应该在x的左边寻找第k小的数，所以递归调用select函数，在左半区寻找第k小的数
     *
     */
    public int select(int[] arr, int begin, int end, int i){
        if(begin == end){
            return arr[begin];
        }
        int pivot = medianOfMedians(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        if(i >= pivotRange[0] && i <= pivotRange[1]){
            return arr[i];
        }else if(i < pivotRange[0]){
            return select(arr, begin, pivotRange[0] - 1, i);
        }else{
            return select(arr, pivotRange[1] + 1, end, i);
        }
    }

    /**
     * 划分n/5组元素，找到中位数
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public int medianOfMedians(int[] arr, int begin, int end){
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for(int i = 0; i < mArr.length; i++){
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return select( mArr, 0, mArr.length-1, mArr.length/2);
    }

    /**
     * 划分区间
     * @param arr
     * @param begin
     * @param end
     * @param pivotValue
     * @return
     */
    public int[] partition(int[] arr, int begin, int end, int pivotValue){
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while(cur != big){
            if(arr[cur] < pivotValue){
                swap(arr, ++small, cur++);
            }else if(arr[cur] > pivotValue){
                swap(arr, cur, --big);
            }else{
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    /**
     * 找到中位数
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public int getMedian(int[] arr, int begin, int end){
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    /**
     * 插入排序
     * @param arr
     * @param begin
     * @param end
     */
    public void insertionSort(int[] arr, int begin, int end){
        for(int i = begin + 1; i != end + 1; i++){
            for(int j =  i; j != begin; j--){
                if(arr[j-1] > arr[j]){
                    swap(arr, j - 1, j);
                }else{
                    break;
                }
            }
        }
    }
}

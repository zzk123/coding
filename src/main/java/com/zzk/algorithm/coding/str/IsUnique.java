package com.zzk.algorithm.coding.str;

/**
 * @ClassName IsUnique
 * @Description 8.判断字符数组中是否所有的字符都只出现过一次
 * @Author zzk
 * @Date 2020/11/14 17:37
 **/
public class IsUnique {

    /**
     * 时间复杂度为O(N)
     * @param chas
     * @return
     */
    public boolean isUnique(char[] chas){
        if(chas == null){
            return true;
        }
        boolean[] map = new boolean[256];
        for(int i = 0; i < chas.length; i++){
            if(map[chas[i]]){
                return false;
            }
            map[chas[i]] = true;
        }
        return true;
    }


    /**
     * 空间复杂度为O(1)，时间复杂度为O(NlogN)
     * @param chas
     * @return
     */
    public boolean isUnique2(char[] chas){
        if(chas == null){
            return true;
        }
        heapSort(chas);
        for(int i = 1; i < chas.length; i++){
            if(chas[i] == chas[i-1]){
                return false;
            }
        }
        return true;
    }

    public void heapSort(char[] chas){
        for(int i = 0; i < chas.length; i++){
            headInsert(chas, i);
        }
        for(int i = chas.length - 1; i > 0; i--){
            swap(chas, 0, i);
            heapify(chas, 0, i);
        }
    }

    public void headInsert(char[] chas, int i){
        int parent = 0;
        while(i != 0){
            parent = (i-1)/2;
            if(chas[parent] < chas[i]){
                swap(chas, parent, i);
                i = parent;
            }else{
                break;
            }
        }
    }

    public void heapify(char[] chas, int i, int size){
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while(left < size){
            if(chas[left] > chas[i]){
                largest = left;
            }
            if(right < size && chas[right] > chas[largest]){
                largest = right;
            }
            if(largest != i){
                swap(chas, largest, i);
            }else{
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    public void swap(char[] chas, int index1, int index2){
        char tmp = chas[index1];
        chas[index1] = chas[index2];
        chas[index2] = tmp;
    }
}

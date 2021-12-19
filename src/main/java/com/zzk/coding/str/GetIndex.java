package com.zzk.coding.str;

/**
 * @ClassName GetIndex
 * @Description 9.在有序但含有空的数组中查找字符串
 * @Author zzk
 * @Date 2020/11/15 13:39
 **/
public class GetIndex {

    /**
     *
     * @param strs
     * @param str
     * @return
     */
    public int getIndex(String[] strs, String str){
        if(strs == null || strs.length == 0 || strs == null){
            return -1;
        }
        int res = -1;
        int left = 0;
        int right = strs.length - 1;
        int mid = 0;
        int i = 0;
        while(left <= right){
            mid = (left + right) / 2;
            if(strs[mid] != null && strs[mid].equals(str)){
                res = mid;
                right = mid - 1;
            }else if(strs[mid] != null){
                if(strs[mid].compareTo(str) < 0){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{
                i = mid;
                while(strs[i] == null && --i >= left){
                    if(i < left || strs[i].compareTo(str) < 0){
                        left = mid + 1;
                    }else{
                        res = strs[i].equals(str) ? i : res;
                        right = i - 1;
                    }
                }
            }
        }
        return res;
    }
}

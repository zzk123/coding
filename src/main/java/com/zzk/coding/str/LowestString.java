package com.zzk.coding.str;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName LowestString
 * @Description 17.拼接所有字符串产生字典顺序最小的大写字符串
 * @Author zzk
 * @Date 2020/11/22 15:37
 **/
public class LowestString{

    class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a+b).compareTo(b+a);
        }
    }

    public String LowestString(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for(int i = 0; i < strs.length; i++){
            res += strs[i];
        }
        return res;
    }
}



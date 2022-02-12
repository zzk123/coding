package com.zzk.algorithm.coding.str;

/**
 * @program: coding
 * @description: 4.判断两个字符串是否互为旋转词
 * @author: zzk
 * @create: 2020-11-11 23:23
 */
public class IsRotation {

    /**
     * 时间复杂度为O(N)
     * @param a
     * @param b
     * @return
     */
    public boolean isRotation(String a, String b){
        if(a == null || b == null || a.length() != b.length()){
            return false;
        }
        //字符串拼接
        String b2 = b + b;
        //判断b2是否包含a
        return getIndexOf(b2, a) != -1;
    }

    // KMP算法
    public int getIndexOf(String s, String m){
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArry(ms);
        while(si < ss.length && mi < ms.length){
            if(ss[si] == ms[mi]){
                si++;
                mi++;
            }else if(next[mi] == -1){
                si++;
            }else{
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public int[] getNextArry(char[] ms){
        if(ms.length == 1){
            return new int[] {-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 1;
        int pos = 2;
        int cn = 0;
        while(pos < next.length){
            if(ms[pos - 1] == ms[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[pos++] = 0;
            }
        }
        return next;
    }
}

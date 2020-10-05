package com.zzk.coding.other;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2020-10-05 14:49
 */
public class getIndexOf {

    /**
     * 返回匹配的位置
     * @param s
     * @param m
     * @return
     */
    public int getIndexOf(String s, String m){
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
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

    /**
     * 根据kmp算法计算出对应的nextArr
     * @param ms
     * @return
     */
    public int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[] {-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
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

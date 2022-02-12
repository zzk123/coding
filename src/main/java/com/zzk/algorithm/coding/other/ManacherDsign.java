package com.zzk.algorithm.coding.other;

/**
 * @ClassName ManacherDsign
 * @Description 30.Manacher算法
 * @Author zzk
 * @Date 2021/3/23 23:14
 **/
public class ManacherDsign {

    public char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for(int i = 0; i != res.length; i++){
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    /**
     * 返回最长回文字符串的长度
     * 时间复杂度为O(N)
     * @param str
     * @return
     */
    public int maxLcpsLength(String str){
        if(str == null || str.length() == 0){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i != charArr.length; i++){
            //判断是否在右边界内
            //如果是在边界内，要么是对应的对称点的值pArr[2 * index - 1]，要么是右边界-i得到的半径
            pArr[i] = pR > i ? Math.min(pArr[2 * index - 1], pR - i) : 1;
            //循环判断半径长度
            while(i + pArr[i] < charArr.length && i - pArr[i] > -1){
                if(charArr[i + pArr[i]] == charArr[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            //判断是否超过右边界。超过则更新右边界
            if(i + pArr[i] > pR){
                pR = i + pArr[i];
                index = i;
            }
            //获得最大半径
            max = Math.max(max, pArr[i]);
        }
        return max-1;
    }

    public String shortestEnd(String str){
        if(str == null || str.length() == 0){
            return null;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int maxContainsEnd = -1;
        for(int i = 0; i != charArr.length; i++){
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while(i + pArr[i] < charArr.length && i - pArr[i] > -1){
                if(charArr[i + pArr[i]] == charArr[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if(i + pArr[i] > pR){
                pR = i + pArr[i];
                index = i;
            }
            if(pR == charArr.length){
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[str.length() - maxContainsEnd + 1];
        for(int i = 0; i < res.length; i++){
            res[res.length - 1 -i] = charArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }
}

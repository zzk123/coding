package com.zzk.coding.str;

/**
 * @program: coding
 * @description: 5.将整数字符串转成整数值
 * @author: zzk
 * @create: 2020-11-10 21:54
 */
public class NumSum {

    /**
     * 时间复杂度为O(N)、空间复杂度为O(1)
     * @param str
     * @return
     */
    public int numSum(String str){
        if(str == null){
            return 0;
        }
        char[] charArr = str.toCharArray();
        int res = 0;
        int num = 0;
        boolean posi = true;
        int cur = 0;
        for(int i = 0; i < charArr.length; i++){
            cur = charArr[i] - '0';
            //数字字符判断
            if(cur < 0 || cur > 9){
                res += num;
                num = 0;
                //奇数偶数符号判断
                if(charArr[i] == '-'){
                    if(i - 1 > -1 && charArr[i-1] == '-'){
                        posi = !posi;
                    }else{
                        posi = false;
                    }
                }else{
                    posi = true;
                }
            }else{
                num = num * 10 + (posi ? cur : -cur);
            }
        }
        res += num;
        return res;
    }
}

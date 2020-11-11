package com.zzk.coding.str;

/**
 * @program: coding
 * @description:  5.将整数字符串转成整数值
 * @author: zzk
 * @create: 2020-11-11 23:31
 */
public class Convert {

    /**
     * 检查str是否符合日常书写的整数形式
     * 1. 如果str不以"-"开头，也不以数字字符开头，例如，str = "A12"，返回false
     * 2. 如果str以"-"开头，但是str的长度为1，即str=="-"，返回false，如果str的长度大于1，但是"-"的后面紧跟着"0"，
     *    例如，str == "-0" 或 "-012"，返回false
     * 3. 如果str以"0"开头。但是str的长度大于1，例如，str=="023"，返回false
     * 4. 如果经过步骤1~3都没有返回，接下来检查str[1...N-1]是否都是数字字符，如果有一个不是数字字符，返回false，
     *    如果都是数字字符，说明str符合日常书写，返回true
     * @param chas
     * @return
     */
    public boolean isValid(char[] chas){
        if(chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')){
            return false;
        }
        if(chas[0] == '-' && (chas.length == 1 || chas[1] == '0')){
            return false;
        }
        if(chas[0] == '0' && chas.length > 1){
            return false;
        }
        for(int i = 1; i < chas.length; i++){
            if(chas[i] < '0' || chas[i] > '9'){
                return false;
            }
        }
        return true;
    }


    /**
     *
     *
     * @param str
     * @return
     */
    public int convert(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chas = str.toCharArray();
        if(!isValid(chas)){
            return 0;
        }
        boolean posi = chas[0] == '-' ? false : true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for(int i = posi ? 0 : 1; i < chas.length; i++){
            cur = '0' - chas[i];
            if((res < minq) || (res == minq && cur < minr)){
                return 0;
            }
            res = res * 10 + cur;
        }
        if(posi && res == Integer.MIN_VALUE){
            return 0;
        }
        return posi ? -res : res;
    }
}

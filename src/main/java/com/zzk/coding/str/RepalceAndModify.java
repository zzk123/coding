package com.zzk.coding.str;

/**
 * @ClassName RepalceAndModify
 * @Description 10.字符串的调整与替换
 * @Author zzk
 * @Date 2020/11/15 14:11
 **/
public class RepalceAndModify {

    /**
     * 时间复杂度为O(N)，额外空间复杂度为O(1)
     * @param chas
     */
    public void replace(char[] chas){
        if(chas == null || chas.length == 0){
            return;
        }
        int num = 0;
        int len = 0;
        for(len = 0; len < chas.length && chas[len] != 0; len++){
            if(chas[len] == ' '){
                num++;
            }
        }
        int j = len + num * 2 - 1;
        for(int i = len - 1; i > -1; i--){
            if(chas[i] != ' '){
                chas[j--] = chas[i];
            }else{
                chas[j--] = '0';
                chas[j--] = '2';
                chas[j--] = '%';
            }
        }
    }


    /**
     * 时间复杂度为O(N)，空间复杂度为O(1)
     * @param chas
     */
    public void modify(char[] chas){
        if(chas == null || chas.length == 0){
            return;
        }
        int j = chas.length - 1;
        for(int i = chas.length - 1; i > -1; i--){
            if(chas[i] != '*'){
                chas[j--] = chas[i];
            }
        }
        for(; j > -1;){
            chas[j--] = '*';
        }
    }
}

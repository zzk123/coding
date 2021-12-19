package com.zzk.coding.str;

/**
 * @ClassName Rotate
 * @Description 11.翻转字符串
 * @Author zzk
 * @Date 2020/11/15 14:58
 **/
public class Rotate {

    public void rotateWord(char[] chas){
        if(chas == null || chas.length == 0){
            return;
        }
        reverse(chas, 0, chas.length-1);
        int l = -1;
        int r = -1;
        for(int i = 0; i < chas.length; i++){
            if(chas[i] != ' '){
                l = i == 0 || chas[i-1] == ' ' ? i : l;
                r = i == chas.length - 1 || chas[i+1] == ' ' ? i : r;
            }
            if(l != -1 && r != -1){
                reverse(chas, l, r);
                l = -1;
                r = -1;
            }
        }
    }

    /**
     * 字符串组逆序
     * @param chas
     * @param start
     * @param end
     */
    public void reverse(char[] chas, int start, int end){
        char tmp = 0;
        while(start < end){
            tmp = chas[start];
            chas[start] = chas[end];
            chas[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 方法1
     * @param chas
     * @param size
     */
    public void rotate1(char[] chas, int size){
        if(chas == null || size <= 0 || size >= chas.length){
            return;
        }
        reverse(chas, 0, size-1);
        reverse(chas, size, chas.length-1);
        reverse(chas, 0, chas.length - 1);
    }

    /**
     * 方法2
     * @param chas
     * @param size
     */
    public void rotate2(char[] chas, int size){
        if(chas == null || size < 0 || size >= chas.length){
            return;
        }
        int start = 0;
        int end = chas.length - 1;
        int lpart = size;
        int rpart = chas.length - size;
        int s = Math.min(lpart, rpart);
        int d = lpart - rpart;
        while(true){
            exchange(chas, start, end, s);
            if(d == 0){
                break;
            }else if(d > 0){
                start += s;
                lpart = d;
            }else{
                end -= s;
                rpart = -d;
            }
            s = Math.min(lpart, rpart);
            d = lpart - rpart;
        }
    }

    public void exchange(char[] chas, int start, int end, int size){
        int i = end - size + 1;
        char tmp  = 0;
        while(size-- != 0){
            tmp = chas[start];
            chas[start] = chas[i];
            chas[i] = tmp;
            start++;
            i++;
        }
    }
}

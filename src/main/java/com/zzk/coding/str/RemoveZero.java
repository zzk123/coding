package com.zzk.coding.str;

/**
 * @program: coding
 * @description: 3.去掉字符串中连续出现k个0的子串
 * @author: zzk
 * @create: 2020-11-10 22:39
 */
public class RemoveZero {

    public static String removeKZeros(String str, int k){
        if(str == null || k < 1){
            return str;
        }
        char[] chas = str.toCharArray();
        int count = 0, start = -1;
        for(int i = 0; i != chas.length; i++){
            if(chas[i] == '0'){
                count++;
                start = start == -1 ? i : start;
            }else{
                if(count == k){
                    while(count-- != 0){
                        //这个0 不是字符‘0‘，字符‘0‘的阿西克码是48, 这个0 的阿西克码就是0，‘A00B‘-->‘A  B‘再转换成字符串就是‘AB‘
                        chas[start++] = 0;
                    }
                    count = 0;
                    start = -1;
                }
            }
        }
        if(count == k){
            while(count-- != 0){
                //这个0 不是字符‘0‘，字符‘0‘的阿西克码是48, 这个0 的阿西克码就是0，‘A00B‘-->‘A  B‘再转换成字符串就是‘AB‘
                chas[start++] = 0;
            }
        }
        return String.valueOf(chas);
    }

    public static void main(String[] args) {
        String str = "A00B00";
        int k = 2;
        System.out.print(removeKZeros(str, k));
    }
}

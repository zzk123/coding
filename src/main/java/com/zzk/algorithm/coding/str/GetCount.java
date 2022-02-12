package com.zzk.algorithm.coding.str;

/**
 * @ClassName GetCount
 * @Description 7.字符串的统计字符串
 * @Author zzk
 * @Date 2020/11/14 16:11
 **/
public class GetCount {

    public String getCountString(String str){
        if(str == null || str.equals("")){
            return "";
        }
        char[] chs = str.toCharArray();
        String res = String.valueOf(chs[0]);
        int num = 1;
        for(int i = 0; i < chs.length; i++){
            if(chs[i] != chs[i-1]){
                res = concat(res, String.valueOf(num), String.valueOf(chs[i]));
                num = 1;
            }else{
                num++;
            }
        }
        return concat(res, String.valueOf(num), "");
    }

    public String concat(String s1, String s2, String s3){
        return s1 + "_" + s2 + "_" + s3;
    }


    public char getCharAt(String cstr, int index){
        if(cstr == null || cstr.equals("")){
            return 0;
        }
        char[] chs = cstr.toCharArray();
        boolean stage = true;
        char cur = 0;
        int num = 0;
        int sum = 0;
        for(int i = 0; i != chs.length; i++){
            if(chs[i] == '_'){
                stage = !stage;
            }else if(stage){
                sum += num;
                if(sum > index){
                    return cur;
                }
                num = 0;
                cur = chs[i];
            }else{
                //字符转数字
                num = num * 10 + chs[i] - '0';
            }
        }
        return sum + num > index ? cur : 0;
    }

}

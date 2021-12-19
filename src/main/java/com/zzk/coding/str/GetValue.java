package com.zzk.coding.str;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName GetValue
 * @Description 15.公式字符串求值
 * @Author zzk
 * @Date 2020/11/21 16:35
 **/
public class GetValue {

    public static int getValue(String exp){
        return value(exp.toCharArray(), 0)[0];
    }

    public static int[] value(char[] chars, int i){
        Deque<String> deq = new LinkedList<>();
        int pre = 0;
        int[] bra = null;
        while(i < chars.length && chars[i] != ')'){
            if(chars[i] >= '0' && chars[i] <= '9'){
                pre = pre * 10 + chars[i++] - '0';
            }else if(chars[i] != '('){
                addNum(deq, pre);
                deq.addLast(String.valueOf(chars[i++]));
                pre = 0;
            }else{
                bra = value(chars, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(deq, pre);
        return new int[] {getNum(deq), i};
    }

    /**
     * 创建公式。入栈
     * @param deq
     * @param num
     */
    public static void addNum(Deque<String> deq, int num){
        if(!deq.isEmpty()){
            int cur = 0;
            String top = deq.pollLast();
            if(top.equals("+") || top.equals("-")){
                deq.addLast(top);
            }else{
                cur = Integer.valueOf(deq.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        deq.addLast(String.valueOf(num));
    }

    /**
     * 结果获取
     * @param deq
     * @return
     */
    public static int getNum(Deque<String> deq){
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while(!deq.isEmpty()){
            cur = deq.pollFirst();
            if(cur.equals("+")){
                add = true;
            }else if(cur.equals("-")){
                add = false;
            }else{
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "1+2+(3-4)";
        getValue(str);
    }
}

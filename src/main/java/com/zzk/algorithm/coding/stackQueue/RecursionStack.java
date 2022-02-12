package com.zzk.algorithm.coding.stackQueue;

import java.util.Stack;

/**
 * @program: 3.如何仅用递归函数和栈操作逆序一个栈
 * @description:
 * @author: zzk
 * @create: 2020-05-30 21:42
 */
public class RecursionStack {

    /**
     * 将栈底元素返回并删除
     * @param stack
     * @return
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * 逆序一个栈
     * @param stack
     */
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }
}

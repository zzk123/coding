package com.zzk.algorithm.coding.stackQueue;

import java.util.Stack;

/**
 * @program: coding
 * @description: 5.用一个栈实现另一个栈的排序
 * @author: zzk
 * @create: 2020-06-01 23:35
 */
public class SortStack {

    public static void sortStackByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<Integer>();
        while(!stack.isEmpty()){
            int cur = stack.pop();
            //对比栈顶元素。是否比栈顶元素大
            while(!help.isEmpty() && help.peek() < cur){
                stack.push(help.pop());
            }
            //小的放入help
            help.push(cur);
        }
        //排完序重新放回栈中
        while(!help.isEmpty()){
            stack.push(help.pop());
        }
    }
}

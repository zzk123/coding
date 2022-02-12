package com.zzk.algorithm.coding.stackQueue;

import java.util.Stack;

/**
 * @program: coding
 * @description:  2.由两个栈组成的队列
 * @author: zzk
 * @create: 2020-05-30 21:32
 */
public class TwoStackQueue {

    public Stack<Integer> stackPush;

    public Stack<Integer> stackPop;

    public TwoStackQueue(){
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void add(int pushInt){
        stackPush.push(pushInt);
    }

    public int poll(){
        if(stackPush.isEmpty() && stackPop.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else if(stackPop.empty()){
            while (!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
       return stackPop.pop();
    }

    public int peek(){
        if(stackPush.isEmpty() && stackPop.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else if(stackPop.empty()){
            while (!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
        return  stackPop.peek();
    }

}

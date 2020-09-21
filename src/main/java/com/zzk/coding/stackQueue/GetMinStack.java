package com.zzk.coding.stackQueue;

import java.util.Stack;

/**
 * @program: coding
 * @description: 1. 设计一个有getMin功能的栈
 * @author: zzk
 * @create: 2020-05-30 00:31
 */
public class GetMinStack {

    /**
     * 基础接口定义
     */
    public interface StackBase{
        /**
         * 入栈
         * @param newNum
         */
        public void push(int newNum);

        /**
         * 出栈
         */
        public int pop();

        /**
         * 获得最小值
         * @return
         */
        public int getMin();
    }


    /**
     * 1.如果stackData为空，则newNum也会压入stackMin
     * 2.如果不为空，则比较newNum和stackMin的栈顶元素中哪个更小
     * 3.如果newNum更小或两者相等，则newNum也会压入stackMin
     * 4.如果stackMin中栈堆元素更小，则stackMin不压入任何内容
     */
    public class MyStack1 implements StackBase{

        private Stack<Integer> stackData;

        private Stack<Integer> stackMin;

        public MyStack1(){
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        @Override
        public void push(int newNum){
            if(this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if(newNum < this.getMin()){
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        @Override
        public int pop() {
            if(this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            int value = this.stackData.pop();
            if(value == this.getMin()){
                this.stackMin.pop();
            }
            return value;
        }

        public int getMin(){
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            return this.stackMin.peek();
        }
    }

    /**
     * 如果当前数据为newNum，先将其压入stackData。然后判断stackMin是否为空
     * 如果为空，则newNum也压入stackMin;如果不为空，则比较newNum和stackMin的栈顶元素中哪个更小
     * 如果newNum更小或者两者相等，则newNum也压入stackMin
     * 如果stackMin中栈顶元素小，则把stackMin的栈顶元素重复压入stackMin，即在栈顶元素上再压入一个栈顶元素
     */
    public  class MyStack2 implements StackBase{

        private Stack<Integer> stackData;

        private Stack<Integer> stackMin;

        public MyStack2(){
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        @Override
        public void push(int newNum) {
            if(this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if(newNum < this.getMin()){
                this.stackMin.push(newNum);
            }else{
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
        }

        @Override
        public int pop() {
            if(this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        @Override
        public int getMin() {
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            return this.stackMin.peek();
        }
    }
}

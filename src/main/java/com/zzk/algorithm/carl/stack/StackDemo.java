package com.zzk.algorithm.carl.stack;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * 单调栈
 */
public class StackDemo {


    /**
     * 1、单调栈模板，时间复杂度 O(N)
     * 数组 nums = [2, 1, 2, 4, 3]
     * 返回 [4, 2, 4, -1, -1]
     * 从数据后面开始元素入栈，遇到大于栈顶值的弹出
     */
    public static Integer[] nextGreaterElement(List<Integer> nums){
        Integer[] ans = new Integer[nums.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = nums.size() - 1; i >= 0; i--){
            while(!s.empty() && s.lastElement() <= nums.get(i)){
                s.pop();
            }
            s.forEach(n -> System.out.print(n + ","));
            System.out.println();
            ans[i] = s.empty() ? -1 : s.lastElement();
            s.push(nums.get(i));
        }
        return ans;
    }

    /**
     * 2、
     *  时间复杂度 O(N)
     *  T = [73, 74, 75, 71, 69, 72, 76, 73]
     * 返回 [1, 1, 4, 2, 1, 1, 0, 0] - 索引间隔
     */
    public static Integer[] dailyTemperatures(List<Integer> nums){
        Integer[] ans = new Integer[nums.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = nums.size()-1; i >= 0; i--){
            while(!s.empty() && nums.get(s.lastElement()) <= nums.get(i)){
                s.pop();
            }
            ans[i] = s.empty() ? 0 : s.lastElement() - i;
            s.push(i);
        }
        return ans;
    }

    /**
     *
     * 3、假设是环形的数组，找下一个比自身大的值
     * 数组 [2, 1, 2, 4, 3]
     * 返回 [4, 2, 4, -1, 4]
     * ------- 通过求模（余数）
     * 时间复杂度 O(N)
     */
    public static Integer[] nextGreaterElement2(List<Integer> nums){
        int n = nums.size();
        Integer[] ans = new Integer[nums.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = 2 * n - 1; i >= 0; i--){
            while (!s.empty() && s.lastElement() <= nums.get(i % n)){
                s.pop();
            }
            ans[i % n] = s.empty() ? -1 : s.lastElement();
            s.push(nums.get(i % n));
        }
        return ans;
    }

    /**
     * 4、用栈实现队列
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（`push`、`pop`、`peek`、`empty`）：
     * 实现 `MyQueue` 类：
     * - `void push(int x)` 将元素 x 推到队列的末尾
     * - `int pop()` 从队列的开头移除并返回元素
     * - `int peek()` 返回队列开头的元素
     * - `boolean empty()` 如果队列为空，返回 `true` ；否则，返回 `false`
     *
     * 输入：
     * ["MyQueue", "push", "push", "peek", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null, null, null, 1, 1, false]
     *
     * 解释：
     * MyQueue myQueue = new MyQueue();
     * myQueue.push(1); // queue is: [1]
     * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
     * myQueue.peek(); // return 1
     * myQueue.pop(); // return 1, queue is [2]
     * myQueue.empty(); // return false
     */
    class MyQueue{
        Stack<Integer> stackIn;
        Stack<Integer> stackOut;

        public MyQueue(){
            stackIn = new Stack<>();
            stackOut = new Stack<>();
        }

        public void push(int x){
            stackIn.push(x);
        }

        public int pop(){
            dumpstackIn();
            return stackOut.pop();
        }

        public int peek(){
            dumpstackIn();
            return stackOut.peek();
        }

        public boolean empty(){
            return stackIn.isEmpty() && stackOut.isEmpty();
        }

        private void dumpstackIn(){
            if(!stackOut.isEmpty()){
                return;
            }
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }

    }

    /**
     * 5、用队列实现栈
     * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（`push`、`top`、`pop` 和 `empty`）。
     * 实现 `MyStack` 类：
     * - `void push(int x)` 将元素 x 压入栈顶。
     * - `int pop()` 移除并返回栈顶元素。
     * - `int top()` 返回栈顶元素。
     * - `boolean empty()` 如果栈是空的，返回 `true` ；否则，返回 `false`
     *
     * 输入：
     * ["MyStack", "push", "push", "top", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null, null, null, 2, 2, false]
     * 解释：
     * MyStack myStack = new MyStack();
     * myStack.push(1);
     * myStack.push(2);
     * myStack.top(); // 返回 2
     * myStack.pop(); // 返回 2
     * myStack.empty(); // 返回 False
     */
    class MyStack{
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack(){
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x){
            queue2.offer(x);
            while(!queue1.isEmpty()){
                queue2.offer(queue1.poll());
            }
            Queue<Integer> queueTemp;
            queueTemp = queue1;
            queue1 = queue2;
            queue2 = queueTemp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll(); // 因为queue1中的元素和栈中的保持一致，所以这个和下面两个的操作只看queue1即可
        }

        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }

    }

    /**
     * 6、有效的括号
     * 给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串 `s` ，判断字符串是否有效。
     * 有效字符串需满足：
     * 1. 左括号必须用相同类型的右括号闭合。
     * 2. 左括号必须以正确的顺序闭合。
     *
     * 输入：s = "()"
     * 输出：true
     */
    public boolean isValid(String s){
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for(int i=0; i<s.length(); i++){
            ch = s.charAt(i);
            if(ch == '('){
                deque.push(')');
            }else if(ch == '{'){
                deque.push('}');
            }else if(ch == '['){
                deque.push(']');
            }else if(deque.isEmpty() || deque.peek() != ch){
                return false;
            }else{
                deque.pop();
            }
        }
        return deque.isEmpty();
    }

    /**
     * 7、删除字符串中的所有相邻重复项
     * 给出由小写字母组成的字符串 `S`，**重复项删除操作**会选择两个相邻且相同的字母，并删除它们。
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一
     */

    /**
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     */
    public String removeDuplicates(String s){
        ArrayDeque<Character> deque = new ArrayDeque<>();
        char ch;
        for(int i=0; i<s.length(); i++){
            ch = s.charAt(i);
            if(deque.isEmpty() || deque.peek() != ch){
                deque.push(ch);
            }else{
                deque.pop();
            }
        }
        String str = "";
        while(!deque.isEmpty()){
            str = deque.pop() + str;
        }
        return str;
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(2,1,2,4,5);
        System.out.println(JSONObject.toJSONString(nextGreaterElement(nums)));
        System.out.println(JSONObject.toJSONString(dailyTemperatures(nums)));
    }
}

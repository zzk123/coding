package com.zzk.algorithm.carl.stack;
import com.google.gson.Gson;

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

    /**
     * 8、每日温度
     * 给定一个整数数组 `temperatures` ，表示每天的温度，返回一个数组 `answer` ，其中 `answer[i]` 是指对于第 `i` 天，
     * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 `0` 来代替。
     *
     * **示例 1:**
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     */
    /**
     * 版本1 - 单调栈
     */
    public int[] dailyTemperatures(int[] temperatures){
        int lens = temperatures.length;
        int[] res = new int[lens];

        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for(int i=1; i<lens; i++){
            if(temperatures[i] <= temperatures[stack.peek()]){
                stack.push(i);
            }else{
                while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }

    /**
     * 版本2
     */
    public int[] dailyTemperatures2(int[] temperatures){
        int lens = temperatures.length;
        int[] res = new int[lens];
        Deque<Integer> stack = new LinkedList<>();
        for(int i=0; i<lens; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 9、下一个更大元素 I
     * `nums1` 中数字 `x` 的 **下一个更大元素** 是指 `x` 在 `nums2` 中对应位置 **右侧** 的 **第一个** 比 `x` 大的元素。
     * 给你两个 **没有重复元素** 的数组 `nums1` 和 `nums2` ，下标从 **0** 开始计数，其中`nums1` 是 `nums2` 的子集。
     * 对于每个 `0 <= i < nums1.length` ，找出满足 `nums1[i] == nums2[j]` 的下标 `j` ，并且在 `nums2` 确定 `nums2[j]` 的 **下一个更大元素** 。如果不存在下一个更大元素，那么本次查询的答案是 `-1` 。
     * 返回一个长度为 `nums1.length` 的数组 `ans` 作为答案，满足 `ans[i]` 是如上所述的 **下一个更大元素** 。
     *
     * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出：[-1,3,-1]
     * 解释：nums1 中每个值的下一个更大元素如下所述：
     * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
     * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
     * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2){
        Stack<Integer> temp = new Stack<>();
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        //使用map存储 nums1的元素信息
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i=0; i<nums1.length; i++){
            hashMap.put(nums1[i], i);
        }
        temp.add(0);
        //比较 nums2中的数据
        for(int i=1; i<nums2.length; i++){
            if(nums2[i] <= nums2[temp.peek()]){
                temp.add(i);
            }else{
                //找到比头部大的元素。进行出栈处理
                while(!temp.isEmpty() && nums2[temp.peek()] < nums2[i]){
                    if(hashMap.containsKey(nums2[temp.peek()])){
                        Integer index = hashMap.get(nums2[temp.peek()]);
                        res[index] = nums2[i];
                    }
                    temp.pop();
                }
                temp.add(i);
            }
        }
        return res;
    }

    /**
     * 10、下一个更大元素 II
     * 给定一个循环数组 `nums` （ `nums[nums.length - 1]` 的下一个元素是 `nums[0]` ），返回 *`nums` 中每个元素的 **下一个更大元素*** 。
     * 数字 `x` 的 **下一个更大的元素** 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 `-1` 。
     *
     * 输入: nums = [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     */
    public int[] nextGreaterElements(int[] nums){
        if(nums == null || nums.length <= 1){
            return new int[]{-1};
        }
        int size = nums.length;
        int[] result = new int[size];
        Arrays.fill(result, -1);
        Stack<Integer> st = new Stack<>();
        //模拟两个数组长度
        for(int i=0; i<2*size; i++){
            //每次判断i的下一个元素是否比栈顶大
            while(!st.isEmpty() && nums[i%size] > nums[st.peek()]){
                result[st.peek()] = nums[i%size];
                st.pop();
            }
            st.push(i%size);
        }
        return result;
    }

    /**
     * 11、接雨水
     * 给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
     *
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     */
    public int trap(int[] height){
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for(int i=0; i<n; i++){
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                int top = stack.pop();
                if(stack.isEmpty()){
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];

                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}

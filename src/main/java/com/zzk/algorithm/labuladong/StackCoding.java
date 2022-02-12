package com.zzk.algorithm.labuladong;

import java.util.*;

/**
 * @program: coding
 * @description: 单调栈相关
 * @author: zzk
 * @create: 2021-12-12 21:46
 */
public class StackCoding {

    /**
     * 单调栈的模板
     * 数组 nums = [2, 1, 2, 4, 3]
     *  返回 [4, 2, 4, -1, -1]
     *  解释：第一个2后面比2大的数是4；1后面比1的的数是2；第二个2后面比2大的数是4；4后面没有比4大的数是-1
     * @param nums
     * @return
     */
    public static Integer[] nextGreaterElement(List<Integer> nums){
        Integer[] ans = new Integer[nums.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = nums.size()-1; i >= 0; i--){
            while(!s.empty() && s.lastElement() <= nums.get(i)){
                s.pop();
            }
            s.forEach(n-> System.out.print(n + ",  "));
            System.out.println();
            ans[i] = s.empty() ? -1 : s.lastElement();
            s.push(nums.get(i));
        }
        return ans;
    }

    /**
     *  T = [73, 74, 75, 71, 69, 72, 76, 73]
     * 返回 [1, 1, 4, 2, 1, 1, 0, 0]
     * @param nums
     * @return
     */
    public static Integer[] dailyTemperatures(List<Integer> nums){
        Integer[] ans = new Integer[nums.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = nums.size()-1; i >= 0; i--){
            while(!s.empty() && nums.get(s.lastElement()) <= nums.get(i)){
                s.pop();
            }
            ans[i] = s.empty() ? 0 : s.lastElement() - i;  //得到索引间距
            s.push(i); //加入的是索引，而不是元素
        }
        return ans;
    }

    /**
     * 假设是环形的数组
     * 数组 [2, 1, 2, 4, 3]
     * 返回 [4, 2, 4, -1, 4]
     * 通过求模（余数）
     * @param nums
     * @return
     */
    public static Integer[] nextGreaterElements(List<Integer> nums){
        int n = nums.size();
        Integer[] ans = new Integer[nums.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = 2 * n - 1; i >= 0; i--){
            while(!s.empty() && s.lastElement() <= nums.get(i % n)){
                s.pop();
            }
            ans[i % n] = s.empty() ? -1 : s.lastElement();
            s.push(nums.get(i % n));
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        List<Integer> nums = Arrays.asList(2,1,2,4,3);
        Integer[] intNums =  nextGreaterElements(nums);
        System.out.println("输出结果：");
        Arrays.stream(intNums).forEach(n-> System.out.print(n + ",  "));
    }
}

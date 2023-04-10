package com.zzk.algorithm.carl.string;

import java.util.*;

/**
 * 字符串Demo
 */
public class StringDemo {

    /**
     * 1、反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 `s` 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须**[原地](https://baike.baidu.com/item/原地算法)修改输入数组**、使用 O(1) 的额外空间解决这一问题。
     *
     * 输入：s = ["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     */
    public void reverseString(char[] s){
        int n = s.length;
        for(int left = 0, right = n-1; left < right; ++left, --right){
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    /**
     * 2、反转字符串 II
     * 给定一个字符串 `s` 和一个整数 `k`，从字符串开头算起，每计数至 `2k` 个字符，就反转这 `2k` 字符中的前 `k` 个字符。
     * - 如果剩余字符少于 `k` 个，则将剩余字符全部反转。
     * - 如果剩余字符小于 `2k` 但大于或等于 `k` 个，则反转前 `k` 个字符，其余字符保持原样。
     *
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     */
    public String reverseStr(String s, int k){
        int n = s.length();
        char[] arr = s.toCharArray();
        for(int i=0; i<n; i++){
            reverse(arr, i, Math.min(i+k, n)-1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right){
        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 3、替换空格
     * 请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。
     */
    public String replaceSpace(String s){
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for(int i=0; i<length; i++){
            char c = s.charAt(i);
            if(c == ' '){
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            }else{
                array[size++] = c;
            }
        }
        return new String(array, 0, size);
    }

    /**
     * 4、颠倒字符串中的单词
     * 给你一个字符串 `s` ，颠倒字符串中 **单词** 的顺序。
     * **单词** 是由非空格字符组成的字符串。`s` 中使用至少一个空格将字符串中的 **单词** 分隔开
     *
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     */
    public String reveseWords(String s){
        //首尾空格删除
        s = s.trim();
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0){
            //搜索首个空格
            while(i >= 0 && s.charAt(i) != ' '){
                i--;
            }
            //添加单词
            res.append(s.substring(i+1, j+1) + " ");
            //跳过单词间空格
            while(i >= 0 && s.charAt(i) == ' '){
                i--;
            }
            //j 指向下一个单词的尾字符串
            j = i;
        }
        return res.toString().trim();
    }

    /**
     * 5、左旋转字符串
     *
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"
     *
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     */
    public String reverseLeftWords(String s, int n){
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        //反转n前面的字符串
        reverseString(sb, 0, n-1);
        //反转n后面的字符串
        reverseString(sb, n, len-1);
        //反转整一个字符串
        return sb.reverse().toString();
    }

    public void reverseString(StringBuilder sb, int start, int end){
        while(start < end){
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    /**
     * 6、找出字符串中第一个匹配项的下标
     * 给你两个字符串 `haystack` 和 `needle` ，请你在 `haystack` 字符串中找出 `needle` 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 `-1` 。
     *
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     *
     */
    /**
     * 朴素算法
     */
    public int strStr(String ss, String pp){
        int n = ss.length(), m = pp.length();
        char[] s = ss.toCharArray(), p = pp.toCharArray();
        for(int i = 0; i <= n-m; i++){
            int a = i, b = 0;
            while(b < m && s[a] == p[b]){
                a++;
                b++;
            }
            if(b == m){
                return i;
            }
        }
        return -1;
    }

    /**
     * KMP算法
     */
    public int strStrKMP(String ss, String pp){
        if(pp.isEmpty()){
            return 0;
        }
        int n = ss.length(), m = pp.length();
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        int[] next = new int[m+1];
        //构建 next 数组，数组长度为匹配串的长度
        //从i=2，j=0开始，i小于等于匹配串长度
        for(int i=2, j=0; i<=m; i++){
            //匹配不成功，j = next[j]
            while(j > 0 && p[i] != p[j+1]){
                j = next[j];
            }
            //匹配成功，先 j++
            if(p[i] == p[j+1]){
                j++;
            }
            //更新next[i]，结束循环，i++
            next[i] = j;
        }
        //匹配过程，i=1，j=0开始
        for(int i=1, j=0; i<=n; i++){
            //匹配不成功。j = next[j]
            while(j > 0 && s[i] != p[j+1]){
                j = next[j];
            }
            //匹配成功，j++
            if(s[i] == p[j+1]){
                j++;
            }
            if(j == m){
                return i-m;
            }
        }
        return -1;
    }

    /**
     * 7、重复的子字符串
     * 给定一个非空的字符串 `s` ，检查是否可以通过由它的一个子串重复多次构成。
     *
     * 输入: s = "abab"
     * 输出: true
     * 解释: 可由子串 "ab" 重复两次构成。
     */
    public boolean repeatedSubStringPattern(String s){
        if("".equals(s)){
            return false;
        }
        int len = s.length();
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len+1];

        for(int i=2, j=0; i<=len; i++){
            while(j>0 && chars[i] != chars[j+1]){
                j = next[j];
            }

            if(chars[i] == chars[j+1]){
                j++;
            }
            next[i] = j;
        }
        //如果 len % (len - (next[len-1]+1)) == 0，说明该字符串有重复的子字符串
        if(next[len] > 0 && len % (len - next[len]) == 0){
            return true;
        }
        return false;
    }

    /**
     * 8、逆波兰表达式求值
     * 根据逆波兰表示法，求表达式的值。
     * 有效的算符包括 `+`、`-`、`*`、`/` 。每个运算对象可以是整数，也可以是另一个逆波兰表达式
     *
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     */
    public int evalRPN(String[] tokens){
        Deque<Integer> stack = new LinkedList<>();
        for(int i=0; i<tokens.length; i++){
            if("+".equals(tokens[i])){
                stack.push(stack.pop() + stack.pop());
            }else if("-".equals(tokens[i])){
                stack.push(-stack.pop() + stack.pop());
            }else if("*".equals(tokens[i])){
                stack.push(stack.pop() * stack.pop());
            }else if("/".equals(tokens[i])){
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2/temp1);
            }else{
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    /**
     * 9、滑动窗口最大值
     * 给你一个整数数组 `nums`，有一个大小为 `k` 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 `k` 个数字。滑动窗口每次只向右移动一位。
     * 返回 *滑动窗口中的最大值*
     *
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     */
    public int[] maxSlidingWindow(int[] nums, int k){
        if(nums == null || nums.length < 2){
            return nums;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        int[] result = new int[nums.length-k+1];
        for(int i=0; i<nums.length; i++){
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.addLast(i);
            //当前队列中队首
            if(queue.peek() <= i-k){
                queue.poll();
            }
            //当窗口长度为k。保存当前窗口的最大值
            if(i+1 >= k){
                result[i+1-k] = nums[queue.peek()];
            }
        }
        return result;
    }
    /**
     * 10、前 K 个高频元素
     * 给你一个整数数组 `nums` 和一个整数 `k` ，请你返回其中出现频率前 `k` 高的元素。你可以按 **任意顺序** 返回答案。
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     */
    /**
     * 基于堆
     * 时间复杂度：O(Nlogk)，其中N为数组的长度
     * 空间复杂度：O(N)。哈希表大小为O(N)，堆的大小为O(k)，共计为 O(N)
     */
    public int[] topKFrequent(int[] nums, int k){
        Map<Integer, Integer> occurrents = new HashMap<>();
        for(int num : nums){
            occurrents.put(num, occurrents.getOrDefault(num, 0) + 1);
        }
        //int[] 的第一个元素代表数组的值，第二个元素代表该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for(Map.Entry<Integer, Integer> entry : occurrents.entrySet()){
            int num = entry.getKey(), count = entry.getValue();
            if(queue.size() == k){
                if(queue.peek()[1] < count){
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            }else{
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for(int i=0; i<k; i++){
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

}

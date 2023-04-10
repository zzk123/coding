package com.zzk.algorithm.carl.array;

import java.util.*;

/**
 * 滑动窗口
 */
public class SlidingWindow {

    /**
     * 1、长度最小的子数组
     * 给定一个含有 `n` 个正整数的数组和一个正整数 `target`
     * 找出该数组中满足其和 `≥ target` 的长度最小的 **连续子数组** `[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度**。**如果不存在符合条件的子数组，返回 `0`
     *
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    /**
     * 暴力查找
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums){
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j<n; j++ ){
                sum += nums[j];
                if(sum >= s){
                    ans = Math.min(ans, j-i+1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
    /**
     * 滑动窗口
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public int minSubArrayLen2(int target, int[] nums){
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for(int right = 0; right < nums.length; right++){
            sum += nums[right];
            while(sum >= target){
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 2、替换后的最长重复字符
     *  给你一个字符串 `s` 和一个整数 `k` 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 `k` 次。
     *  在执行上述操作后，返回包含相同字母的最长子字符串的长度
     *
     *  输入：s = "AABABBA", k = 1
     * 输出：4
     * 解释：
     * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
     * 子串 "BBBB" 有最长重复字母, 答案为 4。
     */
    /**
     * 滑动窗口
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    public int characterRelacement(String s, int k){
        int len = s.length();
        if(len < 2){
            return len;
        }
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = 0;
        int res = 0;
        int maxCount = 0;
        int[] freq = new int[26];
        while(right < len){
            freq[charArray[right] - 'A'] ++;
            maxCount = Math.max(maxCount, freq[charArray[right] - 'A']);
            right++;

            if(right - left > maxCount + k){
                freq[charArray[left] - 'A'] --;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
    /**
     * 3、最大连续1的个数 III
     * 给定一个二进制数组 `nums` 和一个整数 `k`，如果可以翻转最多 `k` 个 `0` ，则返回 *数组中连续 `1` 的最大个数* 。
     * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：[1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     */
    /**
     * 滑动窗口
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param nums
     * @param k
     * @return
     */
    public int lngestOnes(int[] nums, int k){
        int left = 0;
        int right = 0;
        int res = 0;
        int maxCount = 0;
        int len = nums.length;
        while(right < len){
            if(nums[right] == 0){
                maxCount++;
            }
            while (maxCount > k){
                if(nums[left++] == 0){
                    maxCount--;
                }
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 4、尽可能使字符串相等
     * 给你两个长度相同的字符串，`s` 和 `t`。
     * 将 `s` 中的第 `i` 个字符变到 `t` 中的第 `i` 个字符需要 `|s[i] - t[i]|` 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     * 用于变更字符串的最大预算是 `maxCost`。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     * 如果你可以将 `s` 的子字符串转化为它在 `t` 中对应的子字符串，则返回可以转化的最大长度。
     * 如果 `s` 中没有子字符串可以转化成 `t` 中对应的子字符串，则返回 `0`。
     */
    /**
     * 滑动窗口
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubString(String s, String t, int maxCost){
        int n = s.length();
        int[] diff = new int[n];
        for(int i=0; i<n; i++){
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while(end < n){
            sum += diff[end];
            while(sum > maxCost){
                sum -= diff[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }

    /**
     * 5、删掉一个元素以后全为 1 的最长子数组
     * 给你一个二进制数组 `nums` ，你需要从中删掉一个元素。
     * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
     * 如果不存在这样的子数组，请返回 0
     *
     * 输入：nums = [1,1,0,1]
     * 输出：3
     * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
     */

    /**
     * 滑动窗口
     */
    public int longestSubarray(int[] nums){
        int left = 0;
        int right = 0;
        int len = nums.length;
        //记录0的最大个数
        int maxCount = 0;
        int res = 0;
        while(right < len){
            //记录0 的最大个数
            if(nums[right] == 0){
                maxCount++;
            }
            //超过1个0，就收缩窗口
            while(maxCount > 1){
                if(nums[left] == 0){
                    maxCount --;
                }
                left++;
            }
            res = Math.max(res, right-left);
            right++;
        }
        return res;
    }

    /**
     * 6、水果成篮
     * 输入：fruits = [1,2,3,2,2]
     * 输出：4
     * 解释：可以采摘 [2,3,2,2] 这四棵树。
     * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
     */
    /**
     * 滑动窗口
     */
    public int totalFruit(int[] tree){
        if(tree == null || tree.length == 0){
            return 0;
        }
        int n = tree.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0, left = 0;
        for(int i=0; i<n; i++){
            //右边界
            map.put(tree[i], map.getOrDefault(tree[i], 0) + 1);
            while(map.size() > 2){
                map.put(tree[left], map.get(tree[left])-1);
                if(map.get(tree[left]) == 0){
                    map.remove(tree[left]);
                }
                //左边界
                left++;
            }
            maxLen = Math.max(maxLen, i-left+1);
        }
        return maxLen;
    }

    /**
     * 7、滑动窗口最大值
     * 给你一个整数数组 `nums`，有一个大小为 `k` 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 `k` 个数字。滑动窗口每次只向右移动一位。
     * 返回 *滑动窗口中的最大值*
     */
    public int[] maxSlidingWindow(int[] nums, int k){
        if(nums == null || nums.length < 2){
            return nums;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int[] result = new int[nums.length-k+1];
        for(int i=0; i<nums.length; i++){
            //保证从大到小，如果前面的值小于等于当前的值，直接弹出
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            //添加当前值
            queue.addLast(i);
            //判断当前队首是否在区间内
            if(queue.peek() <= i-k ){
                queue.poll();
            }
            //当窗口长度为k时，保存当前窗口的最大值
            if(i+1 >= k){
                result[i+1-k] = nums[queue.peek()];
            }
        }
        return result;
    }

    /**
     * 8、无重复字符串的最长子串
     * 给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长子串** 的长度
     */
    public int lengthOfLongestSubString(String s){
        if(s.length() == 0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i-left+1);
        }
        return max;
    }

    /**
     * 9、找到字符串中所有字母异位词
     * 滑动窗口
     * 时间：O(N)
     * 空间：O(1)
     */
    public List<Integer> findAnagrams(String s, String p){
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if(n < m){
            return res;
        }


        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        //记录p字符的频次
        for(int i=0; i<m; i++){
            pCnt[p.charAt(i) - 'a']++;
        }
        //开始循环s字符的频次
        int left = 0;
        for(int right = 0; right < n; right++){
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight]++;
            while(sCnt[curRight] > pCnt[curRight]){
                int curLeft = s.charAt(left) - 'a';
                sCnt[curLeft]--;
                left++;
            }
            if(right - left + 1 == m){
                res.add(left);
            }
        }
        return res;
    }
    /**
     * 10、最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     */
    public String minWindow(String s, String t){
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int n = chars.length;
        int m = chart.length;

        //记录字符串t的值
        int[] hash = new int[128];
        for(char ch : chart){
            hash[ch]--;
        }

        String res = "";
        //循环字符串 s
        for(int i = 0, j = 0, cnt = 0; i < n; i++){
            hash[chars[i]]++;
            //正好匹配，cnt++
            if(hash[chars[i]] <= 0){
                cnt++;
            }
            //字符串t的值都在窗口中，调整为最小窗口即调整左边界
            while(cnt == m && hash[chars[j]] > 0){
                hash[chars[j]]--;
                j++;
            }
            //判断最小字符串
            if(cnt == m){
                if(res.equals("") || res.length() > i - j + 1){
                    res = s.substring(j, i+1);
                }
            }
        }
        return res;
    }

    /**
     * 11、字符串的排列
     * 给你两个字符串 `s1` 和 `s2` ，写一个函数来判断 `s2` 是否包含 `s1` 的排列。如果是，返回 `true` ；否则，返回 `false`
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     */
    public boolean checkInclusion(String s1, String s2){
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;

        int[] hash = new int[26];
        for(char ch : chars1){
            hash[ch - 'a']--;
        }
        int left = 0;
        for(int right=0; right<n; right++){
            //相加
            hash[chars2[right]-'a']++;
            //控制边界
            while(hash[chars2[right]-'a'] > 0){
                hash[chars2[left]-'a']--;
                left++;
            }
            //保证区间，也就是保证字符串连续
            if(right-left+1 == m){
                return true;
            }
        }
        return false;
    }
}

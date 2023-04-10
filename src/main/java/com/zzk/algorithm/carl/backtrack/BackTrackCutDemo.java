package com.zzk.algorithm.carl.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 回溯算法
 * 切割demo
 */
public class BackTrackCutDemo {

    /**
     * 1、分割回文串
     * 给你一个字符串 `s`，请你将 `s` 分割成一些子串，使每个子串都是 **回文串** 。返回 `s` 所有可能的分割方案。
     * **回文串** 是正着读和反着读都一样的字符串
     *
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     */
    //解法1
    public List<List<String>> partition(String s){
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if(len == 0){
            return res;
        }

        Deque<String> stack = new ArrayDeque<>();
        char[] charArray = s.toCharArray();
        dfs(charArray, 0, len, stack, res);
        return res;
    }
    private void dfs(char[] charArray, int index, int len, Deque<String> path, List<List<String>> res) {
        if(index == len){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=index; i<len; i++){
            //校验是否为回文字符串
            if(!checkPalindrome(charArray, index, i)){
                continue;
            }
            //回溯接下来的字符串
            path.addLast(new String(charArray, index, i+1-index));
            dfs(charArray, i+1, len, path, res);
            path.removeLast();
        }
    }

    /**
     * 校验是否为回文字符串
     * @param charArray
     * @param left
     * @param right
     * @return
     */
    private boolean checkPalindrome(char[] charArray, int left, int right){
        while(left < right){
            if(charArray[left] != charArray[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //解法2
    public List<List<String>> partition2(String s){
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if(len == 0){
            return res;
        }

        char[] charArray = s.toCharArray();
        //预处理
        //状态 dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        for(int right = 0; right < len; right++){
            for(int left = 0; left <= right; left++){
                if(charArray[left] == charArray[right] && (right - left <= 2 || dp[left+1][right-1])){
                    dp[left][right] = true;
                }
            }
        }

        Deque<String> stack = new ArrayDeque<>();
        dfs2(s, 0, len, dp, stack, res);
        return res;
    }

    private void dfs2(String s, int index, int len, boolean[][] dp, Deque<String> path, List<List<String>> res){
        if(index == len){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=index; i<len; i++){
            if(dp[index][i]){
                path.addLast(s.substring(index, i+1));
                dfs2(s, i+1, len, dp, path, res);
                path.removeLast();
            }
        }
    }

    /**
     * 2、复原 IP 地址
     * **有效 IP 地址** 正好由四个整数（每个整数位于 `0` 到 `255` 之间组成，且不能含有前导 `0`），整数之间用 `'.'` 分隔。
     * - 例如：`"0.1.2.201"` 和` "192.168.1.1"` 是 **有效** IP 地址，但是 `"0.011.255.245"`、`"192.168.1.312"`
     * 和 `"192.168@1.1"` 是 **无效** IP 地址。
     * 给定一个只包含数字的字符串 `s` ，用以表示一个 IP 地址，返回所有可能的**有效 IP 地址**，这些地址可以通过在 `s` 中插入 `'.'` 来形成。
     * 你 **不能** 重新排序或删除 `s` 中的任何数字。你可以按 **任何** 顺序返回答案。
     *
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     */
    public List<String> restoreIpAddresses(String s){
        int len = s.length();
        List<String> res = new ArrayList<>();
        //长度不够，不搜索
        if(len < 4 || len > 12){
            return res;
        }

        Deque<String> path = new ArrayDeque<>(4);
        int splitTimes = 0;
        dfs3(s, len, splitTimes, 0, path, res);
        return res;
    }

    private void dfs3(String s, int len, int split, int begin, Deque<String> path, List<String> res){
        if(begin == len){
            if(split == 4){
                res.add(String.join(".", path));
            }
            return;
        }

        // 看到剩下的不够了，就退出（剪枝），len - begin 表示剩余的还未分割的字符串的位数
        if(len - begin < (4-split) || len-begin > 3*(4-split)){
            return;
        }

        for(int i=0; i<3; i++){
            if(begin + i >= len){
                break;
            }

            int ipSegment = judgeIfIpSegment(s, begin, begin+i);
            if(ipSegment != -1){
                // 在判断是 ip 段的情况下，才去做截取
                path.addLast(ipSegment + "");
                dfs3(s, len, split+1, begin+i+1, path, res);
                path.removeLast();
            }
        }

    }

    /**
     * 判断 s 的子区间 [left, right] 是否能够成为一个 ip 段
     * 判断的同时顺便把类型转了
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int judgeIfIpSegment(String s, int left, int right){
        int len = right - left + 1;
        //大于 1 位的时候，不能以 0 开头
        if(len > 1 && s.charAt(left) == '0'){
            return -1;
        }

        // 转成 int 类型
        int res = 0;
        for(int i=left; i<=right; i++){
            res = res * 10 + s.charAt(i) - '0';
        }

        if(res > 255){
            return -1;
        }
        return res;
    }
}

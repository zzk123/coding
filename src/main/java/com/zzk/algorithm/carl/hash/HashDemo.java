package com.zzk.algorithm.carl.hash;

import java.util.*;

/**
 * 哈希表相关
 */
public class HashDemo {

    /**
     * 1、有效的字母异位词
     * 给定两个字符串 `*s*` 和 `*t*` ，编写一个函数来判断 `*t*` 是否是 `*s*` 的字母异位词。
     * **注意：**若 `*s*` 和 `*t*` 中每个字符出现的次数都相同，则称 `*s*` 和 `*t*` 互为字母异位词
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */
    public boolean isAnagram(String s, String t){
        if(s.length() != t.length()){
            return false;
        }
        int[] alpha = new int[26];
        for(int i = 0; i < s.length(); i++){
            alpha[s.charAt(i) - 'a']++;
            alpha[t.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(alpha[i] != 0){
                return false;
            }
        }
        return true;
    }
    /**
     * 2、两个数组的交集
     * 给定两个数组 `nums1` 和 `nums2` ，返回 *它们的交集* 。输出结果中的每个元素一定是 **唯一** 的。我们可以 **不考虑输出结果的顺序**
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     *
     * 时间复杂度 O(m+n)
     * 空间复杂度 O(m+n)
     */
    public int[] intersection(int[] nums1, int[] nums2){
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for(int nums : nums1){
            set1.add(nums);
        }

        for(int nums : nums2){
            set2.add(nums);
        }

        return getIntersection(set1, set2);
    }

    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2){
        if(set1.size() > set2.size()){
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<>();
        for(int nums : set1){
            if(set2.contains(nums)){
                intersectionSet.add(nums);
            }
        }

        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for(int num : intersectionSet){
            intersection[index++] = num;
        }
        return intersection;
    }

    /**
     * 3、快乐数
     * 编写一个算法来判断一个数 `n` 是不是快乐数。
     */
    //哈希表
    public boolean isHappy(int n){
        Set<Integer> seen = new HashSet<>();
        while(n != 1 && !seen.contains(n)){
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    public int getNext(int n){
        int totalSum = 0;
        while(n > 0){
            int d = n % 10;
            n = n / 10;
            totalSum += d*d;
        }
        return totalSum;
    }

    //双指针
    public boolean isHappy2(int n){
        int slow = n, fast = squareSum(n);
        while(slow != fast){
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        return slow == 1;
    }

    public int squareSum(int n){
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * 4、四数相加 II
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        Map<Integer, Integer> map = new HashMap<>();
        int temp = 0;
        int res = 0;
        //整理前两个数组
        for(int i : nums1){
            for(int j : nums2){
                temp = i + j;
                if(map.containsKey(temp)){
                    map.put(temp, map.get(temp) + 1);
                }else{
                    map.put(temp, 1);
                }
            }
        }
        //对比后两个数组
        for(int i : nums3){
            for(int j : nums4){
                temp = i + j;
                if(map.containsKey(0-temp)){
                    res += map.get(0-temp);
                }
            }
        }
        return res;
    }

    /**
     * 5、赎金信
     * 给你两个字符串：`ransomNote` 和 `magazine` ，判断 `ransomNote` 能不能由 `magazine` 里面的字符构成。
     * 如果可以，返回 `true` ；否则返回 `false` 。
     * `magazine` 中的每个字符只能在 `ransomNote` 中使用一次
     */
    public boolean canConstruct(String ransomNot, String magazine){
        int[] arr = new int[26];
        int temp;
        for(int i=0; i<magazine.length(); i++){
            arr[magazine.charAt(i) - 'a']++;
        }

        for(int i=0; i < ransomNot.length(); i++){
            temp = ransomNot.charAt(i) - 'a';
            if(arr[temp] > 0){
                arr[temp] --;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 6、三数之和
     * 给你一个包含 `n` 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素 *a，b，c ，*使得 *a + b + c =* 0 ？请你找出所有和为 `0` 且不重复的三元组。
     * **注意：**答案中不可以包含重复的三元组。
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     */
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return ans;
        }
        int len = nums.length;
        //排序
        Arrays.sort(nums);
        for(int i = 0; i < len; i++){
            //因为已经排序好了，如果当前数字大于0，三数之和一定大于0
            if(nums[i] > 0){
                break;
            }
            //去重
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int L = i + 1;
            int R = len - 1;
            //循环相加
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while(L < R && nums[L] == nums[L+1]){
                        L++;
                    }
                    while(L < R && nums[R] == nums[R-1]){
                        R--;
                    }
                    L++;
                    R++;
                }else if(sum < 0){
                    L++;
                }else{
                    R--;
                }
            }
        }
        return ans;
    }

}

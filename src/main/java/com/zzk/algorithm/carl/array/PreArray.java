package com.zzk.algorithm.carl.array;

/**
 * 前缀和
 */
public class PreArray {

    /**
     * 1、如何求一个数组的连续子数组总个数？
     * 比如[1, 3, 4]，其连续的子数组有：[1], [3], [4], [1, 3], [3, 4], [1, 3, 4]，所以总个数为 6
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public int countSubArray(int[] nums){
        int ans = 0;
        int pre = 0;
        for(int i=0; i<nums.length; i++){
            pre += 1;
            ans += pre;
        }
        return ans;
    }


    /**
     * 2、如何求一个数组值相差为1连续子数组的总个数？
     *  时间：O(N)
     *  空间：O(1)
     * @param nums
     * @return
     */
    public int countSubArray2(int[] nums){
        int ans = 0;
        int pre = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] - nums[i-1] == 1){
                pre += 1;
            }else{
                pre = 0;
            }
            ans += pre;
        }
        return ans;
    }

    /**
     * 3、如何求不大于k的子数组的总个数？
     * 比如[1, 3, 4]，其连续的子数组有：[1], [3], [4], [1, 3], [3, 4], [1, 3, 4]，k=3，不大于3的数组有 [1], [3], [1, 3]，总数为3
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     * @param nums
     * @param k
     * @return
     */
    public int countSubArray3(int[] nums, int k){
        int ans = 0;
        int pre = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= k){
                pre += 1;
            }else{
                pre = 0;
            }
            ans += pre;
        }
        return ans;
    }

    /**
     * 4、如何求出子数组最大值刚好是 k 的子数组的个数？
     * 比如[1, 3, 4]，其连续的子数组有：[1], [3], [4], [1, 3], [3, 4], [1, 3, 4]，k=3，子数组最大值刚好是 3 的子数组有 [3], [1, 3]，总数为 2
     * @param nums
     * @param k
     * @return
     */

    public int getCount(int[] nums, int k){
        return countSubArray4(nums, k) - countSubArray4(nums, k-1);
    }

    public int countSubArray4(int[] nums, int k){
        int ans = 0;
        int pre = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] <= k){
                pre += 1;
            }else{
                pre = 0;
            }
            ans += pre;
        }
        return ans;
    }


    /**
     * 5、求出子数组最大值刚好是介于 k1 和 k2 的子数组的个数？
     */
    public int getCount(int[] nums, int k1, int k2){
        return countSubArray5(nums, k2) - countSubArray5(nums, k1-1);
    }


    public int countSubArray5(int[] nums, int k){
        int ans = 0;
        int pre = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= k){
                pre += 1;
            }else{
                pre = 0;
            }
            ans += pre;
        }
        return ans;
    }



    /**
     * 6、区间子数组个数
     * 给你一个整数数组 `nums` 和两个整数：`left` 及 `right` 。找出 `nums` 中连续、非空且其中最大元素在范围 `[left, right]` 内的子数组，并返回满足条件的子数组的个数
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right){
        return countSubArray6(nums, right) - countSubArray6(nums, left-1);
    }

    public int countSubArray6(int[] nums, int k){
        int pre = 0;
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= k){
                pre += 1;
            }else{
                pre = 0;
            }
            res += pre;
        }
        return res;
    }

    /**
     * 7、K 个不同整数的子数组
     * 给定一个正整数数组 `nums`和一个整数 k ，返回 num 中 「**好子数组」** 的数目。
     * 如果 `nums` 的某个子数组中不同整数的个数恰好为 `k`，则称 `nums` 的这个连续、不一定不同的子数组为 **「好子数组」**。
     *
     * 输入：nums = [1,2,1,2,3], k = 2
     * 输出：7
     * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
     */
    public int subarraysWithKDistinct(int[] nums, int k){
        return getCount2(nums, k) - getCount2(nums, k-1);
    }

    /**
     * 滑动窗口
     * 查找数组由k个整数组成的子数组的总数量
     * @param nums
     * @param k
     * @return
     */
    public int getCount2(int[] nums, int k){
        int len = nums.length;
        int[] freq = new int[len+1];
        int res = 0;
        int count = 0;
        int left = 0;
        for(int i = 0; i < nums.length; i++){
            //统计相同整数的个数
            if(freq[nums[i]] == 0){
                count++;
            }
            freq[nums[i]]++;
            //查找左边界
            while(count > k){
                freq[nums[left]]--;
                if(freq[nums[left]] == 0){
                    count--;
                }
                left++;
            }
            //汇总找到[left, i) 区间中每个以i结尾的子数组的数量
            res += i-left+1;
        }
        return res;
    }

}

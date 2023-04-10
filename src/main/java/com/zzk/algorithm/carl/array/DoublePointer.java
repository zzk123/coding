package com.zzk.algorithm.carl.array;

import java.util.Arrays;

/**
 * 双指针
 */
public class DoublePointer {

    /**
     * 1、比较含退格的字符串
     * 给定 `s` 和 `t` 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 `true` 。`#` 代表退格字符
     *
     * 输入：s = "ab#c", t = "ad#c"
     * 输出：true
     * 解释：s 和 t 都会变成 "ac"。
     */
    /**
     * 时间复杂度 O(M+N)
     * 空间复杂度 O(M+N)
     * @param s
     * @param T
     * @return
     */
    public boolean backspaceCompare(String s, String T){
        return build(s).equals(build(T));
    }
    public String build(String str){
        StringBuffer ret = new StringBuffer();
        int length = str.length();
        for(int i = 0; i < length; i++){
            char ch = str.charAt(i);
            if(ch != '#'){
                ret.append(ch);
            }else{
                if(ret.length() > 0){
                    ret.deleteCharAt(ret.length()-1);
                }
            }
        }
        return ret.toString();
    }

    /**
     * 双指针
     * 时间复杂度 O(M+N)
     * 空间复杂度 O(1)
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }

    /**
     * 2、有序数组的平方
     * 给你一个按 **非递减顺序** 排序的整数数组 `nums`，返回 **每个数字的平方** 组成的新数组，要求也按 **非递减顺序** 排序
     *
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     */

    /**
     * 直接排序
     * 时间复杂度 O(nlogn)
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums){
        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            ans[i] = nums[i] * nums[i];
        }
        Arrays.sort(ans);
        return ans;
    }

    /**
     * 双指针
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public int[] sortedSquares2(int[] nums){
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while(left <= right){
            if(nums[left] * nums[left] > nums[right] * nums[right]){
                result[index--] = nums[left] * nums[left];
                ++left;
            }else{
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }

    /**
     * 3、寻找两个正序数组的中位数
     * 给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **中位数**
     *
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     */
    public double findMedianSortedArrays(int[] A, int[] B){
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for(int i = 0; i <= len/2; i++){
            left = right;
            if(aStart < m && (bStart >= n || A[aStart] < B[bStart])){
                right = A[aStart++];
            }else{
                right = B[bStart++];
            }
        }

        if((len & 1) == 0){
            return (left + right) / 2.0;
        }else{
            return right;
        }
    }


}

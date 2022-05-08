package com.zzk.algorithm.leetcode.test;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2022-03-31 22:52
 */
public class Solution02 {

    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    public static void main(String[] args) {
        /*int[] num1 = {1,2};
        int[] num2 = {2,4};*/
       /* int[] num1 = {1,2};
        int[] num2 = {3,4};*/
        /*int[] num1 = {2};
        int[] num2 = {};*/
        int[] num1 = {100001};
        int[] num2 = {10000};
        findMedianSortedArrays(num1, num2);
    }
}

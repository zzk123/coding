package com.zzk.algorithm.carl.greedy;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 贪心算法 - 简单题
 */
public class EasyDemo {
    /**
     * 1、分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 `i`，都有一个胃口值 `g[i]`，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 `j`，都有一个尺寸 `s[j]` 。如果 `s[j] >= g[i]`，我们可以将这个饼干 `j` 分配给孩子 `i` ，
     * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值
     *
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1
     * 解释:
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     */

    /**
     * 思路
     * - 局部最优是用大饼喂给胃口大的，充分利用饼干尺寸喂饱一个，全局最优就是喂饱尽可能多的小孩
     * - 尝试用贪心策略，先将饼干数组和小孩数组排序
     * - 然后从前到后遍历小孩数组，用小饼干优先满足胃口小的，并统计小孩数量
     */
    public int findContentChildren(int[] g, int[] s){
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int count = 0;
        for(int i=0; i<s.length && start < g.length; i++){
            if(s[i] > g[start]){
                start++;
                count++;
            }
        }
        return count;
    }

    /**
     * 2、K 次取反后最大化的数组和
     * 给你一个整数数组 `nums` 和一个整数 `k` ，按以下方法修改该数组：
     * - 选择某个下标 `i` 并将 `nums[i]` 替换为 `-nums[i]` 。
     * 重复这个过程恰好 `k` 次。可以多次选择同一个下标 `i` 。
     * 以这种方式修改数组后，返回数组 **可能的最大和** 。
     *
     * 输入：nums = [4,2,3], k = 1
     * 输出：5
     * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
     */
    /**
     * 贪心思路：
     * 如何让数组和最大？
     * - 局部最优：让绝对值最大的负数变成整数，当前的数值达到最大，整理最优：整个数组和达到最大
     * 如果将负数都转变为正数了，K依然大于0，此时的问题是一个有序正整数序列，如何转变K次正负，让 数组和 达到最大？
     * - 局部最优：只找数值最小的正整数进行反转，当前数值可以达到最大，全局最优：整个数组和达到最大
     */
    public int largetSumAfterKBegations(int[] nums, int K){
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        int len = nums.length;
        for(int i=0; i<len; i++){
            if(nums[i] < 0 && K > 0){
                nums[i] =- nums[i];
                K--;
            }
        }
        //为奇数，只需要扭转最后一个元素就行。如果为偶数，不用改变
        if(K % 2 == 1){
            nums[len-1] = -nums[len-1];
        }
        return Arrays.stream(nums).sum();
    }


    /**
     * 3、柠檬水找零
     * 在柠檬水摊上，每一杯柠檬水的售价为 `5` 美元。顾客排队购买你的产品，（按账单 `bills` 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 `5` 美元、`10` 美元或 `20` 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 `5` 美元。
     * 注意，一开始你手头没有任何零钱。
     * 给你一个整数数组 `bills` ，其中 `bills[i]` 是第 `i` 位顾客付的账。如果你能给每位顾客正确找零，返回 `true` ，否则返回 `false` 。
     *
     * 输入：bills = [5,5,5,10,20]
     * 输出：true
     * 解释：
     * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
     * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
     * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
     * 由于所有客户都得到了正确的找零，所以我们输出 true。
     */
    /**
     * 思路
     * 三种情况
     * - 账单是5，直接收下
     * - 账单是10，消耗一个5，增加一个10
     * - 账单是20，优先消耗一个10和一个5，如果不够，继续再消耗三个5
     *   - 贪心策略：局部最优 = 遇到账单20，优先消耗美元10完成找零，全局最优 = 完成全部账单的找零
     */
    public boolean lemonadeChange(int[] bills){
        int five = 0;
        int ten = 0;
        for(int i=0; i<bills.length; i++){
            if(bills[i] == 5){
                five++;
            }else if(bills[i] == 10){
                five--;
                ten++;
            }else if(bills[i] == 20){
                if(ten > 0){
                    ten--;
                    five--;
                }else{
                    five -=3;
                }
            }
            if(five < 0 || ten < 0){
                return false;
            }
        }
        return true;
    }
}

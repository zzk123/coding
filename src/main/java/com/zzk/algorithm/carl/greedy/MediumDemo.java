package com.zzk.algorithm.carl.greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 贪心算法 - 中等题
 */
public class MediumDemo {

    //两个维度权衡问题
    /**
     * 1、分发糖果
     * `n` 个孩子站成一排。给你一个整数数组 `ratings` 表示每个孩子的评分。
     * 你需要按照以下要求，给这些孩子分发糖果：
     * - 每个孩子至少分配到 `1` 个糖果。
     * - 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 **最少糖果数目** 。
     *
     * 输入：ratings = [1,0,2]
     * 输出：5
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
     */
    /**
     * 贪心思路
     * 第一：从左到右遍历，比较右边比左边大的情况
     *  - 局部最优：只要右边比左边大，右边孩子多一个糖果
     *  - 全局最优：相邻孩子中，评分高的右孩子获得比左孩子多
     * 第二：从右到左遍历，比较左边比右边大的情况
     *  - 局部最优：保证第 i 个小孩的糖果数量大于左边的也大于右边的
     *  - 全局最优：相邻的孩子中，评分高的孩子获得更多的糖果
     */
    public int candy(int[] ratings){
        int[] candyVec = new int[ratings.length];
        candyVec[0] = 1;
        for(int i=0; i<ratings.length; i++){
            if(ratings[i] > ratings[i-1]){
                candyVec[i] = candyVec[i-1]+1;
            }else{
                candyVec[i] = 1;
            }
        }

        for(int i=ratings.length-2; i>=0; i--){
            if(ratings[i] > ratings[i+1]){
                candyVec[i] = Math.max(candyVec[i], candyVec[i+1]+1);
            }
        }

        int ans = 0;
        for(int s : candyVec){
            ans += s;
        }
        return ans;
    }

    /**
     * 2、根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列，数组 `people` 表示队列中一些人的属性（不一定按顺序）。每个 `people[i] = [hi, ki]`
     * 表示第 `i` 个人的身高为 `hi` ，前面 **正好** 有 `ki` 个身高大于或等于 `hi` 的人。
     * 请你重新构造并返回输入数组 `people` 所表示的队列。返回的队列应该格式化为数组 `queue` ，其中 `queue[j] = [hj, kj]`
     * 是队列中第 `j` 个人的属性（`queue[0]` 是排在队列前面的人）。
     *
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列
     */
    public int[][] reconstructQueue(int[][] people){
        Arrays.sort(people, (a, b) -> {
            if(a[0] == b[0]){
                return a[1]-b[1];
            }
            return b[0]-a[0];
        });
        LinkedList<int[]> que = new LinkedList<>();
        for(int[] p : people){
            que.add(p[1], p);
        }
        return que.toArray(new int[people.length][]);
    }

    //股票问题
    /**
     * 3、买卖股票的最佳时机 II
     * 给你一个整数数组 `prices` ，其中 `prices[i]` 表示某支股票第 `i` 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 **最多** 只能持有 **一股** 股票。
     * 你也可以先购买，然后在 **同一天** 出售。
     * 返回 *你能获得的 **最大** 利润* 。
     *
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7
     */

    /**
     * 贪心思路：
     * 每天的利润序列：price[i]-price[i-1]
     *
     * 局部最优：收集每天的正利润
     * 全局最优：求最大利润
     */
    public int maxProfit(int[] prices){
        int result = 0;
        for(int i=1; i<prices.length; i++){
            result += Math.max(prices[i]-prices[i-1], 0);
        }
        return result;
    }

    /**
     * 4、买卖股票的最佳时机含手续费
     * 给定一个整数数组 `prices`，其中 `prices[i]`表示第 `i` 天的股票价格 ；整数 `fee` 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * **注意：**这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     */
    /**
     * 贪心思路
     * 我们用  buy 表示在最大化收益的前提下，如果我们手上拥有一支股票，那么它的最低买入价格是多少。
     * 在初始时，buy 的值为prices[0] 加上手续费 fee。那么当我们遍历到第 (i>0) 天时：
     *   - 如果当前的股票价格  prices[i] 加上手续费 fee 小于 buy，那么与其使用 buy 的价格购买股票，
     *     我们不如以  prices[i]+fee 的价格购买股票，因此我们将  buy 更新为  prices[i]+fee
     *   - 如果当前的股票价格 prices[i] 大于 buy，那么我们直接卖出股票并且获得 prices[i]−buy 的收益。
     *     但实际上，我们此时卖出股票可能并不是全局最优的（例如下一天股票价格继续上升），
     *     因此我们可以提供一个反悔操作，看成当前手上拥有一支买入价格为 prices[i] 的股票，
     *     将 buy 更新为 prices[i]。这样一来，如果下一天股票价格继续上升，我们会获得 prices[i+1]−prices[i] 的收益，
     *     加上这一天 prices[i]−buy 的收益，恰好就等于在这一天不进行任何操作，而在下一天卖出股票的收益
     */
    public int maxProfit(int[] prices, int fee){
        int buy = prices[0] + fee;
        int sum = 0;
        for(int p : prices){
            if(p + fee < buy){
                buy = p + fee;
            }else if(p > buy){
                sum += p - buy;
                buy = p;
            }
        }
        return sum;
    }

    //序列问题
    /**
     * 5、摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 **摆动序列 。**第一个差（如果存在的话）可能是正数或负数。
     * 仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     * - 例如， `[1, 7, 4, 9, 2, 5]` 是一个 **摆动序列** ，因为差值 `(6, -3, 5, -7, 3)` 是正负交替出现的。
     * - 相反，`[1, 4, 7, 2, 5]` 和 `[1, 7, 4, 5, 5]` 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * **子序列** 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * 给你一个整数数组 `nums` ，返回 `nums` 中作为 **摆动序列** 的 **最长子序列的长度**
     *
     * 输入：nums = [1,7,4,9,2,5]
     * 输出：6
     * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
     */
    /**
     * 贪心策略
     * 局部最优：删除单调坡度上的节点，那么这个坡度就有两个
     * 全局最优：整个序列有最多的局部峰值，从而达到最长摆动序列
     */
    public int wiggleMaxLength(int[] nums){
        if(nums.length <= 1){
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for(int i=1; i<nums.length; i++){
            curDiff = nums[i] - nums[i-1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if((curDiff > 0 && preDiff <= 0)
                    || (curDiff < 0) && preDiff >= 0){
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }


    /**
     * 6、单调递增的数字
     * 当且仅当每个相邻位数上的数字 `x` 和 `y` 满足 `x <= y` 时，我们称这个整数是**单调递增**的。
     * 给定一个整数 `n` ，返回 *小于或等于 `n` 的最大数字，且数字呈 **单调递增***
     *
     * 输入: n = 10
     * 输出: 9
     */
    /**
     * 贪心思路：
     * 局部最优：遇见 strNum[i-1] > strNum[i] 的情况，让 strNum[i-1]--，然后strNum[i]给到9
     * 全局最优：得到小于等于N的最大单调递增的整数
     */
    public int monotoneIncreasingDigits(int n){
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for(int i=s.length()-2; i>=0; i--){
            if(chars[i] > chars[i+1]){
                chars[i]--;
                start = i+1;
            }
        }
        for(int i=start; i<s.length(); i++){
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}

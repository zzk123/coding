package com.zzk.algorithm.carl.greedy;

import com.zzk.algorithm.carl.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HardDemo {

    /**
     * 1、跳跃游戏
     * 给定一个非负整数数组 `nums` ，你最初位于数组的 **第一个下标** 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标
     *
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标
     */
    /**
     * 思路
     * 该问题转为跳跃覆盖范围可不可以覆盖到终点
     * 每次移动取最大跳跃步数，每移动一个单位，就更新最大覆盖范围
     * 局部最优：每次取最大跳跃步数。
     * 整体最优：最后得到整体最大覆盖范围，看能否到终点
     */
    public boolean canJump(int[] nums){
        int n = nums.length;
        int farthest = 0;
        for(int i=0; i<n-1; i++){
            farthest = Math.max(farthest, i+nums[i]);
            if(farthest <= i){
                return false;
            }
        }
        return farthest >= n-1;
    }

    /**
     * 2、跳跃游戏 II
     * 给你一个非负整数数组 `nums` ，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 假设你总是可以到达数组的最后一个位置。
     */
    /**
     * 贪心算法
     * 思路
     * - 局部最优：当前可移动距离尽可能多走，如果还没到终点，步数再加一。
     * - 整体最优：一步尽可能多走，从而达到最小步数。
     */
    public int jump(int[] nums){
        int ans = 0;
        int begin = 0;
        int end = 0;
        //[begin, end]

        while(end < nums.length - 1){
            int temp = 0;
            for(int i=begin; i<=end; i++){
                //能跳的最远距离
                temp = Math.max(nums[i]+1, temp);
            }
            begin = end+1;  //下次开始跳的范围开始格子
            end = temp;     //下次开始跳的范围结束各自
            ans++;          //跳跃次数
        }
        return ans;
    }

    public int jump2(int[] nums){
        int end = 0;
        int n = nums.length;
        int farthest = 0;
        int jump = 0;
        for(int i=0; i<n-1; i++){
            farthest = Math.max(farthest, i+nums[i]);
            if(end == i){
                jump++;
                end = farthest;
            }
        }
        return jump;
    }


    /**
     * 3、用最少数量的箭引爆气球
     * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 `points` ，其中`points[i] = [xstart, xend]`
     * 表示水平直径在 `xstart` 和 `xend`之间的气球。你不知道气球的确切 y 坐标。
     * 一支弓箭可以沿着 x 轴从不同点 **完全垂直** 地射出。在坐标 `x` 处射出一支箭，若有一个气球的直径的开始和结束坐标为 `x``start`，`x``end`，
     * 且满足  `xstart ≤ x ≤ x``end`，则该气球会被 **引爆** 。可以射出的弓箭的数量 **没有限制** 。 弓箭一旦被射出之后，可以无限地前进。
     * 给你一个数组 `points` ，*返回引爆所有气球所必须射出的 **最小** 弓箭数* 。
     *
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：气球可以用2支箭来爆破:
     * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
     * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
     */
    /**
     * 贪心思路
     * 局部最优：当气球出现重叠。一起射，所用弓箭最少
     * 全局最优：把所有气球射爆，所用弓箭最少
     */
    public int findMinArrowShots(int[][] points){
        if(points.length == 0){
            return 0;
        }
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        int count = 1;
        for(int i=1; i<points.length; i++){
            if(points[i][0] > points[i-1][1]){
                count++;
            }else{
                points[i][1] = Math.min(points[i][1], points[i-1][1]);
            }
        }
        return count;
    }

    /**
     * 4、无重叠区间
     * 给定一个区间的集合 `intervals` ，其中 `intervals[i] = [starti, endi]` 。
     * 返回 *需要移除区间的最小数量，使剩余区间互不重叠*
     * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     */
    /**
     * 贪心算法
     */
    public int eraseOverLlapIntervals(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        int remove = 0;
        int pre = intervals[0][1];
        for(int i=1; i<intervals.length; i++){
            //需要一只箭
            if(pre > intervals[i][0]){
                remove++;
                //取区间比较小的。让右边的空间大点，选择多点
                pre = Math.min(pre, intervals[i][1]);
            }else{
                pre = intervals[i][1];
            }
        }
        return remove;
    }

    /**
     * 5、划分字母区间
     * 字符串 `S` 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 返回一个表示每个字符串片段的长度的列表
     *
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     */
    public List<Integer> partitionLabels(String s){
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++){
            edge[chars[i]-'a'] = i;
        }
        int idx = 0;
        int last = -1;
        for(int i=0; i<chars.length; i++){
            idx = Math.max(idx, edge[chars[i] - 'a']);
            if(i == idx){
                list.add(i-last);
                last = i;
            }
        }
        return list;
    }

    /**
     * 6、合并区间
     * 以数组 `intervals` 表示若干个区间的集合，其中单个区间为 `intervals[i] = [starti, endi]` 。
     * 请你合并所有重叠的区间，并返回 *一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间*
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    /**
     * 思路
     * 按照左边界排序，排序后
     * 局部最优：每次合并取得最大的右边界
     * 整体最优：合并所有重叠的区间
     */
    public int[][] merge(int[][] intervals){
        List<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int start = intervals[0][0];
        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0] > intervals[i-1][1]){
                res.add(new int[]{start, intervals[i-1][0]});
                start = intervals[i][0];
            }else{
                intervals[i][1] = Math.max(intervals[i][1], intervals[i-1][1]);
            }
        }
        res.add(new int[]{start, intervals[intervals.length-1][1]});
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 7、最大子数组和
     * 给你一个整数数组 `nums` ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），
     * 返回其最大和
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    /**
     * 贪心思路
     * 局部最优：当连续和为负数时，从下一个元素重新计算连续和，因为负数加上下一个元素连续和只会越来越小
     * 全局最优：选取最大连续和
     */
    public int maxSubArray(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for(int i=0; i<nums.length; i++){
            count += nums[i];
            sum = Math.max(sum, count);
            //小于等于0，重置
            if(count <= 0){
                count = 0;
            }
        }
        return sum;
    }

    /**
     * 8、加油站
     * 在一条环路上有 `n` 个加油站，其中第 `i` 个加油站有汽油 `gas[i]` 升。
     * 你有一辆油箱容量无限的的汽车，从第 `i` 个加油站开往第 `i+1` 个加油站需要消耗汽油 `cost[i]` 升。
     * 你从其中的一个加油站出发，开始时油箱为空。
     * 给定两个整数数组 `gas` 和 `cost` ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 `-1` 。
     * 如果存在解，则 **保证** 它是 **唯一** 的。
     *
     * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * 输出: 3
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     */
    /**
     * 思路
     * 局部最优：当前累加 rest[j] 和 curSum 一旦小于0，起始位置至少要是 j+1，因为从 j 开始一定不行
     * 全局最优：找到可以跑一圈的起始位置
     */
    public int canCompleteCircuit(int[] gas, int[] cost){
        int curSum = 0;
        int totalSum = 0;
        int index = 0;
        for(int i=0; i<gas.length; i++){
            totalSum += gas[i] - cost[i];

            curSum += gas[i] - cost[i];
            if(curSum < 0){
                //下一个位置
                index = (i+1)%gas.length;
                curSum = 0;
            }
        }
        if(totalSum < 0){
            return -1;
        }
        return index;
    }

    /**
     * 9、监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视**其父对象、自身及其直接子对象。**
     * 计算监控树的所有节点所需的最小摄像头数量
     *
     * 输入：[0,0,null,0,0]
     * 输出：1
     * 解释：如图所示，一台摄像头足以监控所有节点。
     */
    int res = 0;
    public int minCameraCover(TreeNode root) {
        if(minCame(root) == 0){
            res++;
        }
        return res;
    }

    public int minCame(TreeNode root){
        if(root == null){
            return 2;
        }
        int left = minCame(root.left);
        int right = minCame(root.right);

        if(left == 2 && right == 2){
            return 0;
        }
        else if(left == 0 || right == 0){
            res++;
            return 1;
        }
        else{
            return 2;
        }
    }
}

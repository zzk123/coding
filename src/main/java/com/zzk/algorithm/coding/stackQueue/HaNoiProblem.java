package com.zzk.algorithm.coding.stackQueue;

/**
 * @program: coding
 * @description: 6.用栈来求解汉诺塔问题 - 汉诺塔问题 - 递归方式
 * @author: zzk
 * @create: 2020-06-03 23:07
 */
public class HaNoiProblem {

    public static void main(String[] args) {
        hanoiProblem1(100, "left", "mid", "right");
    }

    public static int hanoiProblem1(int num, String left, String mid, String right){
        if(num < 1){
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right, String from, String to){
        //如果只有一个盘子
        if(num == 1){
            //并且其中一个端点是中间的情况 只需走一步
            if(from.equals(mid) || to.equals(mid)){
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            }else{
                //不过中点 需要走两步
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        //n个盘子的时候
        //并且其中一个端点在中间的情况 有三大步骤
        if(from.equals(mid) || to.equals(mid)){
            //another表示左端或者右端 来保证定有从左到右
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            //part1指n-1从左到右走的步数
            //n-1从左到中（或者从右到中）
            int part1 = process(num-1, left, mid, right, from, another);
            //part2指n号盘走的一步
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            //n盘移动到中间后 n-1重新返回中
            int part3 = process(num -1 , left, mid, right, another, to);
            // 返回所有的步数
            return part1 + part2 + part3;
        }else{
            //其中没有端点在中点上，即要么从左到右 要么从右到左
            //a.n-1从左到右
            int part1 = process(num - 1, left, mid, right, from, to);
            //b.底盘从左到中间
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            //c.n-1从右到左边
            int part3 = process(num - 1, left, mid, right, to, from);
            //d.底盘从中间走到右盘
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            //e.n-1从左边再到右边
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }
}

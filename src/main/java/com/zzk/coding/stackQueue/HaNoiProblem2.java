package com.zzk.coding.stackQueue;

import java.util.Stack;

/**
 * @program: coding
 * @description: 6.用栈来求解汉诺塔问题 - 汉诺塔问题 - 非递归方式
 * @author: zzk
 * @create: 2020-06-03 23:45
 */
public class HaNoiProblem2 {

    public enum Action{
        No, LToM, MToL, MToR, RToM
    }

    public int hanoiProblem2(int num, String left, String mid, String right){
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for(int i=num; i>0; i++){
            lS.push(i);
        }
        Action[] record = {Action.No};
        int step = 0;
        while(rS.size() != num + 1){
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToL, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    public static int fStackTotStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack,
                                     Stack<Integer> tStack, String from, String to){
        if(record[0] != preNoAct && fStack.peek() < tStack.peek()){
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }
}

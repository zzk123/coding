package com.zzk.algorithm.coding.other;

/**
 * @ClassName PrintAllFold
 * @Description 6.折纸问题
 * @Author zzk
 * @Date 2021/2/23 23:40
 **/
public class PrintAllFold {

    /**
     * 时间复杂度为O(2^n) 空间复杂度为O(n)
     * @param N
     */
    public void printAllFolds(int N){
        printProcess(1, N, true);
    }

    private void printProcess(int i, int N, boolean down) {
        if(i > N){
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? " dwon " : " up ");
        printProcess(i + 1, N ,false);
    }

}

package com.zzk.algorithm.coding.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName GetMinPathValue
 * @Description 24.求最短通路值
 * @Author zzk
 * @Date 2021/2/6 16:31
 **/
public class GetMinPathValue {

    /**
     * 时间复杂度为 O(N*M)，空间复杂度为O(N * M)
     * @param m
     * @return
     */
    public int minPathValue(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0 || m[0][0] != 1
                    || m[m.length - 1][m[0].length - 1] != 1){
            return 0;
        }
        int res = 0;
        int[][] map = new int[m.length][m[0].length];
        map[0][0] = 1;
        Queue<Integer> rQ = new LinkedList<>();
        Queue<Integer> cQ = new LinkedList<>();
        rQ.add(0);
        cQ.add(0);
        int r = 0;
        int c = 0;
        while(!rQ.isEmpty()){
            r = rQ.poll();
            c = cQ.poll();
            if(r == m.length - 1 && c == m[0].length - 1){
                return map[r][c];
            }
            walkTo(map[r][c], r - 1, c, m, map, rQ, cQ);    //up
            walkTo(map[r][c], r + 1, c, m, map, rQ, cQ);    //down
            walkTo(map[r][c], r, c - 1, m, map, rQ, cQ);    //left
            walkTo(map[r][c], r, c + 1, m, map, rQ, cQ);    //right
        }
        return res;
    }

    public void walkTo(int pre, int toR, int toC, int[][] m,
                       int[][] map, Queue<Integer> rQ, Queue<Integer> cQ){
        if(toR < 0 || toR == m.length || toC < 0 || toC == m[0].length
                || m[toR][toC] != 1 || map[toR][toC] != 0){
            return;
        }
        map[toR][toC] = pre + 1;
        rQ.add(toR);
        cQ.add(toC);
    }
}

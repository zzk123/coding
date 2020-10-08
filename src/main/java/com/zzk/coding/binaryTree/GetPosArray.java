package com.zzk.coding.binaryTree;

import java.util.HashMap;

/**
 * @program: coding
 * @description: 22.通过先序和中序数组生成后序数组
 * @author: zzk
 * @create: 2020-10-08 15:10
 */
public class GetPosArray {

    /**
     * 通过先序和中序数组生成后序数组
     * @param pre
     * @param in
     * @return
     */
    public int[] getPosArray(int[] pre, int[] in){
        if(pre == null || in == null){
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0,  len - 1, pos, len - 1, map);
        return pos;
    }

    public int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si, HashMap<Integer, Integer> map){
        if(pi > pj){
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i + 1, pj, n, i+1, nj, s, si, map);
        return setPos(p, pi+1, pi+i-ni, n, ni, i-1, s, si, map);
    }
}

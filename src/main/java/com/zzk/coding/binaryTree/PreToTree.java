package com.zzk.coding.binaryTree;

import java.util.HashMap;

/**
 * @program: coding
 * @description: 21.先序、中序和后序数组两两结合重构二叉树
 * @author: zzk
 * @create: 2020-10-08 14:37
 */
public class PreToTree {

    /**
     * 节点定义
     */
    public class Node{

        public int value;

        public Node left;

        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 先序和中序结合重构二叉树
     * 时间复杂度为O(N)
     * @param pre
     * @param in
     * @return
     */
    public Node preInToTree(int[] pre, int[] in){
        if(pre == null || in == null){
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<in.length; i++){
            map.put(in[i], i);
        }
        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    /**
     *
     * @param p
     * @param pi
     * @param pj
     * @param n
     * @param ni
     * @param nj
     * @param map
     * @return
     */
    public Node preIn(int[] p, int pi, int pj, int[] n, int ni, int nj, HashMap<Integer, Integer> map){
        if(pi > pj){
            return null;
        }
        Node head = new Node(p[pi]);
        int index = map.get(p[pi]);
        head.left = preIn(p, pi+1, pi+index-ni, n, ni, index-1, map);
        head.right = preIn(p,  pi+index-ni+1, pj, n, index+1, nj, map);
        return head;
    }


    /**
     * 中序和后序重构成二叉树
     * @param in
     * @param pos
     * @return
     */
    public Node inPosToTree(int[] in, int[] pos){
        if(in == null || pos == null){
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<in.length; i++){
            map.put(in[i], i);
        }
        return inPos(in, 0 , in.length - 1, pos, 0, pos.length - 1, map);
    }

    /**
     *
     * @param n
     * @param ni
     * @param nj
     * @param s
     * @param si
     * @param sj
     * @param map
     * @return
     */
    public Node inPos(int[] n, int ni, int nj, int[] s, int si, int sj, HashMap<Integer, Integer> map){
        if(si > sj){
            return null;
        }
        Node head = new Node(s[sj]);
        int index = map.get(s[sj]);
        head.left = inPos(n, ni, index-1, s, si, si+index-ni-1, map);
        head.right = inPos(n, index+1, nj, s, si+index-ni, sj-1, map);
        return head;
    }


    /**
     * 先序+后序重构二叉树
     * 注：只有一颗二叉树除叶子节点之外，其他所有节点都有左孩子和右孩子，这样的树才可以被先序和后序数组重构出现
     * @param pre
     * @param pos
     * @return
     */
    public Node prePosToTree(int[] pre, int[] pos){
        if(pre == null || pos == null){
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<pos.length; i++){
            map.put(pos[i], i);
        }
        return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, map);
    }
    public Node prePos(int[] p, int pi, int pj, int[] s, int si, int sj, HashMap<Integer, Integer> map){
        Node head = new Node(s[sj--]);
        if(pi == pj){
            return head;
        }
        int index = map.get(p[++pi]);
        head.left = prePos(p, pi, pi+index-si, s, si, index, map);
        head.right = prePos(p, pi + index - si + 1, pj, s, index + 1, sj , map);
        return head;
    }
}

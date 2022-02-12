package com.zzk.algorithm.coding.binaryTree;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2020-10-03 14:09
 */
public class BstTopoSize2 {

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
     * 节点贡献值对象定义
     */
    public class Record{

        public int l;

        public int r;

        public Record(int left, int right){
            this.l = left;
            this.r = right;
        }
    }

    /**
     * 返回搜索二叉树的最大拓扑结构节点数
     * 二叉树的节点数为N，时间复杂度的最好为O(N)，最差为O(NlogN)
     * @param head
     * @return
     */
    public int bstTopoSize2(Node head){
        Map<Node, Record> map = new HashMap<>();
        return posOrder(head, map);
    }

    /**
     * 迭代查找，返回搜索二叉树的最大拓扑结构节点数
     * @param h
     * @param map
     * @return
     */
    public int posOrder(Node h, Map<Node, Record> map){
        if(h == null){
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    /**
     * 节点贡献值查找
     * @param n
     * @param v
     * @param m
     * @param s
     * @return
     */
    public int modifyMap(Node n, int v, Map<Node, Record> m, boolean s){
        if(n == null || (!m.containsKey(n))){
            return 0;
        }
        Record r = m.get(n);
        if((s && n.value > v) || ((!s) && n.value < v)){
            m.remove(n);
            return r.l + r.r + 1;
        }else{
            int minus = modifyMap(s ? n.right : n.left, v, m, s);
            if(s){
                r.r = r.r - minus;
            }else{
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }
    }
}

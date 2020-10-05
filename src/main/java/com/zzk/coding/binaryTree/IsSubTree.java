package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description:  12.判断t1树中是否有与t2树拓扑结构完全相同的子树
 * @author: zzk
 * @create: 2020-10-05 15:14
 */
public class IsSubTree {

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
     * 将树转换成字符串。根据kmp算法进行匹配
     * t1的节点数为N，t2的节点数为M，时间复杂度为 O(M+N)
     * @param t1
     * @param t2
     * @return
     */
    public boolean isSubtree(Node t1, Node t2){
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    /**
     * 将树变成字符串
     * @param head
     * @return
     */
    public String serialByPre(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // KMP
    public int getIndexOf(String s, String m){
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArry(ms);
        while(si < ss.length && mi < ms.length){
            if(ss[si] == ms[mi]){
                si++;
                mi++;
            }else if(next[mi] == -1){
                si++;
            }else{
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public int[] getNextArry(char[] ms){
        if(ms.length == 1){
            return new int[] {-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 1;
        int pos = 2;
        int cn = 0;
        while(pos < next.length){
            if(ms[pos - 1] == ms[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[pos++] = 0;
            }
        }
        return next;
    }
}

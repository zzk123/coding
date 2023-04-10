package com.zzk.algorithm.carl.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PerfaceNode {
    public int val;
    public PerfaceNode left;
    public PerfaceNode right;
    public PerfaceNode next;

    public PerfaceNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}

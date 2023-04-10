package com.zzk.algorithm.carl.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

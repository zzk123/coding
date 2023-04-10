package com.zzk.algorithm.carl.tree;

import lombok.Data;

import java.util.List;

@Data
public class Node {

    public int val;
    public List<Node> children;
}

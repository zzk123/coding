package com.zzk.coding.binaryTree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @program: coding
 * @description: 18.在二叉树中找到两个节点的最近公共祖先
 * @author: zzk
 * @create: 2020-10-06 13:43
 */
public class LowestAncestor {

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
     * ========================= 原问题
     */
    /**
     * 查找两个节点的最近公共祖先
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public Node lowestAncestor(Node head, Node o1, Node  o2){
        if(head == null || head == o1 || head == o2){
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        //返回公共祖先节点
        if(left != null && right != null){
            return head;
        }
        //返回对应上的节点
        return left != null ? left : right;
    }
    /**
     * ========================= 进阶问题
     */

    /**
     * 建立二叉树中每个节点对应的父结点信息
     * 建立记录过程时间复杂度为O(N)，额外空间复杂度为O(N)
     * 查询操作时，时间复杂度为O(h)，其中h为二叉树的高度
     */
    public class Record1{
        private HashMap<Node, Node> map;

        public Record1(Node head){
            map = new HashMap<>();
            if(head != null){
                map.put(head, null);
            }

        }

        private void setMap(Node head){
            if(head == null){
                return;
            }
            if(head.left != null){
                map.put(head.left, head);
            }
            if(head.right != null){
                map.put(head.right, head);
            }
            setMap(head.left);
            setMap(head.right);
        }

        public Node query(Node o1, Node o2){
            HashSet<Node> path = new HashSet<>();
            while(map.containsKey(o1)){
                path.add(o1);
                o1 = map.get(o1);
            }
            while(!path.contains(o2)){
                o2 = map.get(o2);
            }
            return o2;
        }
    }


    /**
     * 直接建立任意两个节点之间的最近公共祖先记录，以便于以后查询时直接查
     * 建立记录的具体过程如下：
     * 1.对二叉树中的每颗子树（一共N棵）都进行步骤2
     * 2.假设子树的头节点h，h所有的后代节点和h节点的最近公共祖先都是h，记录下来。
     *        h左子树的每个节点和h右子树的每个节点的最近公共祖先都是h，记录下来
     *
     * 如果二叉树的节点数为N，想要记录每个节点之间的信息，信息的条数为((N-1)*N)/2
     * 额外空间复杂度O(N^2)，时间复杂度为O(N^2)，单次查询的时间复杂度为O(1)
     */
    public class Record2{

        private HashMap<Node, HashMap<Node, Node>> map;

        public Record2(Node head){
            map = new HashMap<Node, HashMap<Node, Node>>();

        }

        private void initMap(Node head){
            if(head == null){
                return;
            }
            map.put(head, new HashMap<Node, Node>());
            initMap(head.left);
            initMap(head.right);
        }

        private void setMap(Node head){
            if(head == null){
                return;
            }
            headRecord(head.left, head);
            headRecord(head.right, head);
            subRecord(head);
            setMap(head.left);
            setMap(head.right);
        }

        private void headRecord(Node n, Node h){
            if(n == null){
                return;
            }
            map.get(n).put(h, h);
            headRecord(n.left, h);
            headRecord(n.right, h);
        }

        private void subRecord(Node head){
            if(head == null){
                return;
            }
            preLeft(head.left, head.right, head);
            subRecord(head.left);
            subRecord(head.right);
        }

        private void preLeft(Node l, Node r, Node h){
            if(l == null){
                return;
            }
            preRight(l, r, h);
            preLeft(l.left, r, h);
            preLeft(l.right, r, h);
        }

        private void preRight(Node l, Node r, Node h){
            if(r == null){
                return;
            }
            map.get(l).put(r, h);
            preRight(l, r.left, h);
            preRight(l, r.right, h);
        }

        public Node query(Node o1, Node o2){
            if(o1 == o2){
                return o1;
            }
            if(map.containsKey(o1)){
                return map.get(o1).get(o2);
            }
            if(map.containsKey(o2)){
                return map.get(o2).get(o1);
            }
            return null;
        }
    }
}

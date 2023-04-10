package com.zzk.algorithm.carl.tree;

import java.util.*;

/**
 * 遍历方式 - 层次遍历
 */
public class TreeTraversalDemo1 {

    /**
     * 1、深度遍历
     */
    public List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root){
        checkFun(root, 0);
        return resList;
    }

    public void checkFun(TreeNode node, Integer deep){
        if(node == null){
            return;
        }
        deep++;
        if(resList.size() < deep){
            List<Integer> item = new ArrayList<>();
            resList.add(item);
        }
        resList.get(deep-1).add(node.val);

        checkFun(node.left, deep);
        checkFun(node.right, deep);
    }
    /**
     * 2、广度遍历
     */
    public List<List<Integer>> resList2 = new ArrayList<>();

    public List<List<Integer>> levelOrder2(TreeNode root){
        checkFun(root);
        return resList2;
    }

    public void checkFun(TreeNode node){
        if(node == null){
            return;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(node);

        while (!que.isEmpty()){
            List<Integer> itemList = new ArrayList<>();
            int len = que.size();

            while(len > 0){
                TreeNode tmpNode = que.poll();
                itemList.add(tmpNode.val);

                if(tmpNode.left != null){
                    que.offer(tmpNode.left);
                }

                if(tmpNode.right != null){
                    que.offer(tmpNode.right);
                }

                len--;
            }
            resList2.add(itemList);
        }
    }

    /**
     * 3、二叉树的层序遍历
     * 给你二叉树的根节点 `root` ，返回其节点值的 **层序遍历** 。 （即逐层地，从左到右访问所有节点）。
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     */
    public List<List<Integer>> levelOrder3(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            List<Integer> element = new ArrayList<>();
            int size = q.size();
            for(int i=0; i<size; i++){
                TreeNode node = q.poll();
                element.add(node.val);
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
            result.add(element);
        }
        return result;
    }

    /**
     * 4、二叉树的层序遍历 II
     * 给你二叉树的根节点 `root` ，返回其节点值 **自底向上的层序遍历** 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[15,7],[9,20],[3]]
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        Deque<TreeNode> que = new LinkedList<>();

        if(root == null){
            return list;
        }

        que.offerLast(root);
        while(!que.isEmpty()){
            List<Integer> levelList = new ArrayList<>();

            int levelSize = que.size();
            for(int i=0; i<levelSize; i++){
                TreeNode peek = que.peekFirst();
                levelList.add(que.pollFirst().val);

                if(peek.left != null){
                    que.offerLast(peek.left);
                }

                if(peek.right != null){
                    que.offerLast(peek.right);
                }
            }
            list.add(levelList);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = list.size()-1; i >= 0; i--){
            result.add(list.get(i));
        }

        return result;
    }

    /**
     * 5、二叉树的右视图
     * 给定一个二叉树的 **根节点** `root`，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     *  输入: [1,2,3,null,5,null,4]
     *  输出: [1,3,4]
     */
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0; i<size; i++){
                TreeNode node = que.poll();
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
                if(i == size-1){
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    /**
     * 6、二叉树的层平均值
     * 给定一个非空二叉树的根节点 `root` , 以数组的形式返回每一层节点的平均值。与实际答案相差 `10-5` 以内的答案可以被接受
     */
    public List<Double> averageOfLevels(TreeNode root){
        List<Double> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        while(!que.isEmpty()){
            int size = que.size();
            double sum = 0;
            for(int i=0; i<size; i++){
                TreeNode node = que.poll();
                sum += node.val;
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
            result.add(sum/size);
        }
        return result;
    }

    /**
     * 7、N 叉树的层序遍历
     * 给定一个 N 叉树，返回其节点值的*层序遍历*。（即从左到右，逐层遍历）。
     * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
     *
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[[1],[3,2,4],[5,6]]
     */
    public List<List<Integer>> levelOrder3(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        LinkedList<Node> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> item = new ArrayList<>();
            for(int i=0; i<size; i++){
                Node node = que.poll();
                item.add(node.val);
                if(node.children != null){
                    que.addAll(node.children);
                }
            }
        }
        return result;
    }
    /**
     * 8、在每个树行中找最大值
     * 给定一棵二叉树的根节点 `root` ，请找出该二叉树中每一层的最大值。
     *
     * 输入: root = [1,3,2,5,3,null,9]
     * 输出: [1,3,9]
     */
    public List<Integer> largestValue(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            int max = Integer.MIN_VALUE;
            int size = que.size();
            for(int i=0; i<size; i++){
                TreeNode node = que.poll();
                max = Math.max(max, node.val);
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }

    /**
     * 9、填充每个节点的下一个右侧节点指针
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 `NULL`。
     * 初始状态下，所有 next 指针都被设置为 `NULL`。
     *
     * 输入：root = [1,2,3,4,5,6,7]
     * 输出：[1,#,2,3,#,4,5,6,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，
     * 如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
     */
    public PerfaceNode connect(PerfaceNode root){
        if(root == null){
            return root;
        }
        Queue<PerfaceNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            PerfaceNode pre = null;
            for(int i=0; i<size; i++){
                PerfaceNode node = que.poll();
                if(pre != null){
                    pre.next = node;
                }
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
                if(i == size-1){
                    node.next = null;
                }
                pre = node;
            }
        }
        return root;
    }

    public PerfaceNode connect2(PerfaceNode root){
        if(root == null){
            return root;
        }
        PerfaceNode cur = root;
        while(cur != null){
            PerfaceNode dummy = new PerfaceNode(0);
            //pre表示访下一层节点的前一个节点
            PerfaceNode pre = dummy;
            while(cur != null){
                //如果当前节点的左子节点不为空，就让pre节点
                //的next指向他，也就是把它串起来
                if(cur.left != null){
                    pre.next = cur.left;
                    pre = pre.next;
                }
                //同理参照左子树
                if(cur.right != null){
                    pre.next = cur.right;
                    pre = pre.next;
                }
                //指向隔壁节点
                cur = cur.next;
            }
            //指向下一层
            cur = dummy.next;
        }
        return root;
    }

    /**
     * 10、二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 给定二叉树 `[3,9,20,null,null,15,7]`，
     * 返回它的最大深度 3
     */
    public int maxDepth(TreeNode root){
        Integer maxSize = 0;
        return inOrder(root, 1, maxSize);
    }

    public int inOrder(TreeNode root, int size, int maxSize){
        if(root == null){
            return maxSize;
        }
        if(size > maxSize){
            maxSize = size;
        }
        int leftMaxSize = inOrder(root.left, size+1, maxSize);
        int rightMaxSize = inOrder(root.right, size+1, maxSize);
        maxSize = leftMaxSize > maxSize ? leftMaxSize : maxSize;
        maxSize = rightMaxSize > maxSize ? rightMaxSize : maxSize;
        return maxSize;
    }

    /**
     * 11、二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * **说明：**叶子节点是指没有子节点的节点
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     */
    public int minDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1, m2) + 1;
    }

}

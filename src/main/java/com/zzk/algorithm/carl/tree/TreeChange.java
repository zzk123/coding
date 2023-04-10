package com.zzk.algorithm.carl.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 二叉树的修改和改造
 */
public class TreeChange {

    /**
     * 1、翻转二叉树
     * 给你一棵二叉树的根节点 `root` ，翻转这棵二叉树，并返回其根节点
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     */
    //递归 时间：O(N)，空间：O(h)
    public TreeNode invertTree(TreeNode root){
        //后序遍历，从下到上交换
        if(root == null){
            return null;
        }
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.right = leftNode;
        root.left = rightNode;
        return root;
    }
    //迭代 时间：O(N)  空间：O(N)
    public TreeNode invertTree2(TreeNode root){
        if(root == null){
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            TreeNode left = tmp.left;
            tmp.left = tmp.right;
            tmp.right = left;
            if(tmp.left != null){
                queue.add(tmp.left);
            }
            if(tmp.right != null){
                queue.add(tmp.right);
            }
        }
        return root;
    }
    /**
     * 2、从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 `inorder` 和 `postorder` ，其中 `inorder` 是二叉树的中序遍历，
     * `postorder` 是同一棵树的后序遍历，请你构造并返回这颗 *二叉树*
     */
    public TreeNode buildTree(int[] inorder, int[] postorder){
        if(inorder.length == 0 || postorder.length == 0){
            return new TreeNode();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            map.put(inorder[i], i);
        }
        return postIn(0, inorder.length-1, postorder, 0, postorder.length-1, map);
    }

    public TreeNode postIn(int pi, int pj, int[] postorder, int ni, int nj, Map<Integer, Integer> map){
        if(pi > pj){
            return null;
        }
        TreeNode node = new TreeNode(postorder[nj]);
        int index = map.get(postorder[nj]);
        node.left = postIn(pi, index-1, postorder, ni, ni+index-pi-1, map);
        node.right = postIn(index+1, pj, postorder, ni+index-pi, nj-1, map);
        return node;
    }

    /**
     * 3、从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 `preorder` 和 `inorder` ，其中 `preorder` 是二叉树的**先序遍历**，
     * `inorder` 是同一棵树的**中序遍历**，请构造二叉树并返回其根节点。
     *
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder){
        if(preorder.length == 0 || inorder.length == 0){
            return new TreeNode();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            map.put(inorder[i], i);
        }
        return preIn(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
    }

    public TreeNode preIn(int[] p, int pi, int pj, int[] inorder, int ni, int nj, Map<Integer, Integer> map){
        if(pi > pj){
            return null;
        }
        TreeNode node = new TreeNode(p[pi]);
        int index = map.get(p[pi]);
        node.left = preIn(p, pi+1, pi+index-ni, inorder, ni, index-1,map);
        node.right = preIn(p, pi+index-ni+1, pj, inorder, index+1, nj, map);
        return node;
    }

    /**
     * 4、最大二叉树
     * 给定一个不重复的整数数组 `nums` 。 **最大二叉树** 可以用下面的算法从 `nums` 递归地构建:
     * 1. 创建一个根节点，其值为 `nums` 中的最大值。
     * 2. 递归地在最大值 **左边** 的 **子数组前缀上** 构建左子树。
     * 3. 递归地在最大值 **右边** 的 **子数组后缀上** 构建右子树。
     * 返回 *`nums` 构建的* ***最大二叉树\***
     *
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     *         - 空数组，无子节点。
     *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     *             - 空数组，无子节点。
     *             - 只有一个元素，所以子节点是一个值为 1 的节点。
     *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     *         - 只有一个元素，所以子节点是一个值为 0 的节点。
     *         - 空数组，无子节点。
     */
    public TreeNode constructMaximumBinaryTree(int[] nums){
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int leftIndex, int rightIndex){
        //没有元素
        if(rightIndex - leftIndex < 1){
            return null;
        }
        //只有一个元素
        if(rightIndex - leftIndex == 1){
            return new TreeNode(nums[leftIndex]);
        }
        //最大值所在位置
        int maxIndex = leftIndex;
        //最大值
        int maxVal = nums[maxIndex];
        for(int i=leftIndex+1; i<rightIndex; i++){
            if(nums[i] > maxVal){
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        //根据maxIndex划分左右子树
        root.left = constructMaximumBinaryTree(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree(nums, maxIndex + 1, rightIndex);
        return root;
    }

    /**
     * 5、合并二叉树
     * 给你两棵二叉树： `root1` 和 `root2` 。
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。
     * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，**不为** null 的节点将直接作为新二叉树的节点。
     * 返回合并后的二叉树。
     * **注意:** 合并过程必须从两个树的根节点开始。
     *
     * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
     * 输出：[3,4,5,5,4,null,7]
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return null;
        }
        TreeNode node = new TreeNode((root1 != null ? root1.val : 0) + (root2 != null ? root2.val : 0));
        node.left = mergeTrees(root1 != null ? root1.left : null , root2 != null ? root2.left : null);
        node.right = mergeTrees(root1 != null ? root1.right : null, root2 != null ? root2.right : null);
        return node;
    }

}

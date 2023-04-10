package com.zzk.algorithm.carl.tree;

import java.util.*;

/**
 * 二叉树的属性
 */
public class TreeProperty {

    /**
     * 1、对称二叉树
     * 给你一个二叉树的根节点 `root` ， 检查它是否轴对称
     *
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     */
    public boolean isSymmetric(TreeNode root){
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right){
        if(left == null && right != null){
            return false;
        }
        if(left != null && right == null){
            return false;
        }
        if(left == null && right == null){
            return true;
        }
        if(left.val != right.val){
            return false;
        }
        return compare(left.left, right.right) && compare(left.right, right.left);
    }


    /**
     * 2、二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数
     *
     * 给定二叉树 `[3,9,20,null,null,15,7]`，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3
     */
    public int maxDepth(TreeNode root){
        int maxSize = 0;
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
     * 3、N 叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）
     *
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：3
     */
    public int maxDepth(Node root){
        if(root == null){
            return 0;
        }
        int depth = 0;
        if(root.children != null){
            for(Node child : root.children){
                depth = Math.max(depth, maxDepth(child));
            }
        }
        return depth+1;
    }

    /**
     * 4、二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
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

    /**
     * 5、完全二叉树的节点个数
     * 给你一棵 **完全二叉树** 的根节点 `root` ，求出该树的节点个数。
     * 完全二叉树的定义如下：
     * 在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的
     * 节点都集中在该层最左边的若干位置。若最底层为第 `h` 层，则该层包含 `1~ 2h` 个节点。
     *
     * 输入：root = [1,2,3,4,5,6]
     * 输出：6
     */
    //通用递归解法
    public int countNodes(TreeNode root){
        if(root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right);
    }
    //迭代法
    public int countNodes2(TreeNode root){
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                TreeNode cur = queue.poll();
                result++;
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return result;
    }

    /**
     * 6、平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树
     */
    public boolean isBalanced(TreeNode root){
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if(leftHeight == -1){
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if(rightHeight == -1){
            return -1;
        }
        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 7、二叉树的所有路径
     * 给你一个二叉树的根节点 `root` ，按 **任意顺序** ，返回所有从根节点到叶子节点的路径。
     * **叶子节点** 是指没有子节点的节点。
     */
    //深度优先
    // - 递归
    //模板
    public void treeDFS(TreeNode root){
        if(root == null){
            return ;
        }
        System.out.println(root.val);
        treeDFS(root.left);
        treeDFS(root.right);
    }
    //解答
    public List<String> binaryTreePath(TreeNode root){
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }
    private void dfs(TreeNode root, String path, List<String> res){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            res.add(path + root.val);
            return;
        }
        dfs(root.left, path + root.val + "->", res);
        dfs(root.right, path + root.val + "->", res);
    }
    // - 非递归
    //模板
    public void treeDFS2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if(root.left != null){
                stack.push(root.left);
            }
            if(root.right != null){
                stack.push(root.right);
            }
        }
    }
    //解答
    public List<String> binaryTreePaths(TreeNode root){
        List<String> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<Object> stack = new Stack<>();
        stack.push(root);
        stack.push(root.val + "");
        while(!stack.isEmpty()){
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            if(node.left == null && node.right == null){
                res.add(path);
            }
            if(root.right != null){
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            if(root.left != null){
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return res;
    }
    //广度优先（BFS）
    //模板
    public void levelOrder(TreeNode tree){
        if(tree == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.val);
            if(tree.left != null){
                queue.add(node.left);
            }
            if(tree.right != null){
                queue.add(node.right);
            }
        }
    }
    //解答
    public List<String> binaryTreePaths2(TreeNode root){
        List<String> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<Object> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root.val + "");
        while(!queue.isEmpty()){
            TreeNode node = (TreeNode) queue.poll();
            String path = (String) queue.poll();
            if(node.left == null && node.right == null){
                res.add(path);
            }
            if(node.right != null){
                queue.add(node.right);
                queue.add(path + "->" + node.right.val);
            }
            if(node.left != null){
                queue.add(node.left);
                queue.add(path + "->" + node.left.val);
            }
        }
        return res;
    }
    //递归
    public List<String> binaryTreePaths3(TreeNode root){
        List<String> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        if(root.left == null && root.right == null){
            res.add(root.val + "");
            return res;
        }
        for(String path : binaryTreePaths3(root.left)){
            res.add(root.val + "->" + path);
        }
        for(String path : binaryTreePaths3(root.right)){
            res.add(root.val + "->" + path);
        }
        return res;
    }
    /**
     * 8、左叶子之和
     * 给定二叉树的根节点 `root` ，返回所有左叶子之和
     *
     * 输入: root = [3,9,20,null,null,15,7]
     * 输出: 24
     * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     */
    //求所有节点之和
    public int sumOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        int leave = root.val;
        int left = sumOfTree(root.left);
        int right = sumOfTree(root.right);
        return leave + left + right;
    }
    //所有叶子节点之和
    public int sumOfTrees(TreeNode root){
        if(root == null){
            return 0;
        }
        int leave = 0;
        if(root.left == null && root.right == null){
            leave = root.val;
        }
        int left = sumOfTrees(root.left);
        int right = sumOfTrees(root.right);
        return left + right + leave;
    }
    //求所有左叶子节点之和
    public int sumOfTrees(TreeNode root, boolean isFromleft){
        if(root == null){
            return 0;
        }
        int leave = 0;
        if(isFromleft && root.left == null && root.right == null){
            leave = root.val;
        }
        int left = sumOfTrees(root.left, true);
        int right = sumOfTrees(root.right, false);
        return leave + left + right;
    }

    /**
     * 9、找树左下角的值
     * 给定一个二叉树的 **根节点** `root`，请找出该二叉树的 **最底层 最左边** 节点的值。
     * 假设二叉树中至少有一个节点
     */
    public int findBottomLeftValue(TreeNode root){
        Deque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        int ans = 0;
        while(!d.isEmpty()){
            int sz = d.size();
            ans = d.peek().val;
            while(sz-- > 0){
                TreeNode poll = d.pollFirst();
                if(poll.left != null){
                    d.addLast(poll.left);
                }
                if(poll.right != null){
                    d.addLast(poll.right);
                }
            }
        }
        return ans;
    }
    /**
     * 10、路径总和
     * 给你二叉树的根节点 `root` 和一个表示目标和的整数 `targetSum` 。判断该树中是否存在 **根节点到叶子节点** 的路径，
     * 这条路径上所有节点值相加等于目标和 `targetSum` 。如果存在，返回 `true` ；否则，返回 `false`
     * **叶子节点** 是指没有子节点的节点。
     */
    public boolean hasPathSum(TreeNode root, int targetSum){
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null && targetSum == root.val){
            return true;
        }
        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val);
    }
    /**
     * 11、路径总和 II
     * 给你二叉树的根节点 `root` 和一个整数目标和 `targetSum` ，找出所有 **从根节点到叶子节点** 路径总和等于给定目标和的路径。
     * **叶子节点** 是指没有子节点的节点。
     */
    //1、递归
    public List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum){
        pathSum(root, targetSum, new ArrayList<>());
        return result;
    }
    public void pathSum(TreeNode root, int targetSum, List<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.val);
        if(root.left == null && root.right == null && targetSum - root.val == 0){
            result.add(res);
            return;
        }
        pathSum(root.left, targetSum-root.val, new ArrayList<>(res));
        pathSum(root.right,targetSum-root.val, new ArrayList<>(res));
    }
    //2、回溯，往下减
    public List<List<Integer>> pathSum2(TreeNode root, int sum){
        List<List<Integer>> result = new ArrayList<>();
        dfs2(root, sum, new ArrayList<>(), result);
        return result;
    }

    public void dfs2(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result){
        if(root == null){
            return;
        }
        list.add(new Integer(root.val));
        if(root.left == null && root.right == null){
            if(sum == root.val){
                result.add(new ArrayList<>(list));
            }
            list.remove(list.size()-1);
            return;
        }
        dfs2(root.left, sum-root.val, list, result);
        dfs2(root.right, sum-root.val, list, result);
        list.remove(list.size()-1);
    }
    //3、回溯，往下加
    public List<List<Integer>> pathSum3(TreeNode root, int sum){
        List<List<Integer>> result = new ArrayList<>();
        dfs3(root, sum, 0, new ArrayList<>(), result);
        return result;
    }

    public void dfs3(TreeNode root, int sum, int total, List<Integer> list, List<List<Integer>> result){
        if(root == null){
            return;
        }
        list.add(new Integer(root.val));
        total += root.val;
        if(root.left == null && root.right == null){
            if(sum == total){
                result.add(new ArrayList<>(list));
            }
            list.remove(list.size()-1);
            return;
        }
        dfs3(root.left, sum, total, list, result);
        dfs3(root.right, sum, total, list, result);
        list.remove(list.size()-1);
    }

}

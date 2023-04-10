package com.zzk.algorithm.carl.tree;

/**
 * 二叉搜索树
 * 二叉搜索树是一个有序树：
 * - 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * - 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * - 它的左、右子树也分别为二叉搜索树
 */
public class SearchTreeDemo {

    /**
     * 1、二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点 `root` 和一个整数值 `val`。
     * 你需要在 BST 中找到节点值等于 `val` 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 `null`
     *
     * 输入：root = [4,2,7,1,3], val = 2
     * 输出：[2,1,3]
     *
     */
    public TreeNode searchBST(TreeNode root, int val){
        if(root == null || root.val == val){
            return root;
        }
        if(val < root.val){
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }

    /**
     * 2、验证二叉搜索树
     * 给你一个二叉树的根节点 `root` ，判断其是否是一个有效的二叉搜索树。
     * **有效** 二叉搜索树定义如下：
     * - 节点的左子树只包含 **小于** 当前节点的数。
     * - 节点的右子树只包含 **大于** 当前节点的数。
     * - 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 输入：root = [2,1,3]
     * 输出：true
     */
    public boolean isValidBST(TreeNode root){
        return validBST(Long.MIN_VALUE, Long.MAX_VALUE, root);
    }

    public boolean validBST(long lower, long upper, TreeNode root){
        if(root == null){
            return true;
        }
        if(root.val <= lower || root.val >= upper){
            return false;
        }
        return validBST(lower, root.val, root.left) && validBST(root.val, upper, root.right);
    }

    /**
     * 3、二叉搜索树的最小绝对差
     * 给你一个二叉搜索树的根节点 `root` ，返回 **树中任意两不同节点值之间的最小差值** 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     *
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     */
    //记录前后两个节点差值的最小值
    int min = Integer.MAX_VALUE;
    //记录前一个节点
    TreeNode prev;
    public int getMinimumDifference(TreeNode root){
        inorder(root);
        return min;
    }

    public void inorder(TreeNode root){
        if(root == null){
            return;
        }
        //遍历左子树
        inorder(root.left);
        //记录差值
        if(prev != null){
            min = Math.min(min, root.val - prev.val);
        }
        //当前节点遍历之后变成前一个节点
        prev = root;

        //遍历右子树
        inorder(root.right);
    }

    /**
     * 4、把二叉搜索树转换为累加树
     * 给出二叉 **搜索** 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     * 使每个节点 `node` 的新值等于原树中大于或等于 `node.val` 的值之和。
     * 提醒一下，二叉搜索树满足下列约束条件：
     * - 节点的左子树仅包含键 **小于** 节点键的节点。
     * - 节点的右子树仅包含键 **大于** 节点键的节点。
     * - 左右子树也必须是二叉搜索树。
     *
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     */
    int sum = 0;
    public TreeNode converBST(TreeNode root){
        sum = 0;
        converBST1(root);
        return root;
    }

    public void converBST1(TreeNode root){
        if(root == null){
            return;
        }
        converBST1(root.right);
        sum += root.val;
        converBST1(root.left);
    }

    /**
     * 5、二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点 `root` 和要插入树中的值 `value` ，将值插入二叉搜索树。
     * 返回插入后二叉搜索树的根节点。 输入数据 **保证** ，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * 输入：root = [40,20,60,10,30,50,70], val = 25
     * 输出：[40,20,60,10,30,50,70,null,null,25]
     */
    //非递归
    public TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode cur = root;
        while(true){
            if(cur.val > val){
                if(cur.left != null){
                    cur = cur.left;
                }else{
                    cur.left = new TreeNode(val);
                    return root;
                }
            }else{
                if(cur.right != null){
                    cur = cur.right;
                }else{
                    cur.right = new TreeNode(val);
                    return root;
                }
            }
        }
    }
    //递归
    public TreeNode insertIntoBST2(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val > val){
            root.left = insertIntoBST2(root.left, val);
        }else{
            root.right = insertIntoBST2(root.right, val);
        }
        return root;
    }
    /**
     * 6、删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 **root** 和一个值 **key**，删除二叉搜索树中的 **key** 对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 一般来说，删除节点可分为两个步骤：
     * 1. 首先找到需要删除的节点；
     * 2. 如果找到了，删除它。
     */
    public TreeNode deleteNode(TreeNode root, int key){
        root = deleteNode(root, key);
        return root;
    }
    private TreeNode delete(TreeNode root, int key){
        if(root == null){
            return null;
        }
        if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else{
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            //当前节点既有左子树又有右子树
            TreeNode node = root.right;
            //找到当前节点右子树最左边的叶子结点
            while(node.left != null){
                node = node.left;
            }
            //将root的左子树放到root的右子树的最下面的左叶子节点的左子树上
            node.left = root.left;
            return root.right;
        }
        return root;
    }

    /**
     * 7、修剪二叉搜索树
     * 给你二叉搜索树的根节点 `root` ，同时给定最小边界`low` 和最大边界 `high`。通过修剪二叉搜索树，使得所有节点的值在`[low, high]`中。
     * 修剪树 **不应该** 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 **唯一的答案** 。
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变
     *
     * 输入：root = [1,0,2], low = 1, high = 2
     * 输出：[1,null,2]
     */
    public TreeNode trimBST(TreeNode root, int low, int high){
        if(root == null){
            return null;
        }
        if(root.val < low){
            return trimBST(root.right, low, high);
        }
        if(root.val > high){
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    /**
     * 8、将有序数组转换为二叉搜索树
     *给你一个整数数组 `nums` ，其中元素已经按 **升序** 排列，请你将其转换为一棵 **高度平衡** 二叉搜索树。
     * **高度平衡** 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     *
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     */
    public TreeNode sortedArrayToBST(int[] nums){
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right){
        if(left >= right){
            return null;
        }
        if(right - left == 1){
            return new TreeNode(nums[left]);
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid);
        root.right = sortedArrayToBST(nums, mid+1, right);
        return root;
    }
}

package com.zzk.coding.binaryTree;

/**
 * @program: coding
 * @description: 14.根据后序数组重建搜索二叉树
 * @author: zzk
 * @create: 2020-10-05 16:11
 */
public class PosArrayToBST {

    /**
     * 节点定义
     */
    static class Node{

        public int value;

        public Node left;

        public Node right;

        public Node(int value){
            this.value = value;
        }
    }


    /**
     * 判断数组是否为二叉树后序遍历的结果
     * @param arr
     * @return
     */
    public static boolean isPostArray(int[] arr){
        if(arr == null || arr.length == 0){
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    /**
     * 迭代判断，开始到结束的数组下的节点
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static boolean isPost(int[] arr, int start, int end){
        if(start == end){
            return true;
        }
        int less = -1;
        int more = end;
        for(int i = start; i < end; i++){
            if(arr[end] > arr[i]){
                less = i;
            }else{
                more = more == end ? i : more;
            }
        }
        if(less == -1 || more == end){
            return isPost(arr, start, end-1);
        }
        if(less != more - 1){
            return false;
        }
        return isPost(arr, start, less) && isPost(arr, more, end -1);
    }

    /**
     * 将后序遍历的结果组转成树
     * @param posArr
     * @param start
     * @param end
     * @return
     */
    public static Node posToBST(int[] posArr, int start, int end){
        if(start > end){
            return null;
        }
        Node head = new Node(posArr[end]);
        int less = -1;
        int more = end;
        for(int i = start; i < end; i++){
            if(posArr[end] > posArr[i]){
                less = i;
            }else{
                more = more == end ? i : more;
            }
        }
        head.left = posToBST(posArr, start, less);
        head.right = posToBST(posArr, more, end-1);
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,3,6,5,7,4};
        isPostArray(arr);
    }
}

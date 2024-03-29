package com.zzk.algorithm.coding.stackQueue;

import java.util.HashMap;
import java.util.Stack;

/**
 * @program: coding
 * @description: 7. 构造数组的MaxTree
 * @author: zzk
 * @create: 2020-09-13 21:19
 */
public class GetMaxTree {

    /**
     * 树节点
     */
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static Node getMaxTree(int[] arr){
        Node[] nArr = new Node[arr.length];
        for(int i = 0; i != arr.length; i++){
            nArr[i] = new Node(arr[i]);
        }
        Stack<Node> stack = new Stack<Node>();
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();

        for(int i = 0; i != nArr.length; i++){
            Node curNode = nArr[i];
            while((!stack.isEmpty()) && stack.peek().value < curNode.value){
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curNode);
        }

        while(!stack.isEmpty()){
            popStackSetMap(stack, lBigMap);
        }

        for(int i = nArr.length - 1; i != -1; i--){
            Node curNode = nArr[i];
            while((!stack.isEmpty()) && stack.peek().value < curNode.value){
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }

        while (!stack.isEmpty()){
            popStackSetMap(stack, rBigMap);
        }

        System.out.println(rBigMap.toString());
        System.out.println(lBigMap.toString());

        Node head = null;

        for(int i = 0; i != nArr.length; i++){
            Node curNode = nArr[i];
            Node left = lBigMap.get(curNode);
            Node right = rBigMap.get(curNode);
            if(left == null && right == null){
                head = curNode;
            }else if(left == null){
                if(right.left == null){
                    right.left = curNode;
                }else{
                    right.right = curNode;
                }
            }else if(right == null){
                if(left.left == null){
                    left.left = curNode;
                }else{
                    left.right = curNode;
                }
            }else{
                Node parent = left.value < right.value ? left : right;
                if(parent.left == null){
                    parent.left = curNode;
                }else{
                    parent.right = curNode;
                }
            }
        }
        return head;
    }

    public static void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map){
        Node popNode = stack.pop();
        if(stack.isEmpty()){
            map.put(popNode, null);
        }else{
            map.put(popNode, stack.peek());
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,4,5,1,2};
        Node node = getMaxTree(arr);
        System.out.println(node);
    }
}

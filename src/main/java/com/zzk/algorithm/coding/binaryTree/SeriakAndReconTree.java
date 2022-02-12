package com.zzk.algorithm.coding.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: coding
 * @description: 4.二叉树的序列化和反序列化
 * @author: zzk
 * @create: 2020-09-27 23:37
 */
public class SeriakAndReconTree {

    public class Node{

        public int value;

        public Node left;

        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 通过先序遍历实现序列化和反序列化
     * @param head
     * @return
     */
    public String serialByPre(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    /**
     * 反序列化过程
     * @param preStr
     * @return
     */
    public Node reconByPreString(String preStr){
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i != values.length; i++){
            queue.offer(values[i]);
        }
        return  reconPreOrder(queue);
    }

    public Node reconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    /**
     * 通过层遍历实现序列化和反序列化
     * @param head
     * @return
     */
    public String serialByLevel(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            if(head.left != null){
                res += head.left.value + "!";
                queue.offer(head.left);
            }else{
                res += "#!";
            }
            if(head.right != null){
                res += head.right.value + "!";
                queue.offer(head.right);
            }else{
                res += "#!";
            }
        }
        return res;
    }

    /**
     * 反序列化过程
     * @param levelStr
     * @return
     */
    public Node reconByLevelString(String levelStr){
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<>();
        if(head != null){
            queue.offer(head);
        }
        Node node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public Node generateNodeByString(String val){
        if(val.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(val));
    }
}

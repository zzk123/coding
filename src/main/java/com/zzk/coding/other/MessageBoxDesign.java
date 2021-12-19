package com.zzk.coding.other;

import java.util.HashMap;

/**
 * @ClassName MessageBoxDesign
 * @Description 23.一种消息接收并打印的结构设计
 * @Author zzk
 * @Date 2021/3/17 22:07
 **/
public class MessageBoxDesign {

    public static class Node{
        public int num;
        public Node next;

        public Node(int num){
            this.num = num;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", next=" + next +
                    '}';
        }
    }

    public static class MessageBox{
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int lastPrint;

        public MessageBox(){
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            lastPrint = 0;
        }

        /**
         * 时间复杂度为O(N)
         * @param num
         */
        public void receive(int num){
            if(num < 1){
                return;
            }
            Node cur = new Node(num);
            headMap.put(num, cur);
            tailMap.put(num, cur);

            System.out.println("num:" + num);
            System.out.println("之前：headMap:" + headMap);
            System.out.println("之前：tailMap:" + tailMap);

            if(tailMap.containsKey(num - 1)){
                tailMap.get(num - 1).next = cur;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            if(headMap.containsKey(num + 1)){
                cur.next = headMap.get(num + 1);
                tailMap.remove(num);
                headMap.remove(num + 1);
            }

            System.out.println("之后：headMap:" + headMap);
            System.out.println("之后：tailMap:" + tailMap);

            if(headMap.containsKey(lastPrint + 1)){
                System.out.println("开始输出：");
                print();
            }
        }

        private void print(){
            Node node = headMap.get(++lastPrint);
            headMap.remove(lastPrint);
            while(node != null){
                System.out.println(node.num + " ");
                node = node.next;
                lastPrint++;
            }
            tailMap.remove(--lastPrint);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] ints  = {2,1,4,5,7,6,3,9,8,6};
        MessageBox messageBox = new MessageBox();
        for(int i : ints) {
            messageBox.receive(i);
        }
    }
}

package com.zzk.labuladong;

import java.util.HashMap;

/**
 * @program: coding
 * @description: LRU算法描述
 * @author: zzk
 * @create: 2021-12-01 22:47
 */
public class LRUStudy {

    public class Node{
        public int key, val;
        public Node next, prev;
        public Node(int k, int v){
            this.key = k;
            this.val = v;
        }
    }

    /**
     * 双向链表实现
     */
    public class DoubleList{
        private Node head, tail;
        private int size;

        public DoubleList(){
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        /**
         * 最后插入
         * @param x
         */
        public void addLast(Node x){
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        /**
         * 删除节点
         * @param x
         */
        public void remove(Node x){
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        /**
         * 删除第一个
         * @return
         */
        public Node removeFirst(){
            if(head.next == tail){
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size(){
            return size;
        }
    }

    /**
     * LRU 实现
     * 最常使用的元素保持在最前面，最少使用的元素优先删除
     */
    public class LRUCache{

        private HashMap<Integer, Node> map;

        private DoubleList cache;

        private int cap;

        public LRUCache(int capacity){
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        /**
         * 更新key为最新的位置，先删除后插入
         * @param key
         */
        private void makeRecently(int key){
            Node x = map.get(key);
            cache.remove(x);
            cache.addLast(x);
        }

        /**
         * 新加元素到最新位置
         * @param key
         * @param val
         */
        private void addRecently(int key, int val){
            Node x = new Node(key, val);
            cache.addLast(x);
            map.put(key, x);
        }

        /**
         * 删除元素，把链表的元素删除，把哈希表的元素删除
         * @param key
         */
        private void deleteKey(int key){
            Node x = map.get(key);
            cache.remove(x);
            map.remove(key);
        }

        /**
         * 删除最久没有使用的元素
         */
        private void removeLeastRecently(){
            Node deletedNode = cache.removeFirst();
            int deletedKey = deletedNode.key;
            map.remove(deletedKey);
        }

        /**
         * 获取元素，更新最新位置
         * @param key
         * @return
         */
        public int get(int key){
            //不存在返回-1
            if(!map.containsKey(key)){
                return -1;
            }
            //更新key为最新的位置
            makeRecently(key);
            return map.get(key).val;
        }

        /**
         * 添加元素，如果达到容量，删除最旧没用的元素
         * @param key
         * @param val
         */
        public void put(int key, int val){
            //如果已经存在
            if(map.containsKey(key)){
                //先删除
                deleteKey(key);
                //更新到最新位置
                addRecently(key, val);
                return;
            }
            //如果达到容量
            if(cap == cache.size){
                //删除最久的元素
                removeLeastRecently();
            }
            //更新最新的位置
            addRecently(key, val);
        }
    }
}

package com.zzk.coding.other;

import java.util.HashMap;

/**
 * @ClassName PoolDesign
 * @Description 11.设计RandomPool结构
 * @Author zzk
 * @Date 2021/3/9 0:06
 **/
public class PoolDesign {

    public class Pool<K>{

        private HashMap<K, Integer> keyIndexMap;

        private HashMap<Integer, K> indexKeyMap;

        private int size;

        public Pool(){
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        /**
         * 将某个key加入到该结构，做到不重复插入
         * @param key
         */
        public void insert(K key){
            if(!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size++, key);
            }
        }

        /**
         * 将原本在结构中的某个key移除
         * @param key
         */
        public void delete(K key){
            if(this.keyIndexMap.containsKey(key)){
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        /**
         * 等概率获取一个index
         * @return
         */
        public K getRandom(){
            if(this.size == 0){
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size);
            return this.indexKeyMap.get(randomIndex);
        }
    }
}

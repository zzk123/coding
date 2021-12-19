package com.zzk.coding.other;

import java.security.Key;
import java.util.HashMap;

/**
 * @ClassName DesginMap
 * @Description 8.设计有setAll功能的哈希表
 * @Author zzk
 * @Date 2021/3/3 23:29
 **/
public class DesignMap {

    class MyValue<V>{

        private V value;

        private long time;

        public MyValue(V value, long time){
            this.value = value;
            this.time = time;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }

    class MyHashMap<K, V>{

        private HashMap<K, MyValue<V>> baseMap;

        private long time;

        private MyValue<V> setAll;

        public MyHashMap(){
            this.baseMap = new HashMap<>();
            this.time = 0;
            this.setAll = new MyValue<>(null, -1);
        }
        public boolean containsKey(K key){
            return this.baseMap.containsKey(key);
        }

        public void put(K key, V value){
            this.baseMap.put(key, new MyValue<>(value, this.time++));
        }

        public void setAll(V value){
            this.setAll = new MyValue<>(value, this.time++);
        }

        public V get(K Key){
            if(this.containsKey(Key)){
                if(this.baseMap.get(Key).getTime() > this.setAll.getTime()){
                    return this.baseMap.get(Key).getValue();
                }else{
                    return this.setAll.getValue();
                }
            }else{
                return null;
            }
        }
    }
}

package com.zzk.algorithm.labuladong;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @program: coding
 * @description: LFU算法
 * @author: zzk
 * @create: 2021-12-02 22:25
 */
public class LFUStudy {

    public class LFUCashe{
        //key 到 val 的映射
        HashMap<Integer, Integer> keyToVal;
        //key 到 freq 的映射
        HashMap<Integer, Integer> keyToFreq;
        //freq 到 key 列表的映射
        HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
        //记录最小的频次
        int minFreq;
        //记录 LFU缓存的最大容量
        int cap;

        public LFUCashe(int capacity){
            keyToVal = new HashMap<>();
            keyToFreq = new HashMap<>();
            freqToKeys = new HashMap<>();
            this.cap = capacity;
            this.minFreq = 0;
        }

        /**
         * 获取key值
         * @param key
         * @return
         */
        public int get(int key){
            if(!keyToVal.containsKey(key)){
                return -1;
            }
            //增加key对应的freq
            increaseFreq(key);
            return keyToVal.get(key);
        }


        /**
         * 添加值
         * @param key
         * @param val
         */
        public void put(int key, int val){
            if(this.cap <= 0){
                return;
            }
            //如果存在，则覆盖
            if(keyToVal.containsKey(key)){
                keyToVal.put(key, val);
                //频次添加
                increaseFreq(key);
                return;
            }

            //如果超过容量，删除频次最低的
            if(this.cap <= keyToVal.size()){
                removeMinFreqKey();
            }
            //插入操作
            keyToVal.put(key, val);
            keyToFreq.put(key, 1);
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            freqToKeys.get(1).add(key);
            //最新插入的key的频次为1，最小的频次就是1
            this.minFreq = 1;
        }

        /**
         * 删除频次最低的
         */
        private void removeMinFreqKey(){
            LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
            //删除第一个节点
            int deleteKey = keyList.iterator().next();
            keyList.remove(deleteKey);
            if(keyList.isEmpty()){
                freqToKeys.remove(this.minFreq);
            }
            keyToVal.remove(deleteKey);
            keyToFreq.remove(deleteKey);
        }

        /**
         * 添加频次
         * @param key
         */
        private void increaseFreq(int key){
            int freq = keyToFreq.get(key);
            //更新KF表
            keyToFreq.put(key, freq + 1);
            //更新FK表 - 先删除后添加
            freqToKeys.get(freq).remove(key);
            freqToKeys.putIfAbsent(freq+1, new LinkedHashSet<>());
            freqToKeys.get(freq + 1).add(key);
            //如果 freq对应的列表空了，删除
            if(freqToKeys.get(freq).isEmpty()){
                freqToKeys.remove(freq);
                //更新最小频率
                if(freq == this.minFreq){
                    this.minFreq++;
                }
            }
        }
    }
}

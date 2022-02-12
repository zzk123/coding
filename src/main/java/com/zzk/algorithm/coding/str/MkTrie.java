package com.zzk.algorithm.coding.str;

/**
 * @ClassName MkTire
 * @Description 23.字典树（前缀树）的实现
 * @Author zzk
 * @Date 2020/11/29 11:42
 **/
public class MkTrie {

    class TrieNode{
        public int path;
        public int end;
        public TrieNode[] map;

        public TrieNode(){
            path = 0;
            end = 0;
            map = new TrieNode[26];
        }
    }

    class Trie{
        private TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        /**
         * 添加word，可以重复添加
         * @param word
         */
        public void insert(String word){
            if(word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.map[index] == null){
                    node.map[index] = new TrieNode();
                }
                node = node.map[index];
                node.path++;
            }
            node.end++;
        }

        /**
         * 删除word，如果word添加多次，仅删除一个
         * @param word
         */
        public void delete(String word){
            if(search(word)){
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for(int i = 0; i < chs.length; i++){
                    index = chs[i] - 'a';
                    if(node.map[index].path-- == 1){
                        node.map[index] = null;
                        return;
                    }
                    node = node.map[index];
                }
                node.end--;
            }
        }

        /**
         * 查询word是否在字典树中
         * @param word
         * @return
         */
        public boolean search(String word){
            if(word == null){
                return false;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.map[index] == null){
                    return false;
                }
                node = node.map[index];
            }
            return node.end != 0;
        }

        /**
         * 返回以字符串pre为前缀的单词数量
         * @param pre
         * @return
         */
        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.map[index] == null){
                    return 0;
                }
                node = node.map[index];
            }
            return node.path;
        }
    }
}

package com.zzk.coding.binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: coding
 * @description:
 * @author: zzk
 * @create: 2020-10-07 22:02
 */
public class DisjointSet {

    /**
     * 节点定义
     */
    public class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 查询对象定义
     */
    public class Query{
        public Node o1;
        public Node o2;

        public Query(Node o1, Node o2){
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    public class DisjointSets{
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> rankMap;

        public DisjointSets(){
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
        }

        public void makeSets(Node head){
            fatherMap.clear();
            rankMap.clear();

        }

        private void preOrderMake(Node head){
            if(head == null){
                return;
            }
            fatherMap.put(head, head);
            rankMap.put(head, 0);
            preOrderMake(head.left);
            preOrderMake(head.right);
        }

        public Node findFather(Node n){
            Node father = fatherMap.get(n);
            if(father != n){
                father = findFather(father);
            }
            fatherMap.put(n, father);
            return father;
        }

        public void union(Node a, Node b){
            if(a == null || b == null){
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if(aFather != bFather){
                int aFrank = rankMap.get(aFather);
                int bFrank = rankMap.get(bFather);
                if(aFrank < bFrank){
                    fatherMap.put(aFather, bFather);
                }else if(aFrank > bFrank){
                    fatherMap.put(bFather, aFather);
                }else{
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aFrank + 1);
                }
            }
        }
    }


    /**
     * 主方法
     * @param head
     * @param queries
     * @return
     */
    public Node[] tarJanQuery(Node head, Query[] queries){
        Node[] ans = new Tarjan().query(head, queries);
        return ans;
    }

    /**
     * Tarjan 类实现处理流程
     */
    public class Tarjan{
        private HashMap<Node, LinkedList<Node>> queryMap;
        private HashMap<Node, LinkedList<Integer>> indexMap;
        private HashMap<Node, Node> ancestorMap;
        private DisjointSets sets;

        public Tarjan(){
            queryMap = new HashMap<>();
            indexMap = new HashMap<>();
            ancestorMap = new HashMap<>();
            sets = new DisjointSets();
        }

        public Node[] query(Node head, Query[] ques){
            Node[] ans = new Node[ques.length];
            setQueries(ques, ans);
            sets.makeSets(head);
            setAnswers(head, ans);
            return ans;
        }

        private void setQueries(Query[] ques, Node[] ans){
            Node o1 = null;
            Node o2 = null;
            for(int i = 0; i != ans.length; i++){
                o1 = ques[i].o1;
                o2 = ques[i].o2;
                if(o1 == o2 || o1 == null || o2 == null){
                    ans[i] = o1 != null ? o1 : o2;
                }else{
                    if(!queryMap.containsKey(o1)){
                        queryMap.put(o1, new LinkedList<>());
                        indexMap.put(o1, new LinkedList<>());
                    }
                    if(!queryMap.containsKey(o2)){
                        queryMap.put(o2, new LinkedList<>());
                        indexMap.put(o2, new LinkedList<>());
                    }
                    queryMap.get(o1).add(o2);
                    indexMap.get(o1).add(i);
                    queryMap.get(o2).add(o1);
                    indexMap.get(o2).add(i);
                }
            }
        }

        private void setAnswers(Node head, Node[] ans){
            if(head == null){
                return;
            }
            setAnswers(head.left, ans);
            sets.union(head.left, head);
            ancestorMap.put(sets.findFather(head), head);
            setAnswers(head.right, ans);
            sets.union(head.right, head);
            ancestorMap.put(sets.findFather(head), head);
            LinkedList<Node> nList = queryMap.get(head);
            LinkedList<Integer> iList = indexMap.get(head);
            Node node = null;
            Node nodeFather = null;
            int index = 0;
            while(nList != null && !nList.isEmpty()){
                node = nList.poll();
                index = iList.poll();
                nodeFather = sets.findFather(node);
                if(ancestorMap.containsKey(nodeFather)){
                    ans[index] = ancestorMap.get(nodeFather);
                }
            }
        }
    }
}

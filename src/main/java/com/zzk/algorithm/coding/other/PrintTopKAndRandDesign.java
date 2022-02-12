package com.zzk.algorithm.coding.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PrintTopKAndRandDesign
 * @Description 29.出现次数的TOP K问题
 * @Author zzk
 * @Date 2021/3/23 22:16
 **/
public class PrintTopKAndRandDesign {

    public class Node{

        public String str;

        public int times;

        public Node(String s, int t){
            str = s;
            times = t;
        }
    }

    public void printTopKAndRank(String[] arr, int topK){
        if(arr == null || topK < 1){
            return;
        }
        HashMap<String, Integer> map = new HashMap<>();
        //生成哈希表（字符串词频）
        for(int i = 0; i != arr.length; i++){
            String cur = arr[i];
            if(!map.containsKey(cur)){
                map.put(cur, 1);
            }else{
                map.put(cur, map.get(cur) + 1);
            }
        }
        Node[] heap = new Node[topK];
        int index = 0;
        //遍历哈希表，决定每条消息是否进堆
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String str = entry.getKey();
            int times = entry.getValue();
            Node node = new Node(str, times);
            if(index != topK){
                heap[index] = node;
                heapInsert(heap, index++);
            }else{
                if(heap[0].times < node.times){
                    heap[0] = node;
                    heapify(heap, 0, topK);
                }
            }
        }
        //把小根堆的所有元素按词频从大到小排序
        for(int i = index - 1; i != 0; i--){
            swap(heap, 0, i);
            heapify(heap, 0, i);
        }
        //严格按照排名打印K条记录
        for(int i = 0; i != heap.length; i++){
            if(heap[i] == null){
                break;
            }else{
                System.out.print("No." + (i+1) + ":");
                System.out.print(heap[i].str + ",  times:");
                System.out.println(heap[i].times);
            }
        }
    }

    private void heapInsert(Node[] heap, int index) {
        while(index != 0){
            int parent = (index - 1) / 2;
            if(heap[index].times < heap[parent].times){
                swap(heap, parent, index);
                index = parent;
            }else{
                break;
            }
        }
    }

    private void heapify(Node[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int smallest = index;
        while(left < heapSize){
            if(heap[left].times < heap[index].times){
                smallest = left;
            }
            if(right < heapSize && heap[right].times < heap[smallest].times){
                smallest = right;
            }
            if(smallest != index){
                swap(heap, smallest, index);
            }else{
                break;
            }
            index = smallest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    private void swap(Node[] heap, int index1, int index2) {
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }


    public class TopKRecord{
        private Node[] heap;
        private int index;
        private HashMap<String, Node> strNodeMap;
        private HashMap<Node, Integer> nodeIndexMap;

        public TopKRecord(int size){
            heap = new Node[size];
            index = 0;
            strNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }

        public void add(String str){
            Node curNode = null;
            int preIndex = -1;
            if(!strNodeMap.containsKey(str)){
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            }else{
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }
            if(preIndex == -1){
                if(index == heap.length){
                    if(heap[0].times < curNode.times){
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, index);
                    }else{
                        nodeIndexMap.put(curNode, index);
                        heap[index] = curNode;
                        heapInsert(index++);
                    }
                }else{
                    heapify(preIndex, index);
                }
            }
        }

        public void printTopK(){
            System.out.println("Top:");
            for(int i = 0; i != heap.length; i++){
                if(heap[i] == null){
                    break;
                }
                System.out.print("str:" + heap[i].str);
                System.out.println(" Times:" + heap[i].times);
            }
        }

        private void heapInsert(int index){
            while(index != 0){
                int parent = (index - 1)/2;
                if(heap[index].times < heap[parent].times){
                    swap(parent, index);
                    index = parent;
                }else{
                    break;
                }
            }
        }

        private void heapify(int i, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while(l < heapSize){
                if(heap[l].times < heap[index].times){
                    smallest = l;
                }
                if(r < heapSize && heap[r].times < heap[smallest].times){
                    smallest = r;
                }
                if(smallest != index){
                    swap(smallest, index);
                }else{
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }

    }

}

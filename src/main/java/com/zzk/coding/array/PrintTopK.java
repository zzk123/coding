package com.zzk.coding.array;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * @ClassName PrintTopK
 * @Description TODO
 * @Author zzk
 * @Date 2021/2/4 21:25
 **/
public class PrintTopK {

    public class HeapNode{
        public int value;
        public int arrNum;
        public int index;

        public HeapNode(int value, int arrNum, int index){
            this.value = value;
            this.arrNum = arrNum;
            this.index = index;
        }
    }

    public void printTopK(int[][] martix, int topK){
        int heapSize = martix.length;
        HeapNode[] heap = new HeapNode[heapSize];
        for(int i = 0; i != heapSize; i++){
            int index = martix[i].length - 1;
            heap[i] = new HeapNode(martix[i][index], i, index);
            heapInsert(heap, i);
        }
        System.out.println("Top " + topK + ":");
        for(int i = 0; i != topK; i++){
            if(heapSize  == 0){
                break;
            }
            System.out.print(heap[0].value + " ");
            if(heap[0].index != 0){
                heap[0].value = martix[heap[0].arrNum][--heap[0].index];
            }else{
                swap(heap, 0, --heapSize);
            }
            heapify(heap, 0, heapSize);
        }
    }

    public void heapInsert(HeapNode[] heap, int index){
        while(index != 0){
            int parent = (index - 1) / 2;
            if(heap[parent].value < heap[index].value){
                swap(heap, parent, index);
                index = parent;
            }else{
                break;
            }
        }
    }

    public void heapify(HeapNode[] heap, int index, int heapSize){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while(left < heapSize){
            if(heap[left].value > heap[index].value){
                largest = left;
            }
            if(right <heapSize && heap[right].value > heap[largest].value){
                largest = right;
            }
            if(largest != index){
                swap(heap, largest, index);
            }else{
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    public void swap(HeapNode[] heap, int index1,  int index2){
        HeapNode tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }
}

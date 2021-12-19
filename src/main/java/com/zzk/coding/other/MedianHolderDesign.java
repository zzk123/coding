package com.zzk.coding.other;

import java.util.Comparator;

/**
 * @ClassName MedianHolderDesign
 * @Description 25.随时找到数据流的中位数
 * @Author zzk
 * @Date 2021/3/19 0:30
 **/
public class MedianHolderDesign {

    public class MedianHolder{

        private HeapDesign.MyHeap<Integer> minHeap;
        private HeapDesign.MyHeap<Integer> maxHeap;

        public MedianHolder(){
            HeapDesign heapDesign = new HeapDesign();
            this.maxHeap = heapDesign.new MyHeap<>(new MaxHeapComparator());
            this.minHeap = heapDesign.new MyHeap<>(new MinHeapComparator());
        }

        public void addNumber(Integer num){
            if(this.maxHeap.isEmpty()){
                this.maxHeap.add(num);
                return;
            }
            if(this.maxHeap.getHead() >= num){
                this.maxHeap.add(num);
            }else{
                if(this.minHeap.isEmpty()){
                    this.minHeap.add(num);
                    return;
                }
                if(this.minHeap.getHead() > num){
                    this.maxHeap.add(num);
                }else{
                    this.minHeap.add(num);
                }
            }
            this.modifyTwoHeapsSize();
        }

        public Integer getMedian(){
            long maxHeapSize = this.maxHeap.getSize();
            long minHeapSize = this.minHeap.getSize();
            if(maxHeapSize + minHeapSize == 0){
                return null;
            }
            Integer maxHeapHead = this.maxHeap.getHead();
            Integer minHeapHead = this.minHeap.getHead();
            if(((maxHeapSize + minHeapHead) & 1) == 0){
                return (maxHeapHead + minHeapHead) / 2;
            }else if(maxHeapHead > minHeapHead){
                return maxHeapHead;
            }else{
                return minHeapHead;
            }
        }

        private void modifyTwoHeapsSize() {
            if(this.maxHeap.getSize() == this.minHeap.getSize() + 2){
                this.minHeap.add(this.maxHeap.popHead());
            }
            if(this.minHeap.getSize() == this.maxHeap.getSize() + 2){
                this.maxHeap.add(this.minHeap.popHead());
            }
        }
    }


    public class MaxHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o2 > o1){
                return 1;
            }else {
                return -1;
            }
        }
    }

    public class MinHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o2 < o1){
                return 1;
            }else{
                return -1;
            }
        }
    }
}

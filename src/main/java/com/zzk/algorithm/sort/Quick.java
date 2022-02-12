package com.zzk.algorithm.sort;


/**
 * 快速排序
 * @author Administrator
 *
 */
public class Quick extends BaseSort{
	/**
	 * 快速排序算法
	 * @param a
	 */
	public static void sort(Comparable[] a){
		sort(a,0,a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi<=lo) {
			return;
		}
		int j = partition(a, lo, hi);   	 //切分
		sort(a,lo,j-1);					 //将左半部分切分
		sort(a,j+1,hi);					 //将右半部分切分
	}
	
	/**
	 * 快速排序的切分
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j = hi+1;
		//切分元素选取
		Comparable v = a[lo];
		while(true){
			//扫描左右，检查扫描是否结束并交换元素
			//查找小于v的值
			while(less(a[++i],v)) {
				//防止越界
				if (i == hi) {
					break;
				}
			}
			//查找大于v的值
			while(less(v,a[--j])) {
				//防止越界
				if (j == lo) {
					break;
				}
			}
			//防止越界
			if(i>=j) {
				break;
			}
			//进行交换
			exch(a,i,j);
		}
		//切分元素跟j交换，也就是换到中间去
		exch(a, lo, j);
		System.out.printf("lo:%s, hi:%d, j:%s", lo, hi, j);
		System.out.println();
		show(a);
		return j;
	}

	public static void main(String[] args) {
		Comparable[] arr = {9,8,7,6,5,4,2,1,0};
		show(arr);
		sort(arr);
		show(arr);
	}
}

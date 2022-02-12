package com.zzk.algorithm.sort;


/**
 * 作为父类，建好相关函数
 * @author Administrator
 *
 */
public class BaseSort {
	/**
	 * 比较大小
	 * 注：v.compareTo(w) v<w(-1) v=w(0) v>w(1)
	 * 	只有当v<w，返回true
	 * 	当v=w或v>w，返回false
	 * @param v
	 * @param w
	 * @return
	 */
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
	}
	/**
	 * 调换位置
	 * @param a
	 * @param i
	 * @aram j
	 */
	public static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	/**
	 * 打印数组
	 * @param a
	 */
	public static void show(Comparable[] a){
		//单行中打印数组
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 打印数组
	 * @param a
	 */
	public static void show(int[] a){
		//单行中打印数组
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 判断是否有序
	 * @param a
	 * @return
	 */
	public static boolean isSorted(Comparable[] a){
		//测试数组元素是否有序
		for(int i=1; i<a.length; i++){
			if(less(a[i], a[i-1]))
				return false;
		}
		return true;
	}
}

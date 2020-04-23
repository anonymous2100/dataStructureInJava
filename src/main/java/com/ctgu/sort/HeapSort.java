package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: HeapSort
 * @Description: 堆排序
 * @author lh2
 * @date 2020年4月23日 下午4:03:24
 */
public class HeapSort
{
	// 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。
	// 堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
	// 堆排序可以说是一种利用堆的概念来排序的选择排序。分为两种方法：
	// 大顶堆：每个节点的值都大于或等于其子节点的值，在堆排序算法中用于升序排列；
	// 小顶堆：每个节点的值都小于或等于其子节点的值，在堆排序算法中用于降序排列；
	// 堆排序的平均时间复杂度为 Ο(nlogn)。
	// 算法步骤
	// 1、将待排序序列构建成一个堆 H[0……n-1]，根据（升序降序需求）选择大顶堆或小顶堆；
	// 2、把堆首（最大值）和堆尾互换；
	// 3、把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
	// 4、重复步骤 2，直到堆的尺寸为 1。
	public static void heapSort(int[] data)
	{

	}

	/**
	 * @param n
	 *            生成n个元素的随机数组
	 * @param rangeMin
	 *            随机范围最小值
	 * @param rangeMax
	 *            随机范围最大值
	 * @return 返回一个随机 int 型数组
	 */
	public static int[] getRandomArray(int n, int rangeMin, int rangeMax)
	{
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
		{
			arr[i] = (int) (Math.random() * (rangeMax - rangeMin + 1)) + rangeMin;
		}
		return arr;
	}

	public static void main(String[] args)
	{
		int[] data = getRandomArray(16, 0, 99);
		// int[] data = new int[] { 14, 22, 64, 37, 89, 24, 51, 0, 19, 33, 62 };
		// int[] data = new int[] { 56, 12, 80, 91, 20 };
		// int[] data = new int[] { 5, 7, 8, 3, 1, 2, 4, 6 };
		System.out.println("排序前：a=" + Arrays.toString(data));
		heapSort(data);
		System.out.println("排序后：a=" + Arrays.toString(data));
	}

}

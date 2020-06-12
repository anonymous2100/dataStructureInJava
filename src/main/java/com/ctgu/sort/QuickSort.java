package com.ctgu.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 
 * @author lh2
 * @date 2018年10月19日 下午6:28:40
 * @desc
 */
public class QuickSort
{
	// 算法步骤
	// 1、从数列中挑出一个元素，称为 “基准”（pivot）;
	// 2、重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
	// 3、递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
	// 递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了。
	// 虽然一直递归下去，但是这个算法总会退出，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
	public static void quickSort(int[] a, int left, int right)
	{
		// 递归终止条件
		if (left >= right)
		{
			return;
		}

		// 获取基准pivot（区分点）
		int partitionIndex = partition(a, left, right);

		// 基准两边开始递归
		quickSort(a, left, partitionIndex - 1);
		quickSort(a, partitionIndex + 1, right);
	}

	// 分区（partition）操作
	// 首先看一下快速排序的递推公式，我们不断的将大区间分割成小区间，然后对小区间再次进行分割。
	// quickSort(p...q)=quickSort(p...pivot-1)+quickSort(pivot+1...q)
	// 我们可以总结出以上的递推公式。
	// 因为我们不断的将大区间分成小区间，然后一直分下去，不对，一直分总有一个尽头的，所以这也是递归的终止条件。当满足这个条件时，就不再继续往下进行递归，那么快速排序的递归条件是什么呢？上边也说到了，当区间只剩一个数据的时候，我们不再进行划分，所以递归条件为：
	// p >= q
	private static int partition(int[] a, int left, int right)
	{
		int pivot = left; // 设定基准值（pivot）
		int index = pivot + 1; // 大小值交换指针
		// 遍历数据，根据基准pivot进行划分
		for (int i = index; i <= right; i++)
		{
			if (a[i] < a[pivot])
			{
				swap(a, i, index);
				index++;
			}
		}
		// 将pivot点元素放入基准位置
		swap(a, pivot, index - 1);

		// 返回已经排好的基准// return pivot;
		return index - 1;
	}

	// 交换a[m]和a[n]
	private static void swap(int[] a, int m, int n)
	{
		int temp = a[m];
		a[m] = a[n];
		a[n] = temp;
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
		System.out.println("排序前：a=" + Arrays.toString(data));
		quickSort(data, 0, data.length - 1);
		System.out.println("排序后：a=" + Arrays.toString(data));
	}
}

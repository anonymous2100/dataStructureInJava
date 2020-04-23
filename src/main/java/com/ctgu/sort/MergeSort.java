package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: MergeSort
 * @Description: 归并排序
 * @author lh2
 * @date 2020年4月23日 下午4:04:02
 */
public class MergeSort
{

	// 归并排序（Merge sort）是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
	// 作为一种典型的分而治之思想的算法应用，归并排序的实现由两种方法：
	// （1）自上而下的递归（所有递归的方法都可以用迭代重写，所以就有了第 2 种方法）；
	// （2）自下而上的迭代；
	// 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是 O(nlogn) 的时间复杂度。代价是需要额外的内存空间。
	//
	// 算法步骤
	// 1、申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
	// 2、设定两个指针，最初位置分别为两个已经排序序列的起始位置；
	// 3、比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
	// 4、重复步骤 3 直到某一指针达到序列尾；
	// 5、将另一序列剩下的所有元素直接复制到合并序列尾。
	public static int[] mergeSort(int[] a)
	{
		// 递归结束条件：数组长度为1
		if (a.length <= 1)
		{
			return a;
		}
		int middle = (int) Math.floor(a.length / 2);

		int[] left = Arrays.copyOfRange(a, 0, middle);
		int[] right = Arrays.copyOfRange(a, middle, a.length);

		return merge(mergeSort(left), mergeSort(right));
	}

	private static int[] merge(int[] left, int[] right)
	{
		int[] result = new int[left.length + right.length];
		int i = 0;
		while (left.length > 0 && right.length > 0)
		{
			if (left[0] <= right[0])
			{
				result[i++] = left[0];
				left = Arrays.copyOfRange(left, 1, left.length);
			}
			else
			{
				result[i++] = right[0];
				right = Arrays.copyOfRange(right, 1, right.length);
			}
		}

		while (left.length > 0)
		{
			result[i++] = left[0];
			left = Arrays.copyOfRange(left, 1, left.length);
		}

		while (right.length > 0)
		{
			result[i++] = right[0];
			right = Arrays.copyOfRange(right, 1, right.length);
		}
		return result;
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
		// int[] data = getRandomArray(16, 0, 99);
		int[] data = new int[] { 14, 22, 64, 37, 89, 24, 51, 0, 19, 33, 62 };
		System.out.println("排序前：a=" + Arrays.toString(data));
		int[] result = mergeSort(data);
		System.out.println("排序后：a=" + Arrays.toString(result));
	}

}

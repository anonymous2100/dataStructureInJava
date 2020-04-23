package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: BaseSort
 * @Description: 基数排序
 * @author lh2
 * @date 2020年4月23日 下午4:04:20
 */
public class RadixSort
{
	// 基数排序
	// 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
	// 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
	public static void radixSort(int[] a)
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
		// int[] data = getRandomArray(16, 0, 99);
		int[] data = new int[] { 14, 22, 64, 37, 89, 24, 51, 0, 19, 33, 62 };
		System.out.println("排序前：a=" + Arrays.toString(data));
		radixSort(data);
		System.out.println("排序后：a=" + Arrays.toString(data));
	}

}

package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: ShellSort
 * @Description: 希尔排序
 * @author lh2
 * @date 2020年4月23日 下午4:02:11
 */
public class ShellSort
{
	/*
	 * 希尔排序
	 */
	public static void shellSort(int[] a)
	{
		int n = a.length;
		// 进行分组，最开始时的增量gap为数组长度的一半，然后每次减半一直到为1
		for (int gap = n / 2; gap > 0; gap = gap / 2)
		{
			System.out.println("gap=" + gap + "，分组之前，还未对每个组进行排序时，a=" + Arrays.toString(a));

			// 对各个分组进行插入排序
			for (int i = gap; i < n; i++)
			{
				// 将a[i]插入到所在分组的正确位置上
				insertI(a, gap, i);
			}

			System.out.println("gap=" + gap + "，分组之后，已经对每个组排好序了，a=" + Arrays.toString(a) + "\n");
		}
	}

	// 对各个组进行插入时，并不是先对一个组进行排序完再对另一个组进行排序
	// 而是轮流对每个组进行插入排序
	// 将a[i]插入到所在分组的正确位置上
	// a[i]所在分组为：
	// a[i-2*gap] a[i-gap] a[i] a[i+gap] a[i+2*gap]
	private static void insertI(int[] a, int gap, int i)
	{
		int inserted = a[i]; // 待排元素
		int j = 0;

		System.out.println("i=" + i + ",gap=" + gap + ",j=" + j + "，对每个组进行排序之前，a=" + Arrays.toString(a));

		// 同一步长内排序方式是插入排序
		// 插入的时候按组进行插入（组内元素两两相隔gap）
		for (j = i - gap; j >= 0 && inserted < a[j]; j = j - gap)
		{
			a[j + gap] = a[j]; // 原有序数组最大的后移一位
		}
		a[j + gap] = inserted;// 找到了合适的位置插入

		// 判断每个组排序时是否交换了元素
		if (a[i] != a[j + gap])
		{
			System.out.println("i=" + i + ",gap=" + gap + ",j=" + j + "，对每个组进行排序之后，a="  //
					+ Arrays.toString(a) + ",交换的元素为：" + a[i] + "和" + a[j + gap] + "\n");
		}
		else
		{
			System.out.println("i=" + i + ",gap=" + gap + ",j=" + j + "，对每个组进行排序之后，a="  //
					+ Arrays.toString(a) + "\n");
		}
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
		// int[] data = new int[] { 5, 7, 8, 3, 1, 2, 4, 6 };
		System.out.println("排序前：a=" + Arrays.toString(data));
		shellSort(data);
		System.out.println("排序后：a=" + Arrays.toString(data));
	}

}

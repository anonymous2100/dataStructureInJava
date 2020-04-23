package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: InsertSort
 * @Description: 插入排序：
 * @author lh2
 * @date 2020年4月23日 下午1:45:46
 */
public class InsertSort
{
	/**
	 * 插入排序，升序
	 * 
	 * @param data
	 *            待排序数组
	 */
	public static void insertSort(int[] a)
	{
		if (a == null || a.length == 0)
		{
			System.out.println("数组为空！");
			return;
		}
		int n = a.length;
		int temp;
		int i, j;
		for (i = 1; i < n; i++)
		{
			// 临时保存无序序列中的第一个元素，防止被覆盖
			temp = a[i];

			// 从后往前遍历有序序列，找出比temp大的元素，
			for (j = i - 1; j >= 0; j--)
			{
				// 如果有序序列尾部元素a比temp大，则把a往后移动一位
				// 再用temp来填补a的位置
				if (a[j] > temp)
				{
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		int[] data = new int[] { 14, 22, 64, 37, 89, 24, 51, 0, 19, 33, 62 };

		System.out.println("排序前：" + Arrays.toString(data));
		insertSort(data);
		System.out.println("\n排序后：" + Arrays.toString(data));

	}
}

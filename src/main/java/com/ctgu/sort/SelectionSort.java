package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: SelectionSort
 * @Description:选择排序：<br>
 *                        一、介绍<br>
 *                        1、选择排序法是将序列分为两段，有序前列和无序后列，<br>
 *                        每次查找无序后列中最小元素，将其插入到有序前列的最末尾处，<br>
 *                        直至无序后列最后一个元素，最终排序后的序列为升序序列<br>
 *                        2、适用于包括数组和向量在内的序列<br>
 *                        3、选择排序与冒泡排序的区别是选择排序每次遍历时会记住最小元素的位置，只进行一次交换， <br>
 *                        而冒泡排序每次遍历时会交换两个顺序不合法的元素 <br>
 *                        二、思想<br>
 *                        1、将序列分为两段，有序前列[0,r)和无序后列[r,n-1] <br>
 *                        2、在无序后列中查找最小元素s=a[min]，记住其所在位置 <br>
 *                        3、将无序后列中的最小元素与有序前列的末位元素进行交换<br>
 *                        4、循环停止标识：无序后列只剩余最后一个元素<br>
 * @author lh2
 * @date 2020年4月23日 下午1:45:30
 */
public class SelectionSort
{
	/*
	 * 选择排序，升序排序
	 */
	public static void selectionSort(int[] a)
	{
		int len = a.length;
		// 循环有序前列
		for (int i = 0; i < len; i++)
		{
			// 定义一个变量用于记录无序后列中的最小元素下标
			int min = i;
			// 循环无序后列，查找最小元素所在部分
			for (int j = i + 1; j < len; j++)
			{
				if (a[j] < a[min])
				{
					min = j;
				}
				System.out.println("i=" + i + ",j=" + j + ",min=" + min + ",a=" + Arrays.toString(a));
			}
			// 交换无序序列中的首元素和最小元素位置
			int temp = a[min];
			a[min] = a[i];
			a[i] = temp;
			System.out.println("外层循环 i=" + i + ",min=" + min + ",a=" + Arrays.toString(a) + "\n");
		}

	}

	public static void main(String[] args)
	{
		int[] data = new int[] { 14, 22, 64, 37, 89, 24, 51, 0, 19, 33, 62 };
		// int[] data = new int[] { 56, 12, 80, 91, 20 };
		System.out.println("排序前：" + Arrays.toString(data));
		selectionSort(data);
		System.out.println("\n排序后：" + Arrays.toString(data));
	}
}

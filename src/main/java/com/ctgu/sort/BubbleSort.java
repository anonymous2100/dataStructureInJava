package com.ctgu.sort;

/**
 * 冒泡排序算法
 */
public class BubbleSort
{

	public static void main(String[] args)
	{
		int[] a = { 7, 0, 5, 9, 3 };

		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a.length - i - 1; j++)
			{
				if (a[j] > a[j + 1])
				{
					int temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
		}

		for (int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + "  ");
		}

	}
}

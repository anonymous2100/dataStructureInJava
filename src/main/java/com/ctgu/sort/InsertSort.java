package com.ctgu.sort;

/**
 * 插入排序
 */
public class InsertSort
{
	/**
	 * 插入排序，升序
	 * 
	 * @param data
	 *            待排序数组
	 */
	public void insertSort(int[] a, boolean isAscending)
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
				if (isAscending)
				{
					// 如果有序序列尾部元素a比temp大，则把a往后移动一位
					// 再用temp来填补a的位置
					if (a[j] > temp)
					{
						a[j + 1] = a[j];
						a[j] = temp;
					}
				}
				else
				{
					// 如果有序序列尾部元素a比temp小，则把a往后移动一位
					// 再用temp来填补a的位置
					if (a[j] < temp)
					{
						a[j + 1] = a[j];
						a[j] = temp;
					}
				}
			}
		}
	}

	public void printAll(int[] data)
	{
		for (int j = 0; j < data.length; j++)
		{
			System.out.print(data[j] + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		int[] data = new int[] { 14, 22, 64, 37, 89, 24, 51, 0, 19, 33, 62 };
		// int[] data = { 6, 17, 26, 18, 19, 39, 1, 14, 3, 40 };
		// int[] data = { 38, 65, 97, 76, 13, 27, 49 };

		InsertSort is = new InsertSort();
		is.insertSort(data, true);

		is.printAll(data);
	}
}

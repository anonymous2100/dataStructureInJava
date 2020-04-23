package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: BubbleSort
 * @Description: 冒泡排序：相邻的两数两两比较，若不满足条件则替换位置；依此类推，每一趟都有一个较大的数排到末尾;<br>
 *               类似于气泡上升一样，故称之为冒泡排序，步骤如下：<br>
 *               1、比较相邻的元素。如果第一个比第二个大（小），就交换他们两个。<br>
 *               2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大（小）的数。<br>
 *               3、针对所有的元素重复以上的步骤，除了最后已经选出的元素（有序）。<br>
 *               4、持续每次对越来越少的元素（无序元素）重复上面的步骤，直到没有任何一对数字需要比较，则序列最终有序。<br>
 * @author lh2
 * @date 2020年4月23日 下午12:57:44
 */
public class BubbleSort
{
	public static void bubbleSort(int[] a)
	{
		// 外层循环控制趟数，总趟数为len-1
		for (int i = 0; i < a.length; i++)
		{
			// 内层循环为当前i趟数 所需要比较的次数
			for (int j = 0; j < a.length - i - 1; j++)
			{
				if (a[j] > a[j + 1]) // 相邻元素两两对比
				{
					// 元素交换
					int temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		int[] a = { 7, 0, 9, 3, 12, 65, 41, 78, 66, 99, 27 };
		System.out.println("排序前：" + Arrays.toString(a));
		bubbleSort(a);
		System.out.println("\n排序后：" + Arrays.toString(a));

	}
}

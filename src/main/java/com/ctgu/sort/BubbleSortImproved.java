package com.ctgu.sort;

import java.util.Arrays;

/**
 * @ClassName: BubbleSortImproved
 * @Description:改进的冒泡排序算法
 * @author lh2
 * @date 2020年4月23日 下午1:03:09
 */
public class BubbleSortImproved
{

	// 1. 减少外层循环次数的优化
	// 如有数组[8,7,6,1,2,3,4,5]，当我们将8、7、6三个数都排在后面后，其实就不需要继续循环比较了。
	// 那我们如何知道的面不再需要比较了呢？答案就是如果该次循环没有发生一次数的交换，就说明数组已经排好序了，
	// 那么后面的循环比较就可以停止了。
	public static void bubbleSort2(int[] a)
	{
		// flag: 在一次循环中是否发生过数据交换。true：表示发生过交换，false：表示未发生过交换
		boolean flag = true;
		for (int i = 0; i < a.length; i++)
		{
			System.out.println("\n第" + (i + 1) + "趟");
			// 如果未发生交换，则break
			if (!flag)
			{
				break;
			}

			flag = false; // 每次循环都先置为false，即认为不会发生交换
			for (int j = 0; j < a.length - i - 1; j++)
			{
				System.out.println("第" + (j + 1) + "次");
				if (a[j] > a[j + 1])
				{
					int temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;

					flag = true;// 发生了交换
				}
				System.out.println(Arrays.toString(a));
			}
			// 当内循环遍历之后，没有进行过任何一次数据交换，那么就认为当前排序已经结束了。
			// 如果上面没有执行直接退出
			if (flag)
			{
				break;
			}
		}
		System.out.println("冒泡排序后结果为：" + Arrays.toString(a));
	}

	public static void main(String[] args)
	{
		int[] a = { 7, 0, 9, 3, 12, 65, 41, 78, 66, 99, 27 };
		bubbleSort2(a);
	}

}

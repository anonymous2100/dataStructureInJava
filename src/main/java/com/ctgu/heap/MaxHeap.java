package com.ctgu.heap;

// 利用数组实现最大堆
public class MaxHeap
{
	private int[] a;      // 存储元素
	private int size;     // 当前元素的个数

	public MaxHeap(int initCapacity)
	{
		a = new int[initCapacity + 1];   // 下标为0的放"哨兵"，从1开始放堆元素
		a[0] = Integer.MAX_VALUE;   // 建立哨兵
		size = 0;
	}

	// 判空
	public boolean isEmpty()
	{
		return size == 0;
	}

	// 判满
	public boolean isFull()
	{
		return size == a.length - 1;
	}

	// 插入节点
	public boolean insert(int data)
	{
		// 如果数组满 了，则无法插入
		if (isFull())
		{
			System.out.println("已满，无法插入");
			return false;
		}
		a[++size] = data;
		for (int i = size; a[i] > a[i / 2]; i /= 2)
		{
			int t = a[i];
			a[i] = a[i / 2];  // 父节点下移到子节点的位置
			a[i / 2] = t;
		}
		return true;
	}

	// 删除一个节点，并返回最大值
	public int deleteMax()
	{
		// 如果为空
		if (isEmpty())
		{
			System.out.println("空，无元素可删除");
			return -1;
		}
		// 取出最大值
		int maxValue = a[1];
		int tmp = a[size--];
		// 将最后一个节点放在根节点位置,再和左右子树比较
		int parent = 1, child = 2;
		for (; parent * 2 <= size; parent = child)
		{
			child = parent * 2;  // 左孩子
			// 判断是否有右孩子，并且右孩子是否比左孩子大
			if (child < size && a[child + 1] > a[child])
			{
				child++;  // child指向左右孩子的较大者
			}
			// 如果根节点比左右孩子都要大，则停止比较
			if (tmp > a[child])
			{
				break;
			}
			else
			{
				int t = a[parent];
				a[parent] = a[child];
				a[child] = t;
			}
		}
		// 把最后一个节点放在parent位置
		a[parent] = tmp;
		return maxValue;
	}

	// 打印堆元素
	public void print()
	{
		if (isEmpty())
		{
			System.out.println("无元素");
			return;
		}
		for (int i = 1; i <= size; i++)
		{
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		int[] b = { 10, 8, 4, 5, 9, 15, 11, 20 };
		MaxHeap heap = new MaxHeap(b.length);
		for (int i = 0; i < b.length; i++)
		{
			heap.insert(b[i]);
		}
		System.out.println("插入后形成最大堆：");
		heap.print();
		// 下面测试删除后是否还是最大堆
		for (int i = 0; i < 2; i++)
		{
			System.out.println("删除元素：" + heap.deleteMax());
		}
		System.out.println("删除后是否还是最大堆：");
		heap.print();
	}
}
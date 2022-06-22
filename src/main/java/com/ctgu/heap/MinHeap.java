package com.ctgu.heap;

public class MinHeap
{
	private int[] data;    // 利用数组和完全二叉树的性质，存储数据
	private int size;      // 当前大小
	private int maxSize;   // 最大容量

	public MinHeap()
	{
		// 默认大小
		this(16);
	}

	public MinHeap(int maxSize)
	{
		// 下标为0的位置，不存储实际值，只存储哨兵
		data = new int[maxSize + 1];
		data[0] = Integer.MIN_VALUE;   // 哨兵，最小值
		this.size = 0;
		this.maxSize = maxSize;
	}

	// 通过完全输入一个完全二叉树的数组来构建最小堆
	public MinHeap(int[] initData)
	{
		if (initData == null || initData.length < 0)
		{
			System.out.println("初始化数组参数不正确");
			return;
		}
		data = new int[initData.length + 1];
		data[0] = Integer.MIN_VALUE;
		for (int i = 0; i < initData.length; i++)
		{
			data[i + 1] = initData[i];
		}
		this.maxSize = initData.length;
		this.size = initData.length;
		// 构建最小堆
		buildMinHeap();
	}

	// 构建最小堆
	private void buildMinHeap()
	{
		if (size == 1)
		{
			// 只有一个节点
			return;
		}
		// 从最后一个非叶子节点开始，逐渐向上调整，直到根结点
		for (int parent = size / 2; parent > 0; parent--)
		{
			fixMinHead(parent);
		}
	}

	// 将以root为根的树，调整为最小堆
	private void fixMinHead(int root)
	{
		int index = root;
		int tmp = data[root];
		// 向下和孩子节点比较，找到tmp最终存放的位置
		for (int child = index * 2; child <= size; child = child * 2)
		{
			// 如果存在右孩子，并且右孩子小于左孩子
			if (child < size && data[child] >= data[child + 1])
			{
				child++;
			}
			// 如果根结点，比最小的孩子节点值还要小，那么就结束比较，当前位置就是要插入的位置了
			if (tmp < data[child])
			{
				break;
			}
			else
			{
				// 孩子节点往上移动一层
				data[index] = data[child];
				index = child;
			}
		}
		data[index] = tmp;
	}

	// 向最小堆插入一个值
	public boolean insert(int value)
	{
		int index = ++size;  // 当前要插入的下标
		// 判断是否满了
		if (size == maxSize)
		{
			System.out.println("堆已满，无法插入 " + value);
			return false;
		}
		// 沿着祖先路径，查找待插入值的合适位置
		// 当父节点大于自己时
		while (value < data[index / 2])
		{
			// 将父节点放在自己当前的位置
			data[index] = data[index / 2];
			// 然后再继续和上一级比较
			index = index / 2;
		}
		// 循环结束之后，index就是待插入值value的最终位置了，因为data[index] < (data[0] = Integer.MIN_VALUE) 是不可能成立的
		// 这也是哨兵的作用，不用每次都判断index > 0
		data[index] = value;
		return true;
	}

	// 删除根结点，也就是最小值
	public int deleteMinValue()
	{
		// 判断是否为空
		if (size == 0)
		{
			System.out.println("堆空");
			return -1;
		}
		// 如果只有一个结点
		if (size == 1)
		{
			size = 0;
			return data[1];
		}
		// 要删除的值
		int delValue = data[1];
		// 用最后一个叶子节点值，替换根结点，然后再调整为最小堆
		int lastValue = data[size--];
		int index = 1;  // lastValue最终存放的位置，一开始是放在根结点的位置
		// 向下和孩子节点比较，找到lastValue最终存放的位置
		for (int child = index * 2; child <= size;)
		{
			// 如果存在右孩子，并且右孩子小于左孩子
			if (child < size && data[child] >= data[child + 1])
			{
				child++;
			}
			// 如果根结点，比最小的孩子节点值还要小，那么就结束比较，当前位置就是要插入的位置了
			if (lastValue < data[child])
			{
				break;
			}
			else
			{
				// 孩子节点往上移动一层
				data[index] = data[child];
				index = child;
				child = child * 2;
			}
		}
		data[index] = lastValue;
		return delValue;
	}

	public static void main(String[] args)
	{

	}

}

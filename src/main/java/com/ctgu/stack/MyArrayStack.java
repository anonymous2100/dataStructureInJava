package com.ctgu.stack;

/**
 * 顺序栈
 * 
 * @author lh2
 */
public class MyArrayStack implements IStack
{
	private Object[] data; // 保存栈中元素的数组
	private int size; // 栈中元素的个数，也就是data数组中存储的元素个数

	public MyArrayStack()
	{
		this.size = 0;
		this.data = new Object[10];  // 栈的初始长度
	}

	/**
	 * 获得栈的长度
	 */
	@Override
	public int length()
	{
		return this.size;
	}

	/**
	 * 进栈
	 */
	@Override
	public void push(Object elem)
	{
		ensureCapacity(size + 1); // 检查容量
		this.data[size] = elem;
		this.size++;
	}

	/**
	 * 出栈
	 */
	@Override
	public Object pop()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("栈为空，不能出栈！");
		}

		Object top = peek(); // 取栈顶元素
		this.data[size - 1] = null; // 栈顶元素值置为空
		this.size--; // 栈的大小减1

		// 返回栈顶元素
		return top;
	}

	/**
	 * 判断数组是否已满，如果已经满了，则扩充数组空间
	 * 
	 * @param size
	 *            进栈后栈中元素个数
	 */
	private void ensureCapacity(int size)
	{
		int len = data.length; // 进栈前栈中元素的个数
		if (size > len) // 数组已满
		{
			// 临时创建一个数组
			Object[] newData = new Object[size * 2];
			// 然后把栈中原来的数据复制过去
			for (int i = 0; i < len; i++)
			{
				newData[i] = data[i];
			}
			// 再把临时创建的数组的引用赋值给data数组
			this.data = newData;
		}
	}

	/**
	 * 取栈顶元素
	 */
	@Override
	public Object peek()
	{
		if (isEmpty())
		{
			return null;
		}
		return data[size - 1];
	}

	/**
	 * 判断栈是否为空
	 */
	@Override
	public boolean isEmpty()
	{
		return this.size == 0;
	}

	/**
	 * 清空栈
	 */
	@Override
	public void clear()
	{
		for (int i = 0; i < size; i++)
		{
			data[i] = null;
		}
		this.size = 0;
	}

	/**
	 * 输出栈
	 */
	public void printList()
	{
		System.out.print("栈中元素为：[");
		for (int i = 0; i < size; i++)
		{
			System.out.print(data[i] + " ");
		}
		System.out.println("]");
	}

	public static void main(String[] args)
	{
		MyArrayStack s = new MyArrayStack();

		for (int i = 0; i < 20; i++)
		{
			s.push(i);
		}
		s.printList();

		for (int i = 0; i < 10; i++)
		{
			System.out.println("当前栈顶元素为：" + s.peek());
			Object top = s.pop();
			System.out.println("元素" + top + "出栈！");
			s.printList();
			System.out.println("当前栈顶元素为：" + s.peek());

			System.out.println();
		}

		System.out.println("栈中元素个数为：" + s.length());
		s.printList();
		while (!s.isEmpty())
		{
			System.out.println("出栈元素："+s.pop());
		}

		s.printList();

	}
}

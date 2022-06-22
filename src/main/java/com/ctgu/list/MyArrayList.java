package com.ctgu.list;

/**
 * @ClassName: MyArrayList
 * @Description: 顺序表
 * @author lh2
 * @date 2020年4月23日 下午12:14:19
 */
public class MyArrayList implements AbstractList
{
	/**
	 * 用来保存数据的数组
	 */
	private Object[] dataArray;
	/**
	 * 保存最后一个元素位置的下标，也可以用来表示当前链表中的元素个数
	 */
	private int current;
	/**
	 * 链表大小
	 */
	private int size;

	public MyArrayList()
	{
		this(10);
		this.current = -1;
	}

	public MyArrayList(int initialSize)
	{
		if (initialSize < 0)
		{
			throw new RuntimeException("数组大小不正确！");
		}
		else
		{
			this.dataArray = new Object[initialSize];
			this.size = initialSize;
			this.current = -1;
		}
	}

	public int length()
	{
		return this.current + 1;
	}

	public void set(int index, Object data)
	{
		if (index < 0 || index > this.current)
		{
			throw new RuntimeException("元素索引不正确！");
		}
		else
		{
			this.dataArray[index] = data;
		}
	}

	public Object get(int index)
	{
		if (index < 0 || index > this.current)
		{
			throw new RuntimeException("元素索引不正确！");
		}
		else
		{
			return this.dataArray[index];
		}
	}

	public void add(Object data)
	{
		// 判断数组是否已满
		// 如果数组已满，则再创建一个大小为原来2倍的数组，并把原来的数据复制到新数组中，然后把新数组的引用赋给data数组
		if (current == size - 1)
		{
			Object[] newData = new Object[size * 2];
			for (int i = 0; i <= current; i++)
			{
				newData[i] = this.dataArray[i];
			}
			data = newData;
			newData = null;
		}

		current++;
		this.dataArray[current] = data;
	}

	public void insert(int index, Object element)
	{
		if (index < 0 || index > this.current)
		{
			throw new RuntimeException("元素索引不正确！");
		}
		else
		{
			// 如果数组已满，则再创建一个大小为原来3倍的数组，并把原来的数据复制到新数组中，然后把新数组的引用赋给data数组
			if (current == size - 1)
			{
				Object[] newData = new Object[size * 2];
				for (int i = 0; i <= current; i++)
				{
					newData[i] = this.dataArray[i];
				}
				this.dataArray = newData;
				newData = null;
			}

			// index索引及以后的数据往后移动一位
			for (int i = this.current; i >= index; i--)
			{
				this.dataArray[i + 1] = this.dataArray[i];
			}

			this.dataArray[index] = element;
			this.current++;
		}
	}

	public void delete(int index)
	{
		if (index < 0 || index > this.current)
		{
			throw new RuntimeException("元素索引不正确！");
		}
		else
		{
			// 若为删除最后一个元素
			if (index == this.current)
			{
				this.dataArray[index] = null;
			}
			else
			{// 否则，index后的元素往前移
				for (int i = index; i < this.current; i++)
				{
					this.dataArray[i] = this.dataArray[i + 1];
				}
			}
			this.current--;
		}
	}

	public void clear()
	{
		this.current = -1;
		for (int i = 0; i < dataArray.length; i++)
		{
			this.dataArray[i] = null;
		}
	}

	public boolean isEmpty()
	{
		return this.current == 0;
	}

	public void printList()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i <= this.current; i++)
		{
			sb.append(get(i)).append(" ,");
		}
		// 删除最后一个逗号
		if ('[' != sb.charAt(sb.length() - 1))
		{
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		System.out.println(sb);
	}

	public static void main(String[] args)
	{
		System.out.println("-------------------直接插入元素-------------------");
		MyArrayList list = new MyArrayList();
		for (int i = 0; i < 10; i++)
		{
			list.add(i);
		}
		list.printList();
		System.out.println("当前list长度为：" + list.length() + "\n");

		System.out.println("-------------------在指定位置处插入元素-------------------");
		list.insert(0, 97);
		list.insert(3, 46);
		list.insert(5, 23);
		list.insert(7, 54);
		list.printList();
		System.out.println("当前list长度为：" + list.length() + "\n");

		System.out.println("-------------------修改指定位置元素内容-------------------");
		list.set(0, 76);
		list.set(10, 101);
		list.set(9, 19);
		list.printList();
		System.out.println("当前list长度为：" + list.length() + "\n");

		System.out.println("-------------------删除指定位置元素内容-------------------");
		list.delete(9);
		System.out.println("删除指定位置的元素后：");
		list.printList();
		System.out.println("当前list长度为：" + list.length() + "\n");

		// 删除所有元素
		for (int i = 12; i >= 0; i--)
		{
			Object deleted = list.get(i);
			list.delete(i);
			System.out.print("删除索引为【" + i + "】处的元素【" + deleted + "】后，list为：");
			list.printList();
		}
	}

}

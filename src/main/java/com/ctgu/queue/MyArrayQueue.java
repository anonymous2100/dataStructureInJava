package com.ctgu.queue;

/**
 * 顺序队列（非循环队列），用数组实现（队列：队头(head,data[0])出队，队尾(tail,data[data.length-1)入队）
 * <p>
 * 队头始终指向队列第一个元素，队尾指向队列最后一个元素的下一个位置
 */
public class MyArrayQueue implements IQueue
{
	private Object[] data;
	private int head = 0;
	private int tail = 0;
	private int size;

	public MyArrayQueue()
	{
		this.data = new Object[10];
		this.size = 0;
	}

	@Override
	public int length()
	{
		return (tail - head);
	}

	@Override
	public boolean isEmpty()
	{
		return (head == tail);
	}

	@Override
	public Object peek()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("当前队列为空！");
		}

		Object top = data[head];

		return top;
	}

	/**
	 * 入队列
	 */
	@Override
	public void enqueue(Object elem)
	{
		ensureCapacity(size + 1);

		this.data[tail] = elem;
		this.tail++;

		this.size++;
	}

	/**
	 * 确保队列空间足够，如果没有空间，则自动扩充队列
	 * 
	 * @param i
	 */
	private void ensureCapacity(int len)
	{
		if (len >= data.length)
		{
			Object[] newArray = new Object[data.length * 2];
			for (int i = 0; i < data.length; i++)
			{
				newArray[i] = this.data[i];
			}
			this.data = newArray;
		}
	}

	/**
	 * 出队列
	 */
	@Override
	public Object dequeue()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("当前队列为空，无法出队列！");
		}

		Object headElem = peek();
		this.data[head] = null;
		this.head++;
		this.size--;

		return headElem;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < size; i++)
		{
			data[i] = null;
		}
	}

	public void printQueue()
	{
		System.out.print("[");
		for (int i = 0; i < size; i++)
		{
			System.out.print(data[i] + ",");
		}
		System.out.print("]\n");
	}

	public static void main(String[] args)
	{
		MyArrayQueue q = new MyArrayQueue();

		// for (int i = 0; i < 10; i++)
		// {
		// System.out.println("入队列：");
		// q.enqueue(i);
		// q.printQueue();
		// System.out.println("head=" + q.head + ",tail=" + q.tail + "\n");
		// }
		// System.out.println("-------------------");
		//
		// for (int i = 0; i < 10; i++)
		// {
		// q.dequeue();
		// q.printQueue();
		// System.out.println("head=" + q.head + ",tail=" + q.tail + "\n");
		// }
		// System.out.println(q.isEmpty());
		// System.out.println(q.length());

		for (int i = 0; i < 33; i++)
		{
			q.enqueue(i);
			q.printQueue();
			System.out.println("head=" + q.head + ",tail=" + q.tail + "\n");
		}
	}

}

package com.ctgu.queue;

/**
 * 顺序循环队列 <br>
 * 我们把队列的这种逻辑上首尾相连的顺序存储结构称为循环队列。 <br>
 * 如何判断循环队列究竟是空的还是满的： <br>
 * 现在问题又来了，我们之前说，空队列时，front指针等于rear指针，那么现在循环队列满的时候，也是front等于rear， <br>
 * 那么如何判断循环队列究竟是空的还是满的？有如下办法： <br>
 * 办法1：设置一个标志位flag。初始时置flag=0；每当入队列操作成功就置flag=1；每当出队列操作成功就置flag=0。 <br>
 * 则队列空的判断条件为：rear == front && tag==0；队列满的判断条件为：rear == front && tag= =1。 <br>
 * 办法2：保留一个元素的存储空间。此时，队列满时的判断条件为 (rear + 1) % maxSize == front； <br>
 * 队列空的判断条件还是front == rear。 <br>
 * 办法3：设计一个计数器count，统计队列中的元素个数。此时，队列满的判断条件为：count > 0 && rear == front ； <br>
 * 队列空的判断条件为count == 0。
 */
public class MyArrayCicleQueue implements IQueue
{
	private Object[] data; // 保存队列元素的数组
	private int head = 0; // 队列头指针
	private int tail = 0; // 队列尾指针
	private int size; // 队列大小

	public MyArrayCicleQueue()
	{
		this.data = new Object[10];
		this.size = 0;
	}

	@Override
	public int length()
	{
		return this.size;
	}

	@Override
	public boolean isEmpty()
	{
		return this.size == 0;
	}

	private boolean isFull()
	{
		if (size > 0 && head == tail)
		{
			return true;
		}
		return false;
	}

	@Override
	public Object peek()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("队列为空！");
		}

		Object top = data[head];

		return top;
	}

	@Override
	public void enqueue(Object elem)
	{
		if (isFull())
		{
			Object[] newArray = new Object[size * 2]; // 扩充数组容量为原来的2倍
			for (int i = 0; i < size; i++)
			{
				newArray[i] = data[i];
			}
			data = newArray;
			head = 0;  // 修改头指针为0
			tail = size; // 修改尾指针为size
		}

		data[tail] = elem;
		tail = (tail + 1) % data.length;   //

		size++;
	}

	@Override
	public Object dequeue()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("当前队列为空，无法出队列！");
		}

		Object topElem = peek();
		data[head] = null;

		head = (head + 1) % data.length;

		this.size--;

		return topElem;
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
		for (int i = 0; i < data.length; i++)
		{
			System.out.print(data[i] + ",");
		}
		System.out.print("]");
	}

	public static void main(String[] args)
	{
		MyArrayCicleQueue q = new MyArrayCicleQueue();

		System.out.println("入队列:");
		for (int i = 0; i < 10; i++)
		{
			q.enqueue(i);
			q.printQueue();
			System.out.println("\nhead=" + q.head + ",tail=" + q.tail + "");
			System.out.println("队列长度=" + q.length() + ",队列头=" + q.peek() + ",isEmpty=" + q.isEmpty() + ",isFull="
					+ q.isFull() + "\n");
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("出队列：");
		for (int i = 0; i < 5; i++)
		{
			q.dequeue();
			q.printQueue();
			System.out.println("\nhead=" + q.head + ",tail=" + q.tail + "");
			System.out.println("队列长度=" + q.length() + ",isEmpty=" + q.isEmpty() + ",isFull=" + q.isFull() + "\n");
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("入队列:");
		for (int i = 0; i < 3; i++)
		{
			q.enqueue(i);
			q.printQueue();
			System.out.println("\nhead=" + q.head + ",tail=" + q.tail + "");
			System.out.println("队列长度=" + q.length() + ",队列头=" + q.peek() + ",isEmpty=" + q.isEmpty() + ",isFull="
					+ q.isFull() + "\n");
		}
		System.out.println("----------------------------------------------------------------------------------------");
		// System.out.println("入队列:");
		// for (int i = 0; i < 10; i++)
		// {
		// q.enqueue(i);
		// q.printQueue();
		// System.out.println("\nhead=" + q.head + ",tail=" + q.tail + "");
		// System.out.println("队列长度=" + q.length() + ",队列头=" + q.peek() + ",isEmpty=" + q.isEmpty() + ",isFull="
		// + q.isFull() + "\n");
		// }
		//
		// System.out.println(">>>入队列");
		// // for (int i = 0; i < 8; i++)
		// // {
		// // q.enqueue(10+i);
		// // }
		// q.enqueue(123);
		// q.printQueue();
		// System.out.println("\nhead=" + q.head + ",tail=" + q.tail + "");
		// System.out.println(
		// "队列长度=" + q.length() + ",队列头=" + q.peek() + ",isEmpty=" + q.isEmpty() + ",isFull=" + q.isFull() + "\n");
	}

}

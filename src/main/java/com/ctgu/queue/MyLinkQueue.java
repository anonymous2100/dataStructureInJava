package com.ctgu.queue;

/**
 * 链队列，即使用链表实现队列，默认链表头为队列头，链表尾为队列尾，尾进头出
 */
public class MyLinkQueue implements IQueue
{
	private Node head; // 队列头
	private Node tail; // 队列尾
	private int size; // 队列大小

	public MyLinkQueue()
	{
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int length()
	{
		return this.size;
	}

	@Override
	public boolean isEmpty()
	{
		return (head == null) && (tail == null);
	}

	@Override
	public Object peek()
	{
		if (isEmpty())
		{
			// return null;
			throw new RuntimeException("队列为空！");
		}
		else
		{
			return head.data;
		}
	}

	@Override
	public void enqueue(Object elem)
	{
		Node temp = new Node(elem);
		if (isEmpty())
		{
			head = temp;  // head引用指向第一个结点
			tail = temp; // tail也指向第一个结点
		}
		else
		{
			tail.next = temp;  // 把新增的结点的引用指向tail的下一个结点
			tail = temp;  // 把tail指向最后一个结点
		}
		// 链表长度加1
		this.size++;
	}

	@Override
	public Object dequeue()
	{
		if (isEmpty())
		{
			throw new RuntimeException("队列为空，无法出队列！");
		}
		else
		{
			Object headData = head.data; // 取得头结点的数据
			head = head.next; // 使head指向其后继结点

			// 如果head后继结点为null，则表示队列已经为空了，需要把tail也置为null
			if (head == null)
			{
				tail = null;
			}
			this.size--;
			return headData;
		}
	}

	@Override
	public void clear()
	{
		this.size = 0;
		this.head.next = null;
	}

	/**
	 * 输出整个链表
	 */
	public void printListQueue()
	{
		System.out.print("当前队列为：[");
		Node temp = head;
		while (temp != null)
		{
			System.out.print(temp.data + ",");
			temp = temp.next;
		}
		System.out.print("]\n");
	}

	public class Node
	{
		Object data;  // 存储数据
		Node next; // 指向下一个结点的指针

		public Node()
		{
		}

		public Node(Object data)
		{
			this.data = data;
		}

		@Override
		public String toString()
		{
			return "Node [data=" + data + ", next=" + next + "]";
		}
	}

	public static void main(String[] args)
	{
		MyLinkQueue q = new MyLinkQueue();

		System.out.println("--------------------------入队列：");
		for (int i = 0; i < 10; i++)
		{
			System.out.println("元素" + i + " 入队：");
			q.enqueue(i);
			q.printListQueue();
			System.out.println("length=" + q.size + ",head=" + q.head.data + ",tail=" + q.tail.data + "\n");
		}

		System.out.println("--------------------------出队列：--------------------------");
		for (int i = 0; i < 10; i++)
		{
			System.out.println("元素" + q.peek() + " 出队：");
			q.dequeue();
			q.printListQueue();
			if (q.head != null && q.tail != null)
			{
				System.out.println("length=" + q.size + ",head=" + q.head.data + ",tail=" + q.tail.data + "\n");
			}
		}

	}
}

package com.ctgu.list;

/**
 * 单循环链表
 * 
 * @author lh2
 */
public class SingleCicleList implements IList
{
	private Node head = null; // 头结点
	private int size = 0; // 链表长度

	public SingleCicleList()
	{
		this.head = new Node();
	}

	/**
	 * 获得链表的长度
	 * 
	 * @return
	 */
	@Override
	public int length()
	{
		return this.size;
	}

	/**
	 * 判断链表是否为空
	 * 
	 * @return
	 */
	@Override
	public boolean isEmpty()
	{
		return this.size == 0;
	}

	/**
	 * 向链表中添加数据，采用尾插法，即每次新增数据都添加到链表末尾
	 * 
	 * @param data
	 */
	@Override
	public void add(Object data)
	{
		if (size == 0)
		{
			head.data = data;   // 头结点存储数据
			head.next = head;   // 下一个结点指向头结点

			this.size++;
		}
		else
		{
			// 创建一个临时结点
			Node temp = new Node(data);
			// 获得链表最后一个结点
			Node node = getLastNode();
			node.next = temp;

			temp.next = head; // 尾结点的下一个结点指向头结点，形成循环链表

			this.size++;
		}
	}

	/**
	 * 在链表的index处插入结点，新增的结点位于index处结点的后一位
	 */
	@Override
	public void insert(int index, Object data)
	{
		if (index < 0 || index > this.size)
		{
			throw new RuntimeException("插入元素的位置不合法！");
		}
		else
		{
			// 生成一个临时结点
			Node temp = new Node(data);

			Node currentNode = getNode(index);
			temp.next = currentNode.next;
			currentNode.next = temp;
			this.size++;
		}
	}

	/**
	 * 删除位于index索引处的结点
	 * 
	 * @param index
	 */
	@Override
	public void delete(int index)
	{
		if (index < 0 || index >= this.size)
		{
			throw new RuntimeException("删除元素的位置不合法！");
		}
		else
		{
			// 删除首元素结点
			if (index == 0)
			{
				head = head.next;
			}
			else
			{// 删除非首元素结点
				Node preNode = getNode(index - 1); // 获取当前结点的前一个结点
				Node nextNode = preNode.next.next; // 获取当前结点的下一个结点
				preNode.next = nextNode; // 把下一个结点的引用赋给前一个结点的next，即把当前结点从链表中摘掉
			}
			this.size--;
		}
	}

	/**
	 * 设置位于index索引处的结点的数据为data
	 * 
	 * @param index
	 * @param data
	 */
	@Override
	public void set(int index, Object data)
	{
		if (index < 0 || index > this.size)
		{
			throw new RuntimeException("元素索引不正确！");
		}
		else
		{
			Node current = getNode(index);
			current.data = data;
		}
	}

	/**
	 * 获得索引为index处的结点的数据
	 * 
	 * @param index
	 * @return
	 */
	@Override
	public Object get(int index)
	{
		if (index < 0 || index > this.size)
		{
			throw new RuntimeException("元素索引不正确！");
		}
		else
		{
			Node current = getNode(index);

			return current.data;
		}
	}

	/**
	 * 获得位于index索引处的结点的引用
	 * 
	 * @param index
	 * @return
	 */
	public Node getNode(int index)
	{
		if (index < 0 || index > this.size)
		{
			throw new RuntimeException("元素索引不正确！");
		}
		else
		{
			Node temp = head;

			int i = 0;
			while (i < index)
			{
				// System.out.println("i=" + i + ",temp.next=" + temp.next);
				temp = temp.next;
				i++;
			}
			return temp;
		}
	}

	/**
	 * 获得链表最后一个结点
	 * 
	 * @return
	 */
	public Node getLastNode()
	{
		Node temp = head;
		// 单循环链表，只要当前结点的下一个结点不是头结点，那此结点就不是尾结点
		while (temp.next != head)
		{
			temp = temp.next;
		}

		return temp;
	}

	/**
	 * 清空整个链表
	 */
	@Override
	public void clear()
	{
		this.size = 0;
		this.head.next = null;  // 保留头结点的引用，但是链表大小为0
	}

	/**
	 * 输出整个链表
	 */
	public void printList()
	{
		System.out.println();
		for (int i = 0; i < this.size; i++)
		{
			System.out.print("(" + get(i) + ")->");
		}
		System.out.println();
	}

	/**
	 * 单链表节点定义
	 */
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
	}

	// 测试主方法
	public static void main(String[] args)
	{
		SingleCicleList list = new SingleCicleList();

		for (int i = 1; i <= 5; i++)
		{
			list.add(i);
		}
		list.printList();
		System.out.println("删除数据后");

		list.delete(2);
		list.printList();
	}

}

package com.ctgu.stack;

/**
 * @ClassName: MyListStack
 * @Description: 链表实现的栈
 * @author lh2
 * @date 2020年4月23日 下午12:34:33
 */
public class MyListStack implements IStack
{
	private Node top = null;
	private int size;

	public MyListStack()
	{
		this.top = new Node();
		this.size = 0;
	}

	/**
	 * 进栈
	 */
	@Override
	public void push(Object elem)
	{
		if (size == 0)
		{
			top.data = elem;
			this.size++;
		}
		else
		{
			Node newNode = new Node(elem);
			newNode.next = top;
			top = newNode;

			this.size++;
		}
	}

	/**
	 * 出栈
	 */
	@Override
	public Object pop()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("栈已空，无法出栈！");
		}

		Object elem = peek();

		Node temp = top.next;
		top.next = null;
		top = temp;

		this.size--;

		return elem;
	}

	/**
	 * 取栈顶元素
	 */
	@Override
	public Object peek()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("栈为空！");
		}

		return top.data;
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
	 * 获得栈的长度
	 */
	@Override
	public int length()
	{
		return this.size;
	}

	/**
	 * 清空栈
	 */
	@Override
	public void clear()
	{
		this.size = 0;
		top.next = null;
	}

	/**
	 * 输出栈
	 */
	public void printList()
	{
		System.out.print("栈中元素为（栈顶->栈底）：[");
		Node temp = top;
		int i = 0;
		while (i < size)
		{
			System.out.print(temp.data + " ");
			temp = temp.next;
			i++;
		}
		System.out.println("]\n");
	}

	/**
	 * 节点定义
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

	public static void main(String[] args)
	{
		MyListStack s = new MyListStack();
		for (int i = 0; i < 10; i++)
		{
			s.push(i);
		}
		s.printList();

		s.pop();
		s.printList();

		System.out.println(s.peek());

		s.pop();
		s.printList();

		s.clear();
		s.printList();

		int i = 0;
		while (i < 20)
		{
			s.push(i);
			i++;
		}
		s.printList();
	}

}

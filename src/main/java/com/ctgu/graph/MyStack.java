package com.ctgu.graph;

// 基于数组的栈实现
public class MyStack
{
	private int[] a;  // 存储数据
	private int size;  // 当前元素的个数

	public MyStack(int capacity)
	{
		size = -1;
		a = new int[capacity];
	}

	public MyStack()
	{
		this(16);  // 默认容量为16
	}

	// 判空
	public boolean isEmpty()
	{
		return size == -1;
	}

	// 判满
	public boolean isFull()
	{
		return size == a.length - 1;
	}

	// 入栈
	public boolean push(int value)
	{
		if (isFull())
		{
			System.out.println("栈已满，无法入栈");
			return false;
		}
		a[++size] = value;
		return true;
	}

	// 出栈
	public int pop()
	{
		if (isEmpty())
		{
			System.out.println("栈空，无法出栈");
			return -1;
		}
		return a[size--];
	}

	// 获取栈顶元素
	public int peek()
	{
		if (isEmpty())
		{
			System.out.println("栈空，无法出栈");
			return -1;
		}
		return a[size];
	}

	// 测试
	public static void main(String[] args)
	{
		MyStack stack = new MyStack(3);
		// 入栈
		stack.push(1);
		stack.push(3);
		stack.push(5);
		stack.push(7);   // 栈满
		// 出栈
		while (!stack.isEmpty())
		{
			System.out.println("出栈：" + stack.pop());
		}
		stack.pop();  // 栈空
	}
}

//// ----------------------------------------------------------------------------------------
//// 基于链表的栈实现，使用头插法，实现栈后进先出的功能
//public class MyLinkedStack
//{
//	private Node head;  // 头节点
//	// 判空
//
//	public boolean isEmpty()
//	{
//		return head == null;
//	}
//
//	// 入栈
//	public void push(int value)
//	{
//		Node node = new Node(value);
//		// 如果是第一个节点，就当做头节点
//		if (head == null)
//		{
//			head = node;
//			return;
//		}
//		// 然新节点指向头节点的下一个节点，也就是头插法
//		node.next = head.next;
//		head.next = node;
//	}
//
//	// 出栈
//	public int pop()
//	{
//		if (isEmpty())
//		{
//			System.out.println("栈空");
//			return -1;
//		}
//		// 如果只有一个节点
//		if (head.next == null)
//		{
//			int value = head.data;
//			head = null;
//			return value;
//		}
//		Node tmp = head.next;  // 要删除的节点
//		head.next = tmp.next;  // 删除节点tmp
//		return tmp.data;
//	}
//
//	// 节点类
//	private class Node
//	{
//		int data;  // 值域
//		Node next; // 指针域，指向下一个节点
//
//		public Node(int value)
//		{
//			data = value;
//			next = null;
//		}
//	}
//
//	// 测试
//	public static void main(String[] args)
//	{
//		MyLinkedStack stack = new MyLinkedStack();
//		// 入栈
//		stack.push(1);
//		stack.push(3);
//		stack.push(5);
//		stack.push(7);
//		// 出栈
//		while (!stack.isEmpty())
//		{
//			System.out.println("出栈：" + stack.pop());
//		}
//		stack.pop();  // 栈空
//	}
//}

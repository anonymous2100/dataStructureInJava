package com.ctgu.graph;

// 基于数组的队列实现：使用循环队列
public class MyQueue
{
	private int[] a;
	private int front;
	private int rear;

	public MyQueue(int capacity)
	{
		a = new int[capacity + 1];  // 有一个位置不存储数据，为了区别空和满的状态，牺牲一个空间
		front = 0;
		rear = 0;
	}

	// 判空
	public boolean isEmpty()
	{
		return front == rear;
	}

	// 判满
	public boolean isFull()
	{
		return ((rear + 1) % a.length) == front;
	}

	// 入队
	public boolean add(int value)
	{
		if (isFull())
		{
			System.out.println("队列满");
			return false;
		}
		rear = (rear + 1) % a.length;
		a[rear] = value;
		return true;
	}

	// 出队
	public int remove()
	{
		if (isEmpty())
		{
			System.out.println("队列空");
			return -1;
		}
		front = (front + 1) % a.length;
		return a[front];
	}

	// 获取队头元素
	public int peek()
	{
		if (isEmpty())
		{
			System.out.println("队列空");
			return -1;
		}
		return a[front];
	}

	public static void main(String[] args)
	{
		MyQueue queue = new MyQueue(3);
		// 入队
		queue.add(1);
		queue.add(3);
		queue.add(5);
		queue.add(7);
		// 出队
		while (!queue.isEmpty())
		{
			System.out.println(queue.remove());
		}
	}
}

//// ----------------------------------------------------------------------------------------------
//// 基于链表的队列实现：单向链表，尾插法，头部删除
// public class MyLinkedQueue
// {
// // 节点类
// private class Node
// {
// int data; // 值域
// Node next; // 指针域，指向下一个节点
//
// public Node(int value)
// {
// data = value;
// next = null;
// }
// }
//
// private Node head; // 头节点
// private Node tail; // 尾节点
// // 判空
//
// public boolean isEmpty()
// {
// return head == null;
// }
//
// // 入队
// public boolean add(int value)
// {
// Node node = new Node(value);
// // 如果插入的是第一个节点，头节点，尾节点都是同一个节点
// if (head == null)
// {
// head = node;
// tail = head;
// return true;
// }
// // 让尾节点指向新节点
// tail.next = node;
// // 新节点成为新的尾节点
// tail = node;
// return true;
// }
//
// // 出队
// public int remove()
// {
// if (isEmpty())
// {
// System.out.println("队列空");
// return -1;
// }
// int value = head.data;
// head = head.next; // 删除头节点
// return value;
// }
//
// public static void main(String[] args)
// {
// MyLinkedQueue queue = new MyLinkedQueue();
// // 入队
// queue.add(1);
// queue.add(3);
// queue.add(5);
// // 出队
// while (!queue.isEmpty())
// {
// System.out.println(queue.remove());
// }
// }
// }

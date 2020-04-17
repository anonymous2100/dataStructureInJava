package com.ctgu.binaryTree;

/**
 * 二叉树节点定义
 */
public class Node
{
	int data;   // 节点数据
	Node leftChild; // 左子节点的引用
	Node rightChild; // 右子节点的引用

	public Node(int data)
	{
		this.data = data;
		leftChild = null;
		rightChild = null;
	}

	// 打印节点内容
	public void display()
	{
		System.out.println(data);
	}

}
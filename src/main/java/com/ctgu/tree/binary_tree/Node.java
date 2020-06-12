package com.ctgu.tree.binary_tree;

/**
 * @ClassName: Node
 * @Description: 二叉树的节点定义
 * @author lh2
 * @date 2020年4月23日 下午9:41:22
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
package com.ctgu.binaryTree;

/**
 * @ClassName: Tree
 * @Description: 二叉树的基本操作定义
 * @author lh2
 * @date 2020年4月23日 下午9:41:00
 */
public interface Tree
{
	// 插入新节点
	public boolean insert(int data);

	// 删除节点
	public boolean delete(int key);

	// 查找节点
	public Node find(int key);

	// 查找最大值
	public Node findMax();

	// 查找最小值
	public Node findMin();

	// 中序遍历
	public void infixOrder(Node current);

	// 前序遍历
	public void preOrder(Node current);

	// 后序遍历
	public void postOrder(Node current);

}

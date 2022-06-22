package com.ctgu.tree;

/**
 * @ClassName: Tree
 * @Description: 二叉树的基本操作定义
 * @author lh2
 * @date 2020年4月23日 下午9:41:00
 */
public interface AbstractTree
{
	/**
	 * 插入新节点
	 */
	// public boolean insert(int data);

	/**
	 * 给某个节点插入左节点
	 */
	public void insertLeft(Node parent, Node newNode);

	/**
	 * 给某个节点插入右节点
	 */
	public void insertRight(Node parent, Node newNode);

	/**
	 * 删除节点
	 */
	public boolean delete(int key);

	// /**
	// * 查找节点
	// */
	// public Node find(int key);

	// /**
	// * 查找最大值
	// */
	// public Node findMax(Node oot);
	//
	// /**
	// * 查找最小值
	// */
	// public Node findMin(Node root);

	/**
	 * 中序遍历
	 */
	public void midOrder(Node root);

	/**
	 * 前序遍历
	 */
	public void preOrder(Node root);

	/**
	 * 后序遍历
	 */
	public void postOrder(Node root);

}

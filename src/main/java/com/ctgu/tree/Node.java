package com.ctgu.tree;

/**
 * @ClassName: Node
 * @Description: 二叉树的节点定义
 * @author lh2
 * @date 2020年4月23日 下午9:41:22
 */
public class Node
{
	/**
	 * 节点数据
	 */
	int data;
	/**
	 * 左子节点的引用
	 */
	Node leftChild;
	/**
	 * 右子节点的引用
	 */
	Node rightChild;

	public Node(int data)
	{
		this.data = data;
		leftChild = null;
		rightChild = null;
	}

	/**
	 * 打印节点内容
	 */
	public void display()
	{
		System.out.println(data);
	}

	public int getData()
	{
		return data;
	}

	public void setData(int data)
	{
		this.data = data;
	}

	public Node getLeftChild()
	{
		return leftChild;
	}

	public void setLeftChild(Node leftChild)
	{
		this.leftChild = leftChild;
	}

	public Node getRightChild()
	{
		return rightChild;
	}

	public void setRightChild(Node rightChild)
	{
		this.rightChild = rightChild;
	}

	@Override
	public String toString()
	{
		return "Node [data=" + data + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
	}

	// 前序遍历
	public void preOrder()
	{
		System.out.println(this);
		if (this.leftChild != null)
		{
			this.leftChild.preOrder();
		}
		if (this.rightChild != null)
		{
			this.rightChild.preOrder();
		}
	}

	// 中序遍历
	public void infixOrder()
	{
		if (this.leftChild != null)
		{
			this.leftChild.infixOrder();
		}
		System.out.println(this);
		if (this.rightChild != null)
		{
			this.rightChild.infixOrder();
		}
	}

	// 后序遍历
	public void postOrder()
	{
		if (this.leftChild != null)
		{
			this.leftChild.postOrder();
		}
		if (this.rightChild != null)
		{
			this.rightChild.postOrder();
		}
		// System.out.println(this);
	}

	// 前序遍历查找指定的节点
	public Node preOrderSearch(int data)
	{
		// System.out.println("当前的节点数值为：" + this.data);
		Node res = null;
		if (this.data == data)
		{
			return this;
		}
		if (this.leftChild != null)
		{
			res = this.leftChild.preOrderSearch(data);
		}
		if (res != null)
		{
			return res;
		}
		if (this.rightChild != null)
		{
			res = this.rightChild.preOrderSearch(data);
		}
		return res;
	}

	// 中序遍历查找指定的节点
	public Node infixOrderSearch(int data)
	{

		Node res = null;
		if (this.leftChild != null)
		{
			res = this.leftChild.infixOrderSearch(data);
		}
		if (res != null)
		{
			return res;
		}
		// System.out.println("当前的节点数值为：" + this.data);
		if (this.data == data)
		{
			return this;
		}
		if (this.rightChild != null)
		{
			res = this.rightChild.infixOrderSearch(data);
		}
		return res;
	}

	// 后序遍历查找指定的节点
	public Node postOrderSearch(int data)
	{
		Node res = null;
		if (this.leftChild != null)
		{
			res = this.leftChild.postOrderSearch(data);
		}
		if (res != null)
		{
			return res;
		}
		if (this.rightChild != null)
		{
			res = this.rightChild.postOrderSearch(data);
		}
		if (res != null)
		{
			return res;
		}
		// System.out.println("当前的节点数值为：" + this.data);
		if (this.data == data)
		{
			return this;
		}
		return res;
	}

}
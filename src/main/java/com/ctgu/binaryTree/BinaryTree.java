package com.ctgu.binaryTree;

// 二叉树
public class BinaryTree
{
	private Node root;

	// 根节点 //插入一个节点
	public boolean insert(int data)
	{
		Node newNode = new Node(data);
		// 如果是空树，则新的节点就是根节点
		if (root == null)
		{
			root = newNode;
			return true;
		}
		Node current = root; // 当前节点
		Node parent = null;  // 记录当前节点的父节点
		while (current != null)
		{
			parent = current;
			// 如果data大于当前节点值，则在右子树插入
			if (data > current.data)
			{
				current = current.rightChild;
				// 如果当前节点的右节点为空，则插入到右节点
				if (current == null)
				{
					parent.rightChild = newNode;
					return true;
				}
			}
			// 如果data小于当前节点值，则在左子树插入
			else if (data < current.data)
			{
				current = current.leftChild;
				// 如果当前节点的左节点为空，则插入到左节点
				if (current == null)
				{
					parent.leftChild = newNode;
					return true;
				}
			}
			// data==current.data,重复的值不能插入
			else
			{
				return false;
			}
		}
		return false;
	}		// 根据关键字查找

	public Node find(int key)
	{
		Node current = root;  // 记录当前节点
		while (current != null)
		{			// 如果key大于当前节点值，则到右子树查找
			if (key > current.data)
			{
				current = current.rightChild;
			}
			// 如果key小于当前节点值，则到左子树查找
			else if (key < current.data)
			{
				current = current.leftChild;
			}
			// key==current.data,找到了就停止搜索
			else
			{
				break;
			}
		}
		// 如果返回的是null表示没有找到，不然就是找到了
		return current;
	}

	// 查找以Root为根的二叉树的最小值，最小值就是最左边的，并且没有左孩子的
	public Node findMin(Node Root)
	{
		Node current = Root;
		while (current.leftChild != null)
		{
			current = current.leftChild;
		}
		return current;
	}

	// 查找以Root为根的二叉树的最大值，最大值就是最右边的，并且没有右孩子的
	public Node findMax(Node Root)
	{
		Node current = Root;
		while (current.rightChild != null)
		{
			current = current.rightChild;
		}
		return current;
	}

	// 删除分三种情况：1.删除的是叶子节点 2.删除的节点只有一个孩子 3.有两个孩子的节点
	public boolean delete(int key)
	{
		Node delNode = root;          // 要删除的节点
		boolean isLeftChild = false;  // 要删除的节点是否在左子树上
		Node parent = null;
		// 要删除节点的父节点 //找到要删除的节点
		while (delNode != null)
		{			// 如果key大于当前节点值，则到右子树查找
			if (key > delNode.data)
			{
				parent = delNode;
				delNode = delNode.rightChild;
				isLeftChild = false;
			}			// 如果key小于当前节点值，则到左子树查找
			else if (key < delNode.data)
			{
				parent = delNode;
				delNode = delNode.leftChild;
				isLeftChild = true;
			}			// key==current.data,找到了就停止搜索
			else
			{
				break;
			}
		}
		// 要删除的节点不存在
		if (delNode == null)
		{
			return false;
		}
		System.out.println("删除的节点" + delNode.data + "父节点" + parent.data);
		// 如果是叶子节点
		if (delNode.leftChild == null && delNode.rightChild == null)
		{			// 如果这棵树只有一个根节点
			if (delNode == root)
			{
				root = null;
			}
			else
			{
				if (isLeftChild)
				{
					parent.leftChild = null;
				}
				else
				{
					parent.rightChild = null;
				}
			}
			return true;
		}
		// 如果删除的节点只有左孩子
		else if (delNode.leftChild != null && delNode.rightChild == null)
		{			// 删除的根节点
			if (delNode == root)
			{
				root = delNode.leftChild;
			}
			else
			{
				if (isLeftChild)
				{
					parent.leftChild = delNode.leftChild;
				}
				else
				{
					parent.rightChild = delNode.leftChild;
				}
			}
			return true;
		}
		// 如果删除的节点只有右孩子
		else if (delNode.leftChild == null && delNode.rightChild != null)
		{			// 删除的根节点
			if (delNode == root)
			{
				root = delNode.rightChild;
			}
			else
			{
				if (isLeftChild)
				{
					parent.leftChild = delNode.rightChild;
				}
				else
				{
					parent.rightChild = delNode.rightChild;
				}
			}
			return true;
		}
		// 有两个节点delNode.leftChild!=null&&delNode.rightChild!=null
		else
		{	// 找删除节点的替换节点有两种选择：
				// 1.该删除节点左子树的最大值 ，它一定没有右孩子
				// 2.该删除节点右子树的最小值，它一定没有左孩子
				// 这样，就把问题转化只有一个孩子的节点的删除了
				// 这里使用第二种方法
			Node rightMin = delNode.rightChild;
			parent = delNode;
			while (rightMin.leftChild != null)
			{
				parent = rightMin;
				rightMin = rightMin.leftChild;
			}
			delNode.data = rightMin.data;
			// 如果右孩子就是要删除节点的最小值 //这里的意思是要删除的节点右边只有一个节点
			if (parent == delNode)
			{
				parent.rightChild = null;
			}			// 如果替换节点存在右孩子
			if (rightMin.rightChild != null)
			{
				parent.leftChild = rightMin.rightChild;
			}
			return true;
		}
	}

	// 先序遍历
	public void preOrder(Node Root)
	{	// 如果不是空树
		if (Root != null)
		{
			// 先访问根节点
			System.out.print(Root.data + " ");	 // 递归中序遍历左子树
			preOrder(Root.leftChild);		    // 递归中序遍历右子树
			preOrder(Root.rightChild);
		}
	}

	// 中序遍历
	public void midOrder(Node Root)
	{	 // 如果不是空树
		if (Root != null)
		{// 递归中序遍历左子树
			midOrder(Root.leftChild);			// 再访问根节点
			System.out.print(Root.data + " ");	 // 递归中序遍历右子树
			midOrder(Root.rightChild);
		}
	}

	// 后序遍历
	public void postOrder(Node Root)
	{		// 如果不是空树
		if (Root != null)
		{			// 递归中序遍历左子树
			postOrder(Root.leftChild);		    // 递归中序遍历右子树
			postOrder(Root.rightChild);			// 最后访问根节点
			System.out.print(Root.data + " ");
		}
	}

	public static void main(String[] args)
	{
		BinaryTree bt = new BinaryTree();
		int[] a = { 10, 8, 15, 4, 9, 11, 20, 8, 5 };
		for (int i = 0; i < a.length; i++)
		{
			bt.insert(a[i]);
		}
		System.out.print("先序遍历：");
		bt.preOrder(bt.root);
		System.out.println();
		System.out.print("中序遍历：");
		bt.midOrder(bt.root);
		System.out.println();
		// 要判断有没有找到，不然会出现空指针异常
		if (bt.find(4) != null)
		{
			System.out.println("查找--->" + bt.find(4).data);
		}
		System.out.println("最小值：" + bt.findMin(bt.find(8)).data);
		System.out.println("最大值：" + bt.findMax(bt.find(8)).data);				// 删除节点
		System.out.println("删除成功？" + bt.delete(8));
		bt.preOrder(bt.root);
		System.out.println();
		bt.midOrder(bt.root);
	}
}

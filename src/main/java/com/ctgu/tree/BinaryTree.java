package com.ctgu.tree;

/**
 * @ClassName: BinaryTree
 * @Description: 二叉树基本定义实现
 * @author lh2
 * @date 2020年6月12日 下午4:26:24
 */
public class BinaryTree implements AbstractTree
{
	private Node root;

	public BinaryTree()
	{
	}

	public BinaryTree(Node root)
	{
		this.root = root;
	}

	public Node getRoot()
	{
		return root;
	}

	public void setRoot(Node root)
	{
		this.root = root;
	}

	/**
	 * 清空某个子树的所有节点 <br/>
	 * 二叉树的清空： <br/>
	 * 首先提供一个清空以某个节点为根节点的子树的方法，既递归地删除每个节点； <br/>
	 * 接着提供一个删除树的方法，直接通过第一种方法删除到根节点即可<br/>
	 */
	public void clear(Node node)
	{
		if (node != null)
		{
			clear(node.getLeftChild());
			clear(node.getRightChild());
			// 删除节点
			node = null;
		}
	}

	/**
	 * 清空树
	 */
	public void clear()
	{
		clear(root);
	}

	/**
	 * 判断二叉树是否为空
	 */
	public boolean isEmpty()
	{
		return root == null;
	}

	/**
	 * 获取二叉树的高度<br/>
	 * 首先要一种获取以某个节点为子树的高度的方法，使用递归调用。<br/>
	 * 如果一个节点为空，那么这个节点肯定是一颗空树，高度为0；<br/>
	 * 如果不为空，那么我们要遍历地比较它的左子树高度和右子树高度，<br/>
	 * 高的一个为这个子树的最大高度，然后加上自己本身的高度就是了<br/>
	 * 获取二叉树的高度，只需要调用第一种方法，即传入根节点<br/>
	 */
	public int height()
	{
		return height(root);
	}

	/**
	 * 获取以某节点为子树的高度
	 */
	public int height(Node node)
	{
		if (node == null)
		{
			return 0;
		}
		else
		{
			int lheight = height(node.getLeftChild());
			int rheight = height(node.getRightChild());
			return lheight > rheight ? (lheight + 1) : (rheight + 1);
		}
	}

	/**
	 * 求二叉树的节点数： 求节点数时，先看获取某个节点为子树的节点数的实现。<br/>
	 * 首先节点为空，则个数肯定为0； <br/>
	 * 如果不为空，那就算上这个节点之后继续递归所有左右子树的子节点数，<br/>
	 * 全部相加就是以所给节点为根的子树的节点数<br/>
	 * 如果求二叉树的节点数，则输入根节点即可<br/>
	 */
	public int size()
	{
		return size(root);
	}

	public int size(Node node)
	{
		if (node == null)
		{
			return 0;
		}
		else
		{
			// 计算本节点 所以要+1
			// 然后递归获取左子树节点数和右子树节点数，最终相加
			return 1 + size(node.getLeftChild()) + size(node.getRightChild());
		}
	}

	/**
	 * 返回某节点的父亲节点
	 */
	public Node getParent(Node subTree, Node node)
	{
		if (subTree == null)
		{
			return null;   // 如果是空子树，则没有父节点
		}
		if (subTree.getLeftChild() == node || subTree.getRightChild() == node)
		{
			return subTree;   // 如果子树的根节点的左右孩子之一是待查节点，则返回子树的根节点
		}
		Node parent = null;
		if (getParent(subTree.getLeftChild(), node) != null)
		{
			parent = getParent(subTree.getLeftChild(), node);
			return parent;
		}
		else
		{
			// 递归左右子树
			return getParent(subTree.getRightChild(), node);
		}
	}

	/**
	 * 查找node节点在二叉树中的父节点
	 */
	public Node getParent(Node node)
	{
		return (root == null || root == node) ? null : getParent(root, node);
	}

	/**
	 * 获取某个节点的左子树
	 */
	public Node getleftTree(Node node)
	{
		return node.getLeftChild();
	}

	/**
	 * 获取某个节点的右子树
	 */
	public Node getrightTree(Node node)
	{
		return node.getRightChild();
	}

	/**
	 * 给某个节点插入左节点
	 */
	public void insertLeft(Node parent, Node newNode)
	{
		parent.setLeftChild(newNode);
	}

	/**
	 * 给某个节点插入右节点
	 */
	public void insertRight(Node parent, Node newNode)
	{
		parent.setRightChild(newNode);
	}

	// @Override
	// public Node find(int key)
	// {
	// Node current = root; // 记录当前节点
	// while (current != null)
	// {
	// // 如果key大于当前节点值，则到右子树查找
	// if (key > current.data)
	// {
	// current = current.rightChild;
	// }
	// // 如果key小于当前节点值，则到左子树查找
	// else if (key < current.data)
	// {
	// current = current.leftChild;
	// }
	// // key==current.data,找到了就停止搜索
	// else
	// {
	// break;
	// }
	// }
	// // 如果返回的是null表示没有找到，不然就是找到了
	// return current;
	// }

	public Node preOrderSearch(int num)
	{
		if (root != null)
		{
			return root.preOrderSearch(num);
		}
		return null;
	}

	public Node infixOrderSearch(int num)
	{
		if (root != null)
		{
			return root.infixOrderSearch(num);
		}
		return null;
	}

	public Node postOrderSearch(int num)
	{
		if (root != null)
		{
			return root.postOrderSearch(num);
		}
		return null;
	}

	// // 查找以root为根的二叉树的最小值，最小值就是最左边的，并且没有左孩子的
	// @Override
	// public Node findMin(Node root)
	// {
	// Node current = root;
	// while (current.leftChild != null)
	// {
	// current = current.leftChild;
	// }
	// return current;
	// }

	// // 查找以root为根的二叉树的最大值，最大值就是最右边的，并且没有右孩子的
	// @Override
	// public Node findMax(Node root)
	// {
	// Node current = root;
	// while (current.rightChild != null)
	// {
	// current = current.rightChild;
	// }
	// return current;
	// }

	// 删除分三种情况：1.删除的是叶子节点 2.删除只有一个孩子的节点 3.删除有两个孩子的节点
	@Override
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
		// 如果是叶子节点
		System.out.println("删除的节点为" + delNode.data + "，其父节点为" + parent.data);
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

	/**
	 * 先序遍历
	 */
	@Override
	public void preOrder(Node root)
	{	// 如果不是空树
		if (root != null)
		{
			// 先访问根节点
			System.out.print(root.data + " ");
			// 遍历左子树
			preOrder(root.getLeftChild());
			// 遍历右子树
			preOrder(root.getRightChild());
		}
	}

	/**
	 * 中序遍历
	 */
	@Override
	public void midOrder(Node root)
	{
		// 如果不是空树
		if (root != null)
		{
			// 中序遍历左子树
			midOrder(root.getLeftChild());
			// 再访问根节点
			System.out.print(root.data + " ");
			// 中序遍历右子树
			midOrder(root.getRightChild());
		}
	}

	/**
	 * 后序遍历
	 */
	@Override
	public void postOrder(Node root)
	{
		// 如果不是空树
		if (root != null)
		{
			// 递归中序遍历左子树
			postOrder(root.getLeftChild());
			// 递归中序遍历右子树
			postOrder(root.getRightChild());
			// 最后访问根节点
			System.out.print(root.data + " ");
		}
	}

	public static void main(String[] args)
	{
		BinaryTree bt = new BinaryTree(new Node(10));

		// int[] a = { 10, 8, 15, 4, 9, 11, 20, 8, 5 };
		// 10
		// 8 11
		// 15 4 20
		// 9 8 5
		// 手动构建二叉树
		// 设置根节点的左右节点8,11
		bt.insertLeft(bt.getRoot(), new Node(8));
		bt.insertRight(bt.getRoot(), new Node(11));
		// 设置节点8的左右节点15,4
		bt.insertLeft(bt.preOrderSearch(8), new Node(15));
		bt.insertRight(bt.preOrderSearch(8), new Node(4));
		// 设置节点4的左右节点9
		bt.insertLeft(bt.preOrderSearch(4), new Node(9));
		// 设置节点11的右节点20
		bt.insertRight(bt.preOrderSearch(11), new Node(20));
		// 设置节点20的左右节点8,5
		bt.insertLeft(bt.preOrderSearch(20), new Node(8));
		bt.insertRight(bt.preOrderSearch(20), new Node(5));

		System.out.print("先序遍历：");
		bt.preOrder(bt.getRoot());
		System.out.println();

		System.out.print("中序遍历：");
		bt.postOrder(bt.getRoot());
		System.out.println();

		System.out.print("后序遍历：");
		bt.midOrder(bt.getRoot());
		System.out.println();

		// 要判断有没有找到，不然会出现空指针异常
		if (bt.preOrderSearch(4) != null)
		{
			System.out.println("查找--->" + bt.preOrderSearch(4).data + "是否成功？" + (bt.preOrderSearch(4) != null));
		}
		// System.out.println("最小值：" + bt.findMin(bt.preOrderSearch(8)).data);
		// System.out.println("最大值：" + bt.findMax(bt.preOrderSearch(8)).data);
		// 删除节点
		System.out.println("删除成功？" + bt.delete(8));

		bt.preOrder(bt.root);
		System.out.println();
		bt.midOrder(bt.root);
	}

}

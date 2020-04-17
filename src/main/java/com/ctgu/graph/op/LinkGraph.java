package com.ctgu.graph.op;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.ctgu.graph.MyQueue;
import com.ctgu.graph.MyStack;

// 邻接表的相关操作，包括图的建立、深度优先搜索、广度优先搜索、单源最短路径、多源最短路径、最小生成树的Prim和Kruskal算法。
public class LinkGraph
{
	// 表示无穷大
	private int INF = Integer.MAX_VALUE;

	private Vertex[] vertex;  // 存放顶点
	private int[] parent;

	// 图的初始化
	public LinkGraph(char[] vert, Edge[] edge)
	{
		// 读入顶点，并初始化
		vertex = new Vertex[vert.length];
		parent = new int[vert.length];
		for (int i = 0; i < vertex.length; i++)
		{
			vertex[i] = new Vertex();
			vertex[i].data = vert[i];       // 顶点值
			vertex[i].firstEdge = null;  // 还没有邻接点，当然没有边了
		}
		// 初始化边
		for (int i = 0; i < edge.length; i++)
		{
			char start = edge[i].start;
			char end = edge[i].end;
			// 获取顶点对应的序号
			int p1 = getPosition(start);
			int p2 = getPosition(end);
			// 1.把p2连接在以p1为头的链表中
			Node node1 = new Node();
			node1.index = p2;
			node1.weight = edge[i].weight;
			linkedLast(p1, node1);
			// 2.因为是无向图，所以还需要把p1连接在以p2为头的链表中
			Node node2 = new Node();
			node2.index = p1;
			node2.weight = edge[i].weight;
			linkedLast(p2, node2);
		}
	}

	// 获取某个顶点的序号以及将某个顶点插入到指定顶点的后面。
	// 获取某个顶点对应的序号
	public int getPosition(char v)
	{
		for (int i = 0; i < vertex.length; i++)
		{
			if (vertex[i].data == v)
			{
				return i;
			}
		}
		// 不存在这样的顶点则返回-1
		return -1;
	}

	// 尾插法，将顶点连接到链表的尾巴
	public void linkedLast(int index, Node node)
	{
		if (vertex[index].firstEdge == null)
		{
			vertex[index].firstEdge = node;
		}
		else
		{
			Node tmp = vertex[index].firstEdge;
			while (tmp.nextNode != null)
			{
				tmp = tmp.nextNode;
			}
			tmp.nextNode = node;
		}
	}

	// 打印图
	public void print()
	{
		System.out.println("邻接表存储的图：");
		for (int i = 0; i < vertex.length; i++)
		{
			System.out.print(vertex[i].data + "-->");
			// 如果存在邻接点
			Node tmp = vertex[i].firstEdge;
			while (tmp.nextNode != null)
			{
				System.out.print(vertex[tmp.index].data + "-->");
				tmp = tmp.nextNode;
			}
			System.out.print(vertex[tmp.index].data);
			System.out.println();
		}
	}

	// 深度优先搜索，从第一个顶点开始遍历
	public void DFS()
	{
		boolean[] visited = new boolean[vertex.length];  // 默认为false;
		int[] path = new int[vertex.length];  // 记录遍历的顶点序号
		int index = 0;  // path[]的索引
		MyStack stack = new MyStack(vertex.length);
		visited[0] = true;
		stack.push(0);
		path[index++] = 0;
		while (!stack.isEmpty())
		{
			int v = getUnVisitedAdjVertex(stack.peek(), visited);
			// 如果不存在没有访问的邻接点，就出栈，原路返回
			if (v == -1)
			{
				stack.pop();
			}
			// 否则，存在还没有访问过的邻接点，入栈，并标注已访问
			else
			{
				path[index++] = v;  // 访问邻接点
				visited[v] = true;  // 标志已访问
				stack.push(v);    // 入栈
			}
		}

		// 打印DFS路径
		System.out.println("DFS路径:");
		for (int i = 0; i < path.length; i++)
		{
			System.out.print(vertex[path[i]].data + " ");
		}
	}

	// 查找某个点的还没有被访问的邻接点的序号
	public int getUnVisitedAdjVertex(int v, boolean[] visited)
	{
		Node tmp = vertex[v].firstEdge;
		// 如果存在邻接点
		while (tmp != null)
		{
			// 并且邻接点还没有访问过，就返回该邻接点的序号
			if (visited[tmp.index] == false)
			{
				return tmp.index;
			}
			tmp = tmp.nextNode;
		}
		// 不存在没有被访问的邻接点
		return -1;
	}

	// 广度优先搜索，从第一个顶点开始遍历
	public void BFS()
	{
		boolean[] visited = new boolean[vertex.length];  // 默认为false;
		int[] path = new int[vertex.length];  // 记录遍历的顶点序号
		int index = 0;  // path[]的索引
		MyQueue queue = new MyQueue(vertex.length);
		visited[0] = true;
		queue.add(0);
		path[index++] = 0;
		while (!queue.isEmpty())
		{
			int v = getUnVisitedAdjVertex(queue.peek(), visited);
			// 如果不存在没有访问的邻接点，就出队
			if (v == -1)
			{
				queue.remove();
			}
			// 否则，存在还没有访问过的邻接点，入队，并标注已访问
			else
			{
				path[index++] = v;  // 访问邻接点
				visited[v] = true;  // 标志已访问
				queue.add(v);     // 入队
			}
		}

		// 打印BFS路径
		System.out.println("BFS路径:");
		for (int i = 0; i < path.length; i++)
		{
			System.out.print(vertex[path[i]].data + " ");
		}
	}

	// 单源最短路径问题：Dijkstra算法，s为起点，比上一种方法比较容易计算最短距离
	public void dijkstra(int s)
	{
		int[] path = new int[vertex.length];  // 记录到起点经过的顶点路径
		int[] distance = new int[vertex.length];  // 记录到起点的距离
		boolean[] visited = new boolean[vertex.length]; // 标记是否访问过
		// 初始化到起点的距离
		for (int i = 0; i < vertex.length; i++)
		{
			distance[i] = getWeight(s, i);
			if (i != s && distance[i] < INF)
			{
				path[i] = s;
			}
			else
			{
				path[i] = -1;
			}
		}
		visited[s] = true;  // 起点已经访问过了
		// 遍历所有顶点，并更新到起点的距离
		for (int i = 0; i < vertex.length; i++)
		{
			if (i == s)
			{
				continue;
			}
			int min = INF;
			int k = -1;
			// 找到距离起点距离最短的顶点
			// distance[j]=0,表示已经访问过了
			for (int j = 0; j < vertex.length; j++)
			{
				if (visited[j] == false && distance[j] < min)
				{
					min = distance[j];
					k = j;
				}
			}
			// for循环结束后，k就是要找的那个顶点
			visited[k] = true;  // 表示第k个顶点已经访问过了
			// 更新顶点k的邻接点到起点的最小距离
			for (int j = 0; j < vertex.length; j++)
			{
				// 如果不是k的邻接点
				if (getWeight(k, j) == INF)
				{
					continue;
				}
				int tmp = distance[k] + getWeight(k, j);
				// 如果是未被访问过的邻接点，则更新其到起点的距离
				if (visited[j] == false && distance[j] > tmp)
				{
					distance[j] = tmp;
					path[j] = k;  // 这里的意思是，顶点j到达起点，必定经过顶点k
				}
			}

		}

		// 打印Dijkstra算法的最短路径，这里的路径是逆序输出的，可以使用栈将其恢复正常
		System.out.printf("Dijkstra(%c)\n", vertex[s].data);
		for (int i = 0; i < vertex.length; i++)
		{
			// System.out.print(vertex[s].data+"到"+vertex[i].data+"的最短路径为：");
			System.out.print(path[i] + " ");
			int tmp = i;
			// tmp=1时，就到了起点了
			while (tmp != -1)
			{
				System.out.print(vertex[tmp].data + "<--");
				tmp = path[tmp];
			}
			System.out.print("    最小权值为：" + distance[i]);
			System.out.println();  // 换行
		}
	}

	// 获取边<start, end>的权值；若start和end不是连通的，则返回无穷大。
	private int getWeight(int start, int end)
	{
		if (start == end)
			return 0;
		Node node = vertex[start].firstEdge;
		while (node != null)
		{
			if (end == node.index)
				return node.weight;
			node = node.nextNode;
		}

		return INF;
	}

	// Floyd算法求解任意两个顶点的最短距离问题，也就是多源最短路径问题
	public void floyd()
	{
		// 存储最短路径
		int[][] dist = new int[vertex.length][vertex.length];
		// 记录最短路径经过的顶点
		int[][] prev = new int[vertex.length][vertex.length];
		// 初始化
		for (int i = 0; i < vertex.length; i++)
		{
			for (int j = 0; j < vertex.length; j++)
			{
				dist[i][j] = getWeight(i, j);  // 存储的是权值
				prev[i][j] = j;   // i到j一定会经过j
			}
		}
		// 三重循环，最外层的是顶点的个数，中间两层是遍历整个矩阵
		// 思想是：当k=0时，就借助于第k个顶点，如果i到j的距离可以变小，则更新最小距离
		// 其实就是借助于前k个顶点，如果i到j的距离可以变小，则更新最小距离
		for (int k = 0; k < vertex.length; k++)
		{
			for (int i = 0; i < vertex.length; i++)
			{
				for (int j = 0; j < vertex.length; j++)
				{
					// 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和prev[i][j]
					int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
					if (dist[i][j] > tmp)
					{
						// "i到j最短路径"对应的值,设为更小的一个(即经过k)
						dist[i][j] = tmp;
						// "i到j最短路径"对应的路径，经过k
						prev[i][j] = prev[i][k];
					}
				}
			}
		}

		// 打印floyd最短路径的结果
		System.out.printf("floyd: \n");
		for (int i = 0; i < vertex.length; i++)
		{
			for (int j = 0; j < vertex.length; j++)
				System.out.printf("%2d  ", dist[i][j]);
			System.out.printf("\n");
		}
	}

	// 最小生成树：Prim算法
	public void prim(int s)
	{
		int[] distance = new int[vertex.length];  // 记录到起点的距离
		// 初始化到起点的距离
		for (int i = 0; i < vertex.length; i++)
		{
			distance[i] = getWeight(s, i);  // 起点到顶点i的权值
		}
		int[] prims = new int[vertex.length];  // 记录访问的顶点序号
		int index = 0;  // prims[]的索引
		prims[index++] = s;  // 第一个访问的是起点s

		// 遍历所有顶点，并更新到起点的距离
		for (int i = 0; i < vertex.length; i++)
		{
			if (i == s)
			{
				continue;
			}
			int min = INF;
			int k = -1;
			// 找到距离起点距离最短的顶点
			// distance[j]=0,表示已经访问过了
			for (int j = 0; j < vertex.length; j++)
			{
				if (distance[j] != 0 && distance[j] < min)
				{
					min = distance[j];
					k = j;
				}
			}
			// for循环结束后，k就是要找的那个顶点
			prims[index++] = k;
			distance[k] = 0;  // 表示第k个顶点已经访问过了
			// 更新顶点k的邻接点到起点的最小距离
			for (int j = 0; j < vertex.length; j++)
			{
				// 如果不是k的邻接点
				if (getWeight(k, j) == INF)
				{
					continue;
				}
				int tmp = distance[k] + getWeight(k, j);
				// 如果是未被访问过的邻接点，则更新其到起点的距离
				if (distance[j] != 0 && distance[j] > tmp)
				{
					distance[j] = tmp;
				}
			}

		}

		// 打印最小生成树
		System.out.printf("prim(%c)\n", vertex[s].data);
		for (int i = 0; i < vertex.length - 1; i++)
		{
			System.out.print(vertex[prims[i]].data + "-->");
		}
		System.out.print(vertex[prims[vertex.length - 1]].data);
		int sum = 0; // 最小权值和
		for (int i = 1; i < vertex.length; i++)
		{
			int min = INF;
			for (int j = 0; j < i; j++)
			{
				if (getWeight(prims[i], prims[j]) < min)
				{
					min = getWeight(prims[i], prims[j]);
				}
			}
			sum += min;
		}
		System.out.print("最小权值和为：" + sum);
		System.out.println();
	}

	// 判断v是不是u的邻接点
	public boolean getAdjVertex(int u, int v)
	{
		Node tmp = vertex[u].firstEdge;
		while (tmp != null)
		{
			if (tmp.index == v)
			{
				return true;
			}
			tmp = tmp.nextNode;
		}
		return false;
	}

	// 最小生成树：Kruskal算法
	public void kruskal()
	{
		ArrayList<Edge> list = new ArrayList<>();
		// 初始化边
		for (int i = 0; i < vertex.length; i++)
		{
			for (int j = 0; j < vertex.length; j++)
			{
				// 如果两个顶点有边
				if (i != j && getWeight(i, j) < Integer.MAX_VALUE)
				{
					list.add(new Edge(vertex[i].data, vertex[j].data, getWeight(i, j)));
				}
			}
		}
		// 对边按权值排序
		Collections.sort(list, new Comparator<Edge>()
		{
			@Override
			public int compare(Edge o1, Edge o2)
			{
				return o1.weight - o2.weight; // 权值小的在前
			}
		});
		// 初始化并查集，parent[i]=-1;表示这棵树只有它自己，一开始是n棵树
		for (int i = 0; i < parent.length; i++)
		{
			parent[i] = -1;
		}
		// 下面才是kruskal算法
		// list.size()就是边的数量，这里的边其实是存了两次，因为是无向图
		int u, v, num = 0, sum = 0, index = 0;
		char[] result = new char[2 * vertex.length - 2]; // 记录结果的数组，边的顺序
		System.out.println("下面是kruskal算法：");
		for (int i = 0; i < list.size(); i++)
		{
			Edge e = list.get(i);
			u = e.start - 65;  // 将字符转换为整数下标
			v = e.end - 65;
			// 如果顶点不属于同一个集合
			if (findRoot(u) != findRoot(v))
			{
				sum += e.weight;
				result[index++] = vertex[u].data;
				result[index++] = vertex[v].data;
				num++;
				union(u, v);
			}
			// 如果有n-1条边，就退出了
			if (num == vertex.length - 1)
			{
				break;
			}
		}
		// 打印边的信息
		System.out.println("kruskal包括的边依次是：");
		for (int i = 0; i < result.length; i += 2)
		{
			System.out.println(result[i] + "--" + result[i + 1]);
		}
		System.out.println("kruskal的最小权值：" + sum);
	}

	// 查找某个顶点属于哪个集合
	public int findRoot(int v)
	{
		int root;  // 集合的根节点
		for (root = v; parent[root] >= 0; root = parent[root])
			;
		// 路径压缩
		while (root != v)
		{
			int tmp = parent[v];
			parent[v] = root;
			v = tmp;
		}
		return root;
	}

	// 将两个不同集合的元素进行合并，使两个集合中任两个元素都连通
	void union(int u, int v)
	{
		int r1 = findRoot(u), r2 = findRoot(v); // r1 为 u 的根结点，r2 为 v 的根结点
		int tmp = parent[r1] + parent[r2]; // 两个集合结点个数之和(负数)
		// 如果 R2 所在树结点个数 > R1 所在树结点个数(注意 parent[r1]是负数)
		if (parent[r1] > parent[r2]) // 优化方案――加权法则
		{
			parent[r1] = r2;
			parent[r2] = tmp;
		}
		else
		{
			parent[r2] = r1;
			parent[r1] = tmp;
		}
	}

	// 测试数据
	public static void main(String[] args)
	{
		char[] vexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };   // 顶点
		Edge[] edges = {                                   // 边
				// 起点 终点 权
				new Edge('A', 'B', 12), new Edge('A', 'F', 16), new Edge('A', 'G', 14), new Edge('B', 'C', 10),
				new Edge('B', 'F', 7), new Edge('C', 'D', 3), new Edge('C', 'E', 5), new Edge('C', 'F', 6),
				new Edge('D', 'E', 4), new Edge('E', 'F', 2), new Edge('E', 'G', 8), new Edge('F', 'G', 9), };

		LinkGraph graph = new LinkGraph(vexs, edges);
		// 打印图的邻接表
		graph.print();
		// 深度优先搜索
		graph.DFS();
		System.out.println();
		// 广度优先搜索
		graph.BFS();
		System.out.println();
		// 单源最短路径：Dijkstra算法
		System.out.println("单源最短路径：Dijkstra算法");
		graph.dijkstra(3);
		System.out.println();
		// 多源最短路径问题：Floyd算法
		graph.floyd();
		System.out.println();
		// 最小生成树：Prim算法
		graph.prim(0);
		// 最小生成树：Kruskal算法
		graph.kruskal();
	}

}

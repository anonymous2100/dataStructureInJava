package com.ctgu.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// 3、 建立图；先建立一个没有边的图，这里用邻接矩阵存储顶点的权值，若没有边，则权值设置为无穷大。
// 有权无向图
public class Graph
{
	private Vertex[] vertexList;  // 存放顶点
	private int[][] graphArray;       // 用矩阵表示边
	private MyStack stack;        // 栈
	private MyQueue queue;        // 队列
	private int size;             // 当前顶点数
	private int[] distance;       // 记录到起点的距离
	private int[] path;           // 记录最短路径经过的顶点
	// 比如path[w]=v，表示从起点到顶点w需要先经过w的父顶点v
	private int[][] dist;  // dist[i][j]记录i到j的最短距离
	private int[][] prev;  // prev[i][j]=k表示i到j的最短路径会经过顶点k
	// 并查集中指向父顶点的数组
	int[] parent;

	public Graph(int maxSize)
	{
		vertexList = new Vertex[maxSize];
		graphArray = new int[maxSize][maxSize];
		stack = new MyStack(maxSize);
		queue = new MyQueue(maxSize);
		size = -1;
		distance = new int[maxSize];
		path = new int[maxSize];
		dist = new int[maxSize][maxSize];
		prev = new int[maxSize][maxSize];
		parent = new int[vertexList.length];
		init();  // 初始化为没有边的图
	}

	// 初始化为没有边的图
	public void init()
	{
		for (int i = 0; i < graphArray.length; i++)
		{
			for (int j = 0; j < graphArray[i].length; j++)
			{
				if (i == j)
				{
					graphArray[i][j] = 0;   // 自己到自己的权重为0
				}
				else
				{
					// 还没有边的时候，权重初始化为无穷大
					graphArray[i][j] = Integer.MAX_VALUE;
				}
			}
		}
	}

	// 4、先图中添加顶点和边
	// 添加顶点
	public boolean addVertex(char v)
	{
		if (size == vertexList.length)
		{
			System.out.println("满了，无法添加顶点");
			return false;
		}
		vertexList[++size] = new Vertex(v);
		return true;
	}

	// 添加边
	public boolean addEdge(int start, int end, int weight)
	{
		if (start < 0 || start >= graphArray.length || end < 0 || end >= graphArray.length)
		{
			System.out.println("输入的顶点序号不符合要求");
			return false;
		}
		graphArray[start][end] = weight;
		graphArray[end][start] = weight;
		return true;
	}

	// 5、遍历图中的顶点（包括深度优先搜索和广度优先搜索）
	// 深度优先搜索depthFirstSearch，有点类似于树的先序遍历
	public void DFS()
	{
		System.out.print(vertexList[0].value + " "); // 访问第一个顶点
		vertexList[0].visited = true;  // 表示第一个顶点已经访问过了
		stack.push(0);   // 将第一个顶点入栈
		// 当栈中还有元素
		while (!stack.isEmpty())
		{
			// 找到栈当前顶点邻接且未被访问的顶点
			int v = getUnvisitedVertex(stack.peek());
			// 如果当前顶点值为-1，则表示没有邻接且未被访问顶点，那么出栈顶点
			if (v == -1)
			{
				stack.pop();
			}
			else
			{ // 否则访问下一个邻接顶点
				vertexList[v].visited = true;
				System.out.print(vertexList[v].value + " ");
				stack.push(v);
			}
		}

		// 恢复visited为false，方便下次访问
		for (int i = 0; i < vertexList.length; i++)
		{
			vertexList[i].visited = false;
		}

	}

	// 找到与某一顶点邻接且未被访问的顶点
	public int getUnvisitedVertex(int v)
	{
		for (int i = 0; i < graphArray.length; i++)
		{
			// v顶点与i顶点相邻（邻接矩阵值为1）且未被访问 wasVisited==false
			if (v != i && graphArray[v][i] < Integer.MAX_VALUE && vertexList[i].visited == false)
			{
				return i;
			}
		}
		return -1;
	}

	// 广度优先搜索breadthFirstSearch，有点类似于树的层次遍历
	public void BFS()
	{
		System.out.print(vertexList[0].value + " "); // 访问第一个顶点
		vertexList[0].visited = true;  // 标志已经访问过了
		queue.add(0);  // 将第一个顶点入队
		// 当队列不为空
		while (!queue.isEmpty())
		{
			// 寻找当前顶点没有访问的邻接点
			int v = getUnvisitedVertex(queue.peek());
			if (v != -1)
			{
				System.out.print(vertexList[v].value + " ");
				vertexList[v].visited = true;
				queue.add(v);
			}
			else
			{
				queue.remove();
			}
		}

		// 恢复visited为false，方便下次访问
		for (int i = 0; i < vertexList.length; i++)
		{
			vertexList[i].visited = false;
		}
	}

	// 6、Dijkstra解决单源最短路径问题
	// Dijkstra解决单源最短路径问题
	// 它的主要特点是以起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止
	// s表示的是以第几个顶点为起点，从0开始
	public void dijkstra(int s)
	{
		// 初始化距离和路径
		for (int i = 0; i < vertexList.length; i++)
		{
			distance[i] = Integer.MAX_VALUE; // 到起点的距离设置为无穷大
			path[i] = -1;  // 到起点的路径初始化为-1
		}
		distance[s] = 0;  // 到起点本身的距离为0
		// 每次取最小值，其实可以用最小堆来实现，这里就直接比较了
		// 进行n次循环
		for (int i = 0; i < vertexList.length; i++)
		{
			int k = 0;  // 记录最小路径的顶点序号
			int min = Integer.MAX_VALUE;
			// 从未被访问过的顶点中找一个距离最小的顶点
			for (int j = 0; j < vertexList.length; j++)
			{
				// 如果还没有访问过，并且比当前值要小
				if (vertexList[j].visited == false && distance[j] < min)
				{
					vertexList[j].visited = true; // 设置为已访问
					min = distance[j];      	// 更新最小值
					k = j;    // 记录最小路径的顶点序号
				}
			}
			// 修正当前最短路径和前驱顶点
			// 即，当已知"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"。
			for (int j = 0; j < vertexList.length; j++)
			{
				// 找顶点k的邻接点j，并更新它的邻接点到起点的最短路径
				int tmp = (graphArray[k][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : (min + graphArray[k][j]));
				// min + graphArray[k][j]就表示顶点k的最短路径加上<k,j>边的权重，即为顶点j可能的最短路径
				if (vertexList[j].visited == false && (tmp < distance[j]))
				{
					distance[j] = tmp;  // 更新顶点j到起点的最短路径
					path[j] = k;        // 设置它的父顶点为k
				}
			}
		}

		// 打印Dijkstra最短路径的结果
		printDijkstra(s);

		// 恢复visited为false，方便下次访问
		for (int i = 0; i < vertexList.length; i++)
		{
			vertexList[i].visited = false;
		}
	}

	// 打印Dijkstra最短路径的结果
	public void printDijkstra(int s)
	{
		// 利用栈后进先出的特性，将路径逆序
		MyStack st = new MyStack(vertexList.length);
		System.out.printf("dijkstra(%c): \n", vertexList[s].value);
		for (int i = 0; i < vertexList.length; i++)
		{
			System.out.printf("  shortest(%c, %c)=%d 路径为：", vertexList[s].value, vertexList[i].value, distance[i]);
			// 这里可以用一个栈来存储顶点，然后出栈就是顺序输出了，而不是反向输出
			// 打印路径
			st.push(i);  // 终点
			int tmp = path[i];
			while (tmp != -1)
			{
				st.push(tmp);
				tmp = path[tmp];
			}
			while (!st.isEmpty())
			{
				System.out.printf("%c-->", vertexList[st.pop()].value);
			}
			System.out.println();
		}
	}

	// 7、Floyd算法求解任意两个顶点的最短距离问题，也就是多源最短路径问题
	public void floyd()
	{
		// 初始化
		System.out.println("初始化的值：");
		for (int i = 0; i < vertexList.length; i++)
		{
			for (int j = 0; j < vertexList.length; j++)
			{
				dist[i][j] = graphArray[i][j];  // 存储的是权值
				prev[i][j] = j;   // i到j一定会经过j
			}
		}
		// 三重循环，最外层的是顶点的个数，中间两层是遍历整个矩阵
		// 思想是：当k=0时，就借助于第k个顶点，如果i到j的距离可以变小，则更新最小距离
		// 其实就是借助于前k个顶点，如果i到j的距离可以变小，则更新最小距离
		for (int k = 0; k < vertexList.length; k++)
		{
			for (int i = 0; i < vertexList.length; i++)
			{
				for (int j = 0; j < vertexList.length; j++)
				{
					// 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和prev[i][j]
					int tmp = (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) ? Integer.MAX_VALUE
							: (dist[i][k] + dist[k][j]);
					if (dist[i][j] > tmp)
					{
						// "i到j最短路径"对应的值设，为更小的一个(即经过k)
						dist[i][j] = tmp;
						// "i到j最短路径"对应的路径，经过k
						prev[i][j] = prev[i][k];
					}
				}
			}
		}

		// 打印floyd最短路径的结果
		System.out.printf("floyd: \n");
		for (int i = 0; i < vertexList.length; i++)
		{
			for (int j = 0; j < vertexList.length; j++)
				System.out.printf("%2d  ", dist[i][j]);
			System.out.printf("\n");
		}
	}

	// 8、Prim算法解决最小生成树问题，以顶点为思考对象
	public void prim(int start)
	{
		int[] prims = new int[vertexList.length]; // 记录最小生成树的顶点序号
		// 初始化
		for (int i = 0; i < vertexList.length; i++)
		{
			distance[i] = graphArray[start][i];  // 到起点的权值
			prims[i] = -1;
		}
		distance[start] = 0;  // 自己到自己的距离为0
		int index = 0;  // 最小生成树的索引
		prims[index++] = start;
		// vertexList[start].visited=true; //表示已经在最小生成树中

		for (int i = 0; i < vertexList.length; i++)
		{
			if (i == start)
			{
				continue;
			}
			int min = Integer.MAX_VALUE;
			int k = -1;
			for (int j = 0; j < vertexList.length; j++)
			{
				// distance[j]==0，表示已经在最小生成树中
				// 如果不在最小生成树中，并且与最小生成树中的某个顶点组成的边的权值更小
				if (distance[j] != 0 && distance[j] < min)
				{
					min = distance[j];
					k = j;
				}
			}

			// 循环结束后，第k个顶点就是和已经收录的顶点构成边权值最小的顶点
			prims[index++] = k;
			// vertexList[k].visited=true;
			distance[k] = 0;  // 表示已经在最小生成树中
			// 更新第k个顶点到未被收录进最小生成树中邻接点的权值
			for (int j = 0; j < vertexList.length; j++)
			{
				// 如果j是k的未被收录的邻接点
				if (distance[j] != 0 && graphArray[k][j] + distance[k] < distance[j])
				{
					distance[j] = graphArray[k][j] + distance[k];
				}
			}
		}

		// 打印最小生成树
		for (int i = 0; i < vertexList.length; i++)
		{
			System.out.print(vertexList[prims[i]].value + " ");
		}
		// 打印最小权值：从最小生成树的第二个顶点开始，找它到前驱顶点的最小权值
		int sum = 0;
		// 一共n-1条边
		for (int i = 1; i < vertexList.length; i++)
		{
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++)
			{
				if (graphArray[prims[j]][prims[i]] < min)
				{
					// prims[j]表示已经在最小生成树中的顶点
					min = graphArray[prims[j]][prims[i]];
				}
			}
			sum += min;
		}
		System.out.println("最小权值和为：" + sum);
	}

	// 9、Kruskal算法：求最小生成树
	public void kruskal()
	{
		ArrayList<Edge> list = new ArrayList<>();
		// 初始化边
		for (int i = 0; i < vertexList.length; i++)
		{
			for (int j = 0; j < vertexList.length; j++)
			{
				// 如果两个顶点有边
				if (graphArray[i][j] != 0 && graphArray[i][j] < Integer.MAX_VALUE)
				{
					list.add(new Edge(i, j, graphArray[i][j]));
				}
			}
		}
		// 对边按权值排序
		Collections.sort(list, new Comparator<Edge>()
		{
			@Override
			public int compare(Edge o1, Edge o2)
			{
				return o1.w - o2.w; // 权值小的在前
			}
		});
		// 初始化并查集，parent[i]=-1;表示这棵树只有它自己，一开始是n棵树
		for (int i = 0; i < parent.length; i++)
		{
			parent[i] = -1;
		}
		// 下面才是kruskal算法
		// list.size()就是边的数量
		int u, v, num = 0, sum = 0, index = 0;
		char[] result = new char[2 * vertexList.length - 2]; // 记录结果的数组，边的顺序
		System.out.println("下面是kruskal算法：");
		for (int i = 0; i < list.size(); i++)
		{
			Edge e = list.get(i);
			u = e.u;
			v = e.v;
			// 如果顶点不属于同一个集合
			if (findRoot(u) != findRoot(v))
			{
				sum += e.w;
				result[index++] = vertexList[u].value;
				result[index++] = vertexList[v].value;
				num++;
				union(u, v);
			}
			// 如果有n-1条边，就退出了
			if (num == vertexList.length - 1)
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

	// 10、测试
	public static void main(String[] args)
	{
		Graph g = new Graph(7);
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		g.addVertex('F');
		g.addVertex('G');

		// 下面是边的关系，有边的值为权重，无边的值为无穷大
		// int matrix[][] = {
		// /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
		// /*A*/ { 0, 12, INF, INF, INF, 16, 14},
		// /*B*/ { 12, 0, 10, INF, INF, 7, INF},
		// /*C*/ { INF, 10, 0, 3, 5, 6, INF},
		// /*D*/ { INF, INF, 3, 0, 4, INF, INF},
		// /*E*/ { INF, INF, 5, 4, 0, 2, 8},
		// /*F*/ { 16, 7, 6, INF, 2, 0, 9},
		// /*G*/ { 14, INF, INF, INF, 8, 9, 0}};
		g.addEdge(0, 1, 12);  // AB相连
		g.addEdge(0, 5, 16); // AF
		g.addEdge(0, 6, 14); // AG
		g.addEdge(1, 2, 10);   // BC相连
		g.addEdge(1, 5, 7);   // BF
		g.addEdge(2, 3, 3); // CD相连
		g.addEdge(2, 4, 5); // CE相连
		g.addEdge(2, 5, 6); // CF
		g.addEdge(3, 4, 4); // DE
		g.addEdge(4, 5, 2); // EF
		g.addEdge(4, 6, 8); // EG
		g.addEdge(5, 6, 9); // FG

		// 深度优先访问
		System.out.println("深度优先：");
		g.DFS();
		System.out.println();
		// 广度优先搜索
		System.out.println("广度优先：");
		g.BFS();
		System.out.println();

		// Dijkstra算法
		g.dijkstra(3);
		System.out.println("路径：");
		for (int i = 0; i < g.vertexList.length; i++)
		{
			System.out.print(g.path[i] + " ");
		}

		// Floyd算法
		System.out.println();
		g.floyd();

		// 最小生成树：prim算法
		System.out.println("最小生成树：prim算法");
		g.prim(0);

		// 最小生成树：kruskal算法
		g.kruskal();
	}
}
package com.ctgu.graph;

// 2、边的类型
// 存储边的两个顶点及边的权值
public class Edge
{
	public int u;  // 顶点
	public int v;  // 顶点
	public int w; // 权值

	public Edge(int u, int v, int w)
	{
		this.u = u;
		this.v = v;
		this.w = w;
	}
}

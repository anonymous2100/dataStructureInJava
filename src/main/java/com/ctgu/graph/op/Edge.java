package com.ctgu.graph.op;

// 边的类型
public class Edge
{
	char start;  // 起点
	char end;    // 终点
	int weight;  // 边的权值

	public Edge(char start, char end, int weight)
	{
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}
package com.ctgu.graph;

// 1、顶点类型
// 图的顶点类型
public class Vertex
{
	public char value;   // 顶点值
	public boolean visited; // 顶点是否被访问过

	public Vertex(char v)
	{
		value = v;
		visited = false;
	}
}

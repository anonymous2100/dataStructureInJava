package com.ctgu.stack;

public interface IStack
{
	// 进栈
	void push(Object data);

	// 出栈
	Object pop();

	// 访问栈顶元素
	Object peek();

	// 返回栈的长度
	int length();

	// 判断栈是否为空
	boolean isEmpty();

	// 清空栈
	void clear();

}

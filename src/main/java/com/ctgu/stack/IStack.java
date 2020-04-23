package com.ctgu.stack;

/**
 * @ClassName: IStack
 * @Description: 栈的基本操作定义
 * @author lh2
 * @date 2020年4月23日 下午12:29:47
 */
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

package com.ctgu.queue;

/**
 * @ClassName: AbstractQueue
 * @Description: 队列的基本操作
 * @author lh2
 * @date 2020年4月23日 下午12:35:30
 */
public interface AbstractQueue
{
	/**
	 * 获取队列长度
	 */
	int length();

	/**
	 * 判断队列是否为空
	 */
	boolean isEmpty();

	/**
	 * 取队列头元素
	 */
	Object peek();

	/**
	 * 入队列
	 */
	void enqueue(Object elem);

	/**
	 * 出队列
	 */
	Object dequeue();

	/**
	 * 清空队列
	 */
	void clear();
}

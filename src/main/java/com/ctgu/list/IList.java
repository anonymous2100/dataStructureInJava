package com.ctgu.list;

/**
 * @ClassName: IList
 * @Description: 线性表通用方法
 * @author lh2
 * @date 2020年4月23日 下午12:15:55
 */
public interface IList
{
	// 获取线性表长度
	int length();

	// 向线性表中添加数据
	void add(Object data);

	// 在指定位置插入数据
	void insert(int index, Object data);

	// // 删除数据
	// void remove(Object data);

	// 删除指定位置处的数据
	void delete(int index);

	// 修改指定位置处的数据
	void set(int index, Object data);

	// 获取指定位置的数据
	Object get(int index);

	// 清除线性表
	void clear();

	// 判断线性表是否为空
	boolean isEmpty();
}

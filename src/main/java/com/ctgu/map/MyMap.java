package com.ctgu.map;

/**
 * @ClassName: MyMap
 * @Description:哈希表接口定义
 * @author lh2
 * @date 2020年4月23日 下午12:26:17
 * @param <K>
 *            键
 * @param <V>
 *            值
 */
public interface MyMap<K, V>
{
	V put(K k, V v);

	V get(K k);

	int size();

	// 哈希表内部链表节点定义
	public interface Entry<K, V>
	{
		K getKey();

		V getValue();
	}

}

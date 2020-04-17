package com.ctgu.map;

/**
 * 哈希表接口
 *
 * @param <K>
 * @param <V>
 */
public interface MyMap<K, V>
{
	V put(K k, V v);

	V get(K k);

	int size();

	public interface Entry<K, V>
	{
		K getKey();

		V getValue();
	}

}

package com.ctgu.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MyHashMap
 * @Description:仿jdk1.7简单实现HashMap，实现了元素的存取和自动扩容操作
 * @author lh2
 * @date 2020年4月23日 下午12:26:04
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> implements MyMap<K, V>
{
	// 数组的默认初始化长度
	private static final int DEFAULT_INITAL_CAPACITY = 16;
	// 默认阈值比例
	private static final float DEFAULT_LOAD_FACTORY = 0.75f;
	// 数组初始化长度
	private int initalCapacity;
	// 初始化阈值
	private float loadFactor;
	// 存放键值对元素的数组引用
	private Entry<K, V>[] table = null;
	// 当前map中元素的个数
	private int size = 0;

	public MyHashMap()
	{
		this(DEFAULT_INITAL_CAPACITY, DEFAULT_LOAD_FACTORY);
	}

	public MyHashMap(int capacity, float loadfactor)
	{
		// 对输入参数的校验
		if (capacity < 0)
		{
			throw new IllegalArgumentException("capacity illegal:" + capacity);
		}
		if (loadfactor <= 0 || Float.isNaN(loadfactor))
		{
			throw new IllegalArgumentException("loadfactor illegal:" + loadfactor);
		}

		initalCapacity = capacity;
		loadFactor = loadfactor;
		// 初始化数组
		table = new Entry[this.initalCapacity];
	}

	public V put(K key, V value)
	{
		// 当满足扩容条件后，开始扩容
		if (size >= (this.initalCapacity * this.loadFactor))
		{
			System.out.println("开始扩容...");
			resize(2 * this.initalCapacity);
		}

		// 对key做一次hash获得索引
		int index = hash(key);

		// 从索引位置取值
		Entry<K, V> oldEntry = table[index];
		// 若之前这个位置没有值，将新值放进去
		if (oldEntry == null)
		{
			table[index] = new Entry<K, V>(key, value, null);// 此为单链表的首元素结点
			this.size++;
		}
		else
		{	// 若之前这个位置有值，则需要遍历单链表
			Entry<K, V> entry = table[index];
			while (entry.next != null)
			{
				if (key == entry.key || key.equals(entry.key))
				{
					V oldValue = entry.value;
					// 覆盖旧值
					entry.value = value;
					return oldValue;
				}
				entry = entry.next;
			}
			// 头插法
			table[index] = new Entry<K, V>(key, value, oldEntry);
			this.size++;
		}

		if (oldEntry == null)
		{
			return null;
		}
		return oldEntry.getValue();
	}

	private int hash(K k)
	{
		// 数组下标从0开始，所以这里要用数组长度减1来取余
		// jdk源码里用的是位操作（这里为了方便理解，就直接用的%取余），原理如下所述：
		// 取余(%)操作中如果除数是2的幂次则等价于与其除数减一的与(&)操作
		// （也就是说 hash%length==hash&(length-1)的前提是 length 是2的 n 次方；）。
		// 并且 采用二进制位操作 &，相对于%能够提高运算效率，这就解释了 HashMap 的长度为什么是2的幂次方。
		int index = k.hashCode() % (this.initalCapacity - 1);
		return index < 0 ? -index : index;
	}

	private void resize(int capacity)
	{
		// 扩容操作
		Entry<K, V>[] newTable = new Entry[capacity];
		this.initalCapacity = capacity;
		this.size = 0;

		// 以下是重新哈希过程：将原来数组里的数据全部放到新的扩容数组里，数组下标重新计算，链表节点重新连接
		// 这个过程比较耗时，所以最好在使用HashMap时就预先判断需要的容量大小，并指定初始容量大小
		// 将原来表中的所有元素先存在list中
		Entry<K, V>[] oldTable = this.table;
		List<Entry<K, V>> entryList = new ArrayList<Entry<K, V>>();
		for (int i = 0; i < oldTable.length; i++)
		{
			Entry<K, V> entry = oldTable[i];
			while (entry != null)
			{
				entryList.add(entry);
				entry = entry.next;
			}
		}
		// 将table引用指向新的table
		this.table = newTable;

		// 重新进行hash
		for (Entry<K, V> entry : entryList)
		{
			this.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V get(K key)
	{
		int index = hash(key);

		// 没找到key值
		if (table[index] == null)
		{
			return null;
		}

		return find(key, table[index]);// 递归遍历链表查找
	}

	private V find(K key, Entry<K, V> entry)
	{
		if (key == entry.getKey() || key.equals(entry.getKey()))
		{
			return entry.getValue();
		}
		else
		{
			if (entry.next != null)
			{
				return find(key, entry.next);
			}
		}
		return null;
	}

	@Override
	public int size()
	{
		return this.size;
	}

	public class Entry<K, V> implements MyMap.Entry<K, V>
	{
		K key;
		V value;
		Entry<K, V> next;// 下一个Entry结点的引用

		public Entry(K key, V value, Entry<K, V> next)
		{
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Entry()
		{
		}

		@Override
		public K getKey()
		{
			return key;
		}

		@Override
		public V getValue()
		{
			return value;
		}
	}

	public static void main(String[] args)
	{
		MyMap<String, Integer> map = new MyHashMap<>();
		map.put("a", 20);
		map.put("b", 30);
		map.put("c", 43);
		System.out.println(map.get("a"));
		System.out.println(map.get("b"));
		System.out.println(map.get("c"));

		// for (int i = 0; i < 300; i++)
		// {
		// map.put("test" + i, i);
		// System.out.println("放入:" + i);
		// try
		// {
		// Thread.sleep(100);
		// }
		// catch (InterruptedException e)
		// {
		// e.printStackTrace();
		// }
		// }
		//
		// for (int i = 0; i < 100; i++)
		// {
		// System.out.println(map.get("test" + i));
		// }
		// System.out.println("map的大小:" + map.size());

	}

}

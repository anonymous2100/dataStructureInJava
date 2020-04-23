package com.ctgu.string;

/**
 * 字符串处理
 * 
 * @author lh2
 * @date 2018年10月23日 下午3:35:53
 * @desc
 */
public class StringTest
{
	// 1、字符串反转（字符串以空格分隔）
	// 如 how are you today反转为today you are how
	// 思路：先对整个字符串进行反转，然后对每一个单词进行反转，例如：
	// 1、how are you today反转为yadot uoy era woH
	// 2、第1个单词yadot反转为today
	// 3、第2个单词uoy反转为you
	// 4、第3个单词era反转为are
	// 5、第4个单词woH反转为How
	public static String inverseWords(String words)
	{
		char[] ch = words.toCharArray();
		// 对整个字符串进行反转操作
		System.out.println("对整个字符串进行反转操作...");
		swap(ch, 0, ch.length - 1);

		// 对每个单词进行字符串反转操作
		int begin = 0;
		for (int i = 0; i < ch.length; i++)
		{
			if (Character.isWhitespace(ch[i]))
			{
				System.out.println("\n对每一个字符串进行反转操作...");
				swap(ch, begin, i - 1);
				begin = i + 1;
			}
		}
		swap(ch, begin, ch.length - 1);

		return new String(ch);
	}

	private static void swap(char[] ch, int begin, int end)
	{
		// System.out.println("begin=" + begin + ",end=" + end + ",字符数组交换之前：" + new String(ch));
		while (begin < end)
		{
			char temp = ch[end];
			ch[end] = ch[begin];
			ch[begin] = temp;
			begin++;
			end--;
			System.out.println("反转字符中...begin=" + ch[begin] + ",end=" + ch[end] + ",ch=" + new String(ch));
		}
		// System.out.println("begin=" + begin + ",end=" + end + ",字符数组交换之后：" + new String(ch));
	}

	// 2、判断两个字符串是否由相同字符组成

	// 3、删除字符串中的重复字符

	// 4、统计一行字符中有多少个单词

	// 5、按要求打印数组的排列情况

	// 6、输出字符串的所有组合

	public static void main(String[] args)
	{
		String test1 = "How are you today";
		System.out.println("\n字符串反转结果为：" + inverseWords(test1));

		// ...后面的几个方法有时间再写
	}

}

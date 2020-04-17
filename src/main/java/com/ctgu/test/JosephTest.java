// package com.ctgu.test;
//
// import java.util.ArrayList;
// import java.util.List;
//
//
//
/// **
// * 约瑟夫环问题： 已知n个人(以编号1，2，3...n分别表示)围坐在一张圆桌周围。<br>
// * 从编号为k的人开始报数，数到m的那个人出列;他的下一个人又从1开始报数，<br>
// * 数到m的那个人又出列; 此规律重复下去，直到圆桌周围的人全部出列 <br>
// */
// public class JosephTest
// {
// /**
// * 法一 思路： 用list集合实现链表循环: <br>
// * 先删除再添加-首元素成功变到尾部,而满足条件的直接删除不添加 <br>
// * 这样 一直循环到 list.size()==1,即得到答案
// */
// public static void method1(int n, int start, int m)
// {
// List<Integer> list = new ArrayList<Integer>();
// for (int i = 0; i < n; i++)
// {
// // 将 起始报数的编号start 放在list集合前面;因为删除操作始终操作最前面的元素
// if (start + i > n)
// {
// list.add(start + i - n);
// }
// else
// {
// list.add(start + i);
// }
// }
// // 用来记录报数的大小
// int num = 0;
// while (list.size() > 1)
// {
// for (int j = 0; j < m - 1; j++)
// {
// num++;
// // 不为 m的倍数 就自动 "续接"到 尾部
// list.add(list.remove(0));
// }
// num++;
// // 为 m的倍数 就删除
// System.out.print(list.remove(0) + ",");
// }
// System.out.println("\n最后的编号为" + list.get(0) + ",最终报数大小为：" + num + "\n");
// }
//
// /**
// * 法二 思路： 数组下标即为编号,数组内容用来记录是否被淘汰(boolean),这样还省去了删除操作
// */
// public static void method2(int n, int start, int m)
// {
// boolean[] arr = new boolean[n];
// for (int i = 0; i < arr.length; i++)
// {
// arr[i] = true;// 每个人 初始 都未被淘汰
// }
// int size = n;// 存储 未被淘汰人数 -用以中止循环
// int index = start - 1; // 第n个人 的数组下标为 n-1
// int count = 0; // 记录每个人报的数
// while (size > 1)
// {
// if (arr[index] == true)
// {
// count++; // 报数
// if (count % m == 0)
// { // 被淘汰
// arr[index] = false;
// size--;// 未淘汰总人数 减一
// System.out.print(index + 1 + ","); // 编号比数组下标多一
// }
// }
// if (index == arr.length - 1)
// {
// index = 0; // 防止数组下标越界
// }
// else
// {
// index++;
// }
// }
// for (int i = 0; i < arr.length; i++)
// {
// if (arr[i] == true)
// {
// System.out.println("\n最后一个人编号为：" + (i + 1) + " ,报数总大小为:" + count);
// }
// }
// }
//
// /**
// * 法三（数学函数 递归实现）：<br>
// * n个人报数,编号(假设编号从0开始与数组下标重合),每出一个人算一轮 <br>
// * 第一轮 编号为 (m-1)%n 的人 出去, <br>
// * 第二轮编号为 m%n的人报1, 若以m%n的人为起点(0),那么第二轮出去的人编号为 (m-1)%(n-1),<br>
// * 那么第二轮出去的人在 第一轮的编号为： (m+(m-1)%(n-1))%n <br>
// * 设 最后淘汰的人的编号为 Xn 那么 就有 Xn = (m + Xn-1)%n (※ Xn-1为Xn在上一轮的编号) <br>
// * 有了这个递推公式,我们就可以从最后一轮递推到第 n轮: <br>
// * X2 = (m+X1)%2; //倒数第二轮 n =2 最后一轮只剩下一个人,编号肯定为0,<br>
// * 即 X1=0 所以我们可以这么写： j = 0; //j为 最终留下来的人,<br>
// * 最后一轮编号为0 for(int i=2;i<=n;i++) //从倒数第二轮开始 j=(m+j)%i; <br>
// * 最终经过n轮后 j的编号 即为最初的编号
// */
// public static void method3(int n, int start, int m)
// {
// int j = 0;
// for (int i = 2; i < n; i++)
// { // 递归 递推到 第二轮淘汰时的编号;因为第一轮从start开始,所以公式需要作出改变
// j = (m + j) % i;
// }
// // 最后留下来的人 在第二轮淘汰时 的坐标 逆推 回第一轮 淘汰时的坐标
// // start-1是因为 此题 编号从1开始,原本编号从0开始时 公式为 j=(start+m+j)%n;
// j = ((start - 1) + m + j) % n;
// // 因为 题目要求 编号从1开始,那么最后答案+1即可
// System.out.println("\n法三答案:" + (++j));
// }
//
// public static void main(String[] args)
// {
// method1(9, 1, 5);
// method2(9, 1, 5);
// method3(9, 1, 5);
// }
// }

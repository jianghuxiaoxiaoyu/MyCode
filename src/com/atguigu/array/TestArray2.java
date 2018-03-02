package com.atguigu.array;

import java.util.Scanner;

/*
 * 数组：用来批量保存一类数据
 * 所谓数组，就是相同数据类型的元素按一定顺序排列的集合，
 * 就是把有限个类型相同的变量用一个名字命名，然后用编号区分他们，这个名字称为数组名，编号称为下标、索引。
 * 组成数组的各个变量称为数组的元素。
 * 
 * 例如：scores数组名
 * 		[0]、[1]。。。索引，下标
 * 		scores[0]、scores[1]。。。是元素
 * 思考：每个组长，把本组的学员的成绩登记，并统计最高分，平均分，并显式每个学员的成绩
 * 6个人
 */
public class TestArray2 {
	public static void main(String[] args) {
		int[] scores = new int[6];
		/*scores[0] = 78;
		scores[1] = 68;
		scores[2] = 88;
		scores[3] = 98;
		scores[4] = 74;
		scores[5] = 79;*/
		
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < scores.length; i++) {
			System.out.print("请输入第" + (i+1) + "个学员的成绩：");
			scores[i] = input.nextInt();
		}
		
		int max = scores[0];
		
		for (int i = 0; i < scores.length; i++) {
			if(max < scores[i]){
				max = scores[i];
			}
		}
		
		System.out.println("最高分：" + max);
		
		int sum = 0;
		for (int i = 0; i < scores.length; i++) {
			sum += scores[i];
		}
		System.out.println("总分：" + sum + "，平均分：" + sum / 6.0);
		
	}
}

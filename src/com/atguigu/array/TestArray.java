package com.atguigu.array;
/*
 * 数组：
 * 
 * 
 * 
 * 思考：每个组长，把本组的学员的成绩登记，并统计最高分，平均分，并显式每个学员的成绩
 * 6个人
 */
public class TestArray {
	public static void main(String[] args) {
		int score1 = 78;
		int score2 = 68;
		int score3 = 88;
		int score4 = 98;
		int score5 = 74;
		int score6 = 79;
		
		int max = score1 ;
		max = max > score2 ? max : score2;
		max = max > score3 ? max : score3;
		max = max > score4 ? max : score4;
		max = max > score5 ? max : score5;
		max = max > score6 ? max : score6;
		
		System.out.println("最高分：" + max);
		
		int sum = 0;
		sum += score1;
		sum += score2;
		sum += score3;
		sum += score4;
		sum += score5;
		sum += score6;
		
		System.out.println("总分：" + sum + "，平均分：" + sum / 6.0);
		
		
		
	}
}

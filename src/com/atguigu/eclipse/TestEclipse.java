package com.atguigu.eclipse;

import java.util.Scanner;

/*
 * 快捷键：
 * 
 * Ctrl + S：保存
 * Ctrl + X：剪切
 * Ctrl + C:复制
 * Ctrl + V：粘贴
 * Ctrl + Z:撤销
 * Ctrl + Y：恢复刚刚撤销的
 * 
 * Ctrl + A:全选
 * 
 * 复制一行：
 * 向上复制：Ctrl+Alt+向上键
 * 向下复制：Ctrl+Alt+向下键
 * 
 * 删除一行：Ctrl + D
 * 
 * 在光标的下一行开始编辑：Shift + 回车
 * 与上一行交换位置：Alt + 向上键
 * 与下一行交换位置：Alt + 向下键
 * 
 * 格式化：Ctrl + Shift + F
 * 
 * 单行注释：Ctrl + /  再按一次取消
 * 多行注释：Ctrl + Shift + /
 *     取消：Ctrl + Shift + \
 * 
 * 修复帮助：Ctrl + 1
 * 快速导包： Ctrl + Shift + O
 * 
 * 内容提示：Alt + /
 * 
 * 快速模板：
 * 主方法：main + Alt+ /
 * 打印语句：sysout + Alt + /
 * 循环：for + Alt + /
 * 
 * 自动生成源代码:Source菜单
 * 构造器：
 * get/set：
 */
public class TestEclipse {
	public static void main(String[] args) {
		
	}
}
class Person{
	private String name;
	private int age;
	private String school;
	private String major;
	
	public Person() {
		
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person(String name, int age, String school, String major) {
		this.name = name;
		this.age = age;
		this.school = school;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
}



package com.atguigu.eclipse;

import java.util.Scanner;

/*
 * ��ݼ���
 * 
 * Ctrl + S������
 * Ctrl + X������
 * Ctrl + C:����
 * Ctrl + V��ճ��
 * Ctrl + Z:����
 * Ctrl + Y���ָ��ոճ�����
 * 
 * Ctrl + A:ȫѡ
 * 
 * ����һ�У�
 * ���ϸ��ƣ�Ctrl+Alt+���ϼ�
 * ���¸��ƣ�Ctrl+Alt+���¼�
 * 
 * ɾ��һ�У�Ctrl + D
 * 
 * �ڹ�����һ�п�ʼ�༭��Shift + �س�
 * ����һ�н���λ�ã�Alt + ���ϼ�
 * ����һ�н���λ�ã�Alt + ���¼�
 * 
 * ��ʽ����Ctrl + Shift + F
 * 
 * ����ע�ͣ�Ctrl + /  �ٰ�һ��ȡ��
 * ����ע�ͣ�Ctrl + Shift + /
 *     ȡ����Ctrl + Shift + \
 * 
 * �޸�������Ctrl + 1
 * ���ٵ����� Ctrl + Shift + O
 * 
 * ������ʾ��Alt + /
 * 
 * ����ģ�壺
 * ��������main + Alt+ /
 * ��ӡ��䣺sysout + Alt + /
 * ѭ����for + Alt + /
 * 
 * �Զ�����Դ����:Source�˵�
 * ��������
 * get/set��
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



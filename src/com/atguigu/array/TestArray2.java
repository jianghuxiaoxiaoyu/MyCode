package com.atguigu.array;

import java.util.Scanner;

/*
 * ���飺������������һ������
 * ��ν���飬������ͬ�������͵�Ԫ�ذ�һ��˳�����еļ��ϣ�
 * ���ǰ����޸�������ͬ�ı�����һ������������Ȼ���ñ���������ǣ�������ֳ�Ϊ����������ų�Ϊ�±ꡢ������
 * �������ĸ���������Ϊ�����Ԫ�ء�
 * 
 * ���磺scores������
 * 		[0]��[1]�������������±�
 * 		scores[0]��scores[1]��������Ԫ��
 * ˼����ÿ���鳤���ѱ����ѧԱ�ĳɼ��Ǽǣ���ͳ����߷֣�ƽ���֣�����ʽÿ��ѧԱ�ĳɼ�
 * 6����
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
			System.out.print("�������" + (i+1) + "��ѧԱ�ĳɼ���");
			scores[i] = input.nextInt();
		}
		
		int max = scores[0];
		
		for (int i = 0; i < scores.length; i++) {
			if(max < scores[i]){
				max = scores[i];
			}
		}
		
		System.out.println("��߷֣�" + max);
		
		int sum = 0;
		for (int i = 0; i < scores.length; i++) {
			sum += scores[i];
		}
		System.out.println("�ܷ֣�" + sum + "��ƽ���֣�" + sum / 6.0);
		
	}
}

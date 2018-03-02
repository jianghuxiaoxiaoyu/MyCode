package com.atguigu.array;
/*
 * 一、
 * 1、如何声明一个数组？
 * 格式：
 * 数组类型   数组名;
 * 或
 * 元素的类型[] 数组名;
 * 
 * 补充：元素的类型可以是任意类型，包括基本数据类型和引用数据类型
 * 
 * 2、数组的初始化
 * 如何创建数组对象
 * （1）动态初始化
 * 格式：
 * 数组名 = new 元素的类型[数组的长度/数组元素的个数];
 * 说明：
 * 初始化数组时，必须确定数组的长度
 * 数组的长度一旦确定，就不可以修改
 * 
 * 
 * 3、理解数组
 * （1）数组类型：引用数据类型
 * （2）数组类型的变量是赋值一个对象，数组对象，所以使用new创建数组对象
 * （3）数组对象存储在堆中
 * （4）数组的元素可以看成数组对象的成员变量
 * 		数组的元素有默认值：byte,short,int,long：0
 * 					  float,double:0.0
 * 					  char:\u0000
 * 					  boolean:false
 * 					 引用数据类型：null
 * 
 * 4、数组元素的表示方式
 * 数组名[下标]表示一个元素
 * 下标的范围：[0,数组的长度)   或 [0,数组的长度-1]
 * 
 * 5、数组元素的赋值
 * 数组名[下标] = 值;
 * 
 * 6、访问，使用数组元素的值
 * 例如：System.out.println(数组名[下标]);
 * 	   if(max < 数组名[下标]){
 * 		}
 * 
 * 7、数组的长度的表示方式
 * 数组名.length
 * 
 * 8、数组的遍历
 * for循环
 * for(int i=0; i<数组名.length; i++){
 * 	  数组名[i]代表一个元素
 * }
 * 
 * 成员变量与局部变量的区别？
 * 其中一点：初始值的获取方式不同
 * 
 */
public class TestMyArray {

	public static void main(String[] args) {
		//1、声明数组
		//数组的类型是int[]，是一个引用数据类型
		//元素的类型是int
		int[] array;
		//2、数组的初始化
		array = new int[5];
		
		for (int i = 0; i < array.length ; i++) {
			System.out.println(array[i]);
		}
		
		//声明数组
		double[] ds; //元素是double，存的是一组double
		String[] strings;//元素是String，存的是一组字符串对象
		Student[] students;//元素是Student，存的是一组学生对象
		
	}

}
class Student{
	
}
package com.atguigu.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

import org.junit.Test;

public class TestLocalDate {
	
	@Test
	public void testLocalDate3(){
		LocalDate date = LocalDate.of(2000, 9, 9);
		boolean leapYear = date.isLeapYear();
		System.out.println(leapYear);
	/*	
		LocalDate date2 = LocalDate.of(2010, 1, 1);
		boolean before = date2.isBefore(date);
		System.out.println(before);*/
		
		LocalDate newDate = date.withDayOfMonth(10);//把日修改为10号
		System.out.println(newDate);
		System.out.println(date);
		
		LocalDate plusDays = date.plusDays(10);//加10天
		System.out.println(plusDays);
		
		LocalDate plusWeeks = date.plusWeeks(2);//加两个星期
		System.out.println(plusWeeks);
		
		LocalDate minusDays = date.minusDays(10);//减10天
		System.out.println(minusDays);
	}
	
	@Test
	public void testLocalDate2(){
//		LocalDate date = LocalDate.now(ZoneId.of("America/New_York"));
		LocalDate date = LocalDate.of(1999, 9, 9);
		System.out.println(date);
	}
	
	@Test
	public void testLocalDate(){
		LocalDate date = LocalDate.now();
		System.out.println(date);//2017-09-30
		
		int year = date.getYear();
		System.out.println("年：" + year);
		
		Month month = date.getMonth();//把月份封装成对象，月份的名称，有月份数值
		System.out.println(month);
		System.out.println(month.getValue());
		
		int day = date.getDayOfMonth();
		System.out.println(day);
		
		DayOfWeek week = date.getDayOfWeek();
		System.out.println(week);
		System.out.println(week.getValue());
	}
}

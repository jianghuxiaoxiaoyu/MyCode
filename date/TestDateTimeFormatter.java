package com.atguigu.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.Test;

public class TestDateTimeFormatter {
	@Test
	public void testDateTimeFormatter3(){
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		System.out.println(df.format(LocalDateTime.now()));
		
		//FULL��֧��
		//LONG:2017��9��30�� ����04ʱ45��20��
		//SHORT:17-9-30 ����4:45
		DateTimeFormatter df3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		System.out.println(df3.format(LocalDateTime.now()));
	}
	
	@Test
	public void testDateTimeFormatter2(){
		DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");
		String format = df.format(LocalTime.now());
		System.out.println(format);
		
		//��֧��FULL
		//֧�֣�LONG  ����04ʱ43��08��
		//SHORT  ����4:42
		DateTimeFormatter df3 = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG);
		System.out.println(df3.format(LocalTime.now()));
	}
	
	@Test
	public void testDateTimeFormatter(){
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy��MM��dd��");
		String format = df.format(LocalDate.now());
		System.out.println(format);
		
		DateTimeFormatter df2 = DateTimeFormatter.ISO_DATE;
		System.out.println(df2.format(LocalDate.now()));
		
		//FormatStyle.FULL 2017��9��30�� ������
		//FormatStyle.SHORT 17-9-30
		//FormatStyle.LONG  2017��9��30��
		DateTimeFormatter df3 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
		System.out.println(df3.format(LocalDate.now()));
	}
}

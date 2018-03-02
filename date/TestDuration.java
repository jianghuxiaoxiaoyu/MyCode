package com.atguigu.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;

import org.junit.Test;

public class TestDuration {
	
	
	@Test
	public void testPeriod(){
		Period p = Period.between(LocalDate.now(), LocalDate.of(2018, 1, 1));
		System.out.println(p.getYears());
		System.out.println(p.getMonths());
		System.out.println(p.getDays());
	}
	
	@Test
	public void testDuration(){
		//ʱ���
		//Duration d = Duration.between(LocalTime.now(), LocalTime.now(ZoneId.of("America/New_York")));
		Duration d = Duration.between(LocalTime.of(12, 1, 1), LocalTime.of(13, 56, 23));
		long hours = d.toHours();//�����Сʱ      ֻ������Сʱ
		System.out.println(hours);
		
		long minutes = d.toMinutes();//һ�������  ֻ�����ķ���
		System.out.println(minutes);
		
		long seconds = d.getSeconds();//һ�����  
		System.out.println(seconds);
	}
}

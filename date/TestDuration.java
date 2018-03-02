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
		//时间差
		//Duration d = Duration.between(LocalTime.now(), LocalTime.now(ZoneId.of("America/New_York")));
		Duration d = Duration.between(LocalTime.of(12, 1, 1), LocalTime.of(13, 56, 23));
		long hours = d.toHours();//差多少小时      只计正的小时
		System.out.println(hours);
		
		long minutes = d.toMinutes();//一共差几分钟  只计正的分钟
		System.out.println(minutes);
		
		long seconds = d.getSeconds();//一共差几秒  
		System.out.println(seconds);
	}
}

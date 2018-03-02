package com.atguigu.date;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.junit.Test;

public class TestLocalDateTime {
	@Test
	public void testLocalDateTime(){
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
	}
	
	@Test
	public void testLocalTime(){
		LocalTime time = LocalTime.now();
		System.out.println(time);
		
		LocalTime time2 = LocalTime.now(ZoneId.of("America/New_York"));
		System.out.println(time2);
		
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
		
		System.out.println(hour);
		System.out.println(minute);
		System.out.println(second);
		
		LocalTime withHour = time.withHour(17);
		System.out.println(withHour);
		
		LocalTime plusHours = time.plusHours(12);
		System.out.println(plusHours);
		
		LocalTime minusHours = time.minusHours(8);
		System.out.println(minusHours);
	}
}

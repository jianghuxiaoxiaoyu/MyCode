package com.atguigu.date;

import java.util.Date;
import java.util.Set;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

/*
 * JDK1.8日期时间API：
	java.time - 包含值对象的基础包
	java.time.chrono- 提供对不同的日历系统的访问
	java.time.format- 格式化和解析时间和日期
	java.time.temporal- 包括底层框架和扩展特性
	java.time.zone- 包含时区支持的类
 
 *
 * java.time包
 * 1、Clock
 *  获取对象：systemDefaultZone()
 *   	  system(ZoneId)
 *   
 * 2、ZonedDateTime
 * 	now()：当前时区
 *  now(Clock)
 *  now(ZoneId)
 * 
 * 3、ZoneId
 * (1)ZoneId.of("id")
 * (2)getAvailableZoneIds()
 */
public class TestDateTime {
	@Test
	public void testZone(){
		//String是集合元素的类型
		Set<String> set = ZoneId.getAvailableZoneIds();
		//for(元素类型  元素名  ： 数组/集合名)
		for (String string : set) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testZoneTime(){
		ZonedDateTime now = ZonedDateTime.now();
		System.out.println(now);
		
		ZoneId of = ZoneId.of("America/New_York");
		Clock clock = Clock.system(of);
		ZonedDateTime now1 = ZonedDateTime.now(clock);
		System.out.println(now1);
		
		ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		System.out.println(now2);
	}
	
	@Test
	public void testClock2(){
		ZoneId of = ZoneId.of("America/New_York");
		Clock clock = Clock.system(of);
		System.out.println(clock.millis());
		
		System.out.println(System.currentTimeMillis());
	}
	
	@Test
	public void testClock(){
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock);
		
		ZoneId zone = clock.getZone();
		System.out.println(zone);
		
		long millis = clock.millis();
		System.out.println(millis);
		
		
		//旧
		System.out.println(System.currentTimeMillis());
		Date date = new Date();
		long time = date.getTime();
		System.out.println(time);
	}
}

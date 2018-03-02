package com.atguigu.date;

import java.util.Date;
import java.util.Set;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

/*
 * JDK1.8����ʱ��API��
	java.time - ����ֵ����Ļ�����
	java.time.chrono- �ṩ�Բ�ͬ������ϵͳ�ķ���
	java.time.format- ��ʽ���ͽ���ʱ�������
	java.time.temporal- �����ײ��ܺ���չ����
	java.time.zone- ����ʱ��֧�ֵ���
 
 *
 * java.time��
 * 1��Clock
 *  ��ȡ����systemDefaultZone()
 *   	  system(ZoneId)
 *   
 * 2��ZonedDateTime
 * 	now()����ǰʱ��
 *  now(Clock)
 *  now(ZoneId)
 * 
 * 3��ZoneId
 * (1)ZoneId.of("id")
 * (2)getAvailableZoneIds()
 */
public class TestDateTime {
	@Test
	public void testZone(){
		//String�Ǽ���Ԫ�ص�����
		Set<String> set = ZoneId.getAvailableZoneIds();
		//for(Ԫ������  Ԫ����  �� ����/������)
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
		
		
		//��
		System.out.println(System.currentTimeMillis());
		Date date = new Date();
		long time = date.getTime();
		System.out.println(time);
	}
}

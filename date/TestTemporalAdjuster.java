package com.atguigu.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/*
 * 
 */
public class TestTemporalAdjuster {

	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		TemporalAdjuster t = TemporalAdjusters.next(DayOfWeek.SATURDAY);
		LocalDate newDate = now.with(t);
		System.out.println(newDate);
		
		LocalDate with = newDate.with(t);
		System.out.println(with);
	}

}

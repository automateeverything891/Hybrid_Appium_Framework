package com.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


public class CalculateDaysUsingJava {

	
	public long getWeekDayCount(String startDate, String  endDate) throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(simple.parse(startDate));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd");
		cal1.setTime(simple1.parse(endDate));
		int workDays=0;
		int weekDays=0;
		do {
		    if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		        ++workDays;
		    }
		    else {
		    	++weekDays;
		    }
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.getTimeInMillis() <=cal1.getTimeInMillis());
		return weekDays;
	   
	}
	public long getWorkDayCount(String startDate, String  endDate) throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(simple.parse(startDate));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd");
		cal1.setTime(simple1.parse(endDate));
		int workDays=0;
		
		do {
		    if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		        ++workDays;
		    }
		   
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.getTimeInMillis() <= cal1.getTimeInMillis());
		return workDays;
	   
	}
 public long getAllDays(String startDate, String  endDate) throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(simple.parse(startDate));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd");
		cal1.setTime(simple1.parse(endDate));
		int workDays=0;
		int weekDays=0;
		
		do {
		    if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		        ++workDays;
		    }
		    else {
		    	++weekDays;
		    }
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.getTimeInMillis() <= cal1.getTimeInMillis());
		return workDays+weekDays+1;
	   
	}
	public static void main(String[] args) throws ParseException {
		
		CalculateDaysUsingJava cal = new CalculateDaysUsingJava();
		System.out.println(cal.getWorkDayCount("2020-12-14","2021-01-14"));
		System.out.println(cal.getWeekDayCount("2020-12-18","2021-01-16"));
		System.out.println(cal.getAllDays("2020-12-15","2021-01-13"));
		
		LocalDate local = LocalDate.now();
		System.out.println(local.getMonthValue());
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
		System.out.println(dt);
		
		SimpleDateFormat simple7 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		System.out.println(Calendar.MONTH);
		cal1.set(Calendar.MONTH, cal1.get(Calendar.MONTH)+1);
		System.out.println(simple7.format(cal1.getTime()));
		System.out.println(simple7.format(dt));
		
	}
}

package com.ieven.ext.time;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeUtilTest {
	
	private TimeUtil util = new TimeUtil();
	
	@Test
	public void testTimestampToDate(){
		System.out.println(util.timestampToDate(1473332810, "yyyy-MM-dd HH:mm:ss"));
		assertEquals("2016-09-08 19:06:50",util.timestampToDate(1473332810, "yyyy-MM-dd HH:mm:ss"));
		assertEquals("2016-09-08 19:06:50",util.timestampToDate(1473332810));
		
	}
	
}

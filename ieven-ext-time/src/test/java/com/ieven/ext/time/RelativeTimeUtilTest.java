package com.ieven.ext.time;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.ieven.ext.time.exception.RelativeTimeException;

/**
 * 相对时间计算辅助类
 * 
 * @author lichong
 *
 */
public class RelativeTimeUtilTest {
	RelativeTimeUtil util = new RelativeTimeUtil();

	@Test
	public void testPlus() {
		String express = "+1 +1 +10 +1";
		try {
			System.out.println(util.getLocalDateTime(express));
			LocalDateTime testTime, utilTime;
			testTime = LocalDateTime.now().plusDays(1).plusHours(10).plusMinutes(1).plusSeconds(1);
			utilTime = util.getLocalDateTime(express);
			assertEquals(testTime.getSecond(),utilTime.getSecond());
			assertEquals(testTime.getMinute(),utilTime.getMinute());
			assertEquals(testTime.getHour(),utilTime.getHour());
			assertEquals(testTime.getDayOfMonth(),utilTime.getDayOfMonth());
			assertEquals(testTime.getDayOfWeek(),utilTime.getDayOfWeek());
			assertEquals(testTime.getDayOfYear(),utilTime.getDayOfYear());
		} catch (RelativeTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testMinus() {
		String express = "-1 -10 -10 -1";
		try {
			System.out.println(util.getLocalDateTime(express));
			LocalDateTime testTime, utilTime;
			testTime = LocalDateTime.now().minusDays(1).minusHours(10).minusMinutes(10).minusSeconds(1);
			utilTime = util.getLocalDateTime(express);
			assertEquals(testTime.getSecond(),utilTime.getSecond());
			assertEquals(testTime.getMinute(),utilTime.getMinute());
			assertEquals(testTime.getHour(),utilTime.getHour());
			assertEquals(testTime.getDayOfMonth(),utilTime.getDayOfMonth());
			assertEquals(testTime.getDayOfWeek(),utilTime.getDayOfWeek());
			assertEquals(testTime.getDayOfYear(),utilTime.getDayOfYear());
		} catch (RelativeTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testNomal() {
		String express = "1 10 10 1";
		try {
			System.out.println(util.getLocalDateTime(express));
			LocalDateTime testTime, utilTime;
			testTime = LocalDateTime.now().withDayOfMonth(1).withHour(10).withMinute(10).withSecond(1);
			utilTime = util.getLocalDateTime(express);
			assertEquals(testTime.getSecond(),utilTime.getSecond());
			assertEquals(testTime.getMinute(),utilTime.getMinute());
			assertEquals(testTime.getHour(),utilTime.getHour());
			assertEquals(testTime.getDayOfMonth(),utilTime.getDayOfMonth());
			assertEquals(testTime.getDayOfWeek(),utilTime.getDayOfWeek());
			assertEquals(testTime.getDayOfYear(),utilTime.getDayOfYear());
		} catch (RelativeTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUndo() {
		String express = "* * * *";
		try {
			System.out.println(util.getLocalDateTime(express));
			LocalDateTime testTime, utilTime;
			testTime = LocalDateTime.now();
			utilTime = util.getLocalDateTime(express);
			assertEquals(testTime.getSecond(),utilTime.getSecond());
			assertEquals(testTime.getMinute(),utilTime.getMinute());
			assertEquals(testTime.getHour(),utilTime.getHour());
			assertEquals(testTime.getDayOfMonth(),utilTime.getDayOfMonth());
			assertEquals(testTime.getDayOfWeek(),utilTime.getDayOfWeek());
			assertEquals(testTime.getDayOfYear(),utilTime.getDayOfYear());
		} catch (RelativeTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

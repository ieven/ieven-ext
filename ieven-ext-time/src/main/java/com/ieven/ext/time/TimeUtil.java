package com.ieven.ext.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 时间转换工具类
 * 
 * @author lichong
 *
 */
public class TimeUtil {

	/**
	 * 将时间戳转换为指定格式时间
	 * 
	 * @param timestamp
	 * @param format
	 * @return
	 */
	public String timestampToDate(long timestamp, String format) {
		LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
		return dateTime.format(DateTimeFormatter.ofPattern(format));
	}

	/**
	 * 将时间戳转换为时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @param format
	 * @return
	 */
	public String timestampToDate(long timestamp) {
		return timestampToDate(timestamp, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将时间戳转换为localDateTime
	 * 
	 * @param timestamp
	 * @return
	 */
	public LocalDateTime timestampToLocalDateTime(long timestamp) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		return LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
	}
}

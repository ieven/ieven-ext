package com.ieven.ext.time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 专门处理mysql时间问题工具类
 * @author lichong
 *
 */
public class MysqlTimeUtil {

	/**
	 * 将mysql时间转换成localdateTime，格式为yyyy-MM-dd HH:mm:ss
	 * @param mysqlTime
	 * @return
	 */
	public LocalDateTime getLocalDateTimeFromMysql(String mysqlTime){
		mysqlTime = mysqlTime.substring(0, mysqlTime.length()-2);
		return LocalDateTime.parse(mysqlTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 将mysql时间转换成时间戳
	 * @param mysqlTime
	 * @return
	 */
	public Long getTimestampFromMysql(String mysqlTime){
		return getLocalDateTimeFromMysql(mysqlTime).atZone(ZoneOffset.systemDefault()).toEpochSecond();
	}
}

package com.ieven.ext.time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.ieven.ext.time.exception.RelativeTimeException;

/**
 * 相对时间计算工具类 +：推迟 -：提前 无符号为具体时间 从左到右为：秒、分、时、天
 * 
 * @author lichong
 *
 */
public class RelativeTimeUtil {

	/**
	 * 根据表达式计算相对时间戳
	 * 
	 * @param expression
	 * @return
	 * @throws RelativeTimeException
	 */
	public long getTimestamp(String expression) throws RelativeTimeException {
		// 根据系统时区返回对应时区时间戳
		return getLocalDateTime(expression).atZone(ZoneOffset.systemDefault()).toEpochSecond();
	}

	/**
	 * 根据表达式计算相对时间戳
	 * 
	 * @param dateTime
	 * @param expression
	 * @return
	 * @throws RelativeTimeException
	 */
	public long getTimestamp(LocalDateTime dateTime, String expression) throws RelativeTimeException {
		return getLocalDateTime(dateTime, expression).atZone(ZoneOffset.systemDefault()).toEpochSecond();
	}

	/**
	 * 根据表达式计算相对时间
	 * 
	 * @param dateTime
	 * @param expression
	 * @return
	 * @throws RelativeTimeException
	 */
	public LocalDateTime getLocalDateTime(LocalDateTime dateTime, String expression) throws RelativeTimeException {
		if (dateTime == null) {
			dateTime = LocalDateTime.now();
		}
		try {
			// 首先将表达式按照空格分割
			String[] expreTemp = expression.split(" ");
			// 处理秒位
			if (!expreTemp[0].equals("*")) {
				switch (expreTemp[0].charAt(0)) {
				case '+':
					dateTime = dateTime.plusSeconds(Integer.parseInt(expreTemp[0].substring(1)));
					break;
				case '-':
					dateTime = dateTime.minusSeconds(Integer.parseInt(expreTemp[0].substring(1)));
					break;
				default:
					dateTime = dateTime.withSecond(Integer.parseInt(expreTemp[0]));
					break;
				}
			}
			// 处理分位
			if (!expreTemp[1].equals("*")) {
				switch (expreTemp[1].charAt(0)) {
				case '+':
					dateTime = dateTime.plusMinutes(Integer.parseInt(expreTemp[1].substring(1)));
					break;
				case '-':
					dateTime = dateTime.minusMinutes(Integer.parseInt(expreTemp[1].substring(1)));
					break;
				default:
					dateTime = dateTime.withMinute(Integer.parseInt(expreTemp[1]));
					break;
				}
			}
			// 处理小时位
			if (!expreTemp[2].equals("*")) {
				switch (expreTemp[2].charAt(0)) {
				case '+':
					dateTime = dateTime.plusHours(Integer.parseInt(expreTemp[2].substring(1)));
					break;
				case '-':
					dateTime = dateTime.minusHours(Integer.parseInt(expreTemp[2].substring(1)));
					break;
				default:
					dateTime = dateTime.withHour(Integer.parseInt(expreTemp[2]));
					break;
				}
			}
			// 处理天位
			if (!expreTemp[3].equals("*")) {
				switch (expreTemp[3].charAt(0)) {
				case '+':
					dateTime = dateTime.plusDays(Integer.parseInt(expreTemp[3].substring(1)));
					break;
				case '-':
					dateTime = dateTime.minusDays(Integer.parseInt(expreTemp[3].substring(1)));
					break;
				default:
					dateTime = dateTime.withDayOfMonth(Integer.parseInt(expreTemp[3]));
					break;
				}
			}
			return dateTime;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RelativeTimeException("相对时间计算错误，请检查表达式是否正确!", e);
		}
	}

	/**
	 * 根据表达式计算相对时间
	 * 
	 * @param expression
	 * @return
	 * @throws RelativeTimeException
	 */
	public LocalDateTime getLocalDateTime(String expression) throws RelativeTimeException {
		return getLocalDateTime(null, expression);
	}

}

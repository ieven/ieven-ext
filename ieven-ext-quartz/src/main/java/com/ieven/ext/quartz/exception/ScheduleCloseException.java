package com.ieven.ext.quartz.exception;

import com.ieven.ext.util.excepiton.NestedRuntimeException;

/**
 * 任务调度初始化异常
 * @author lichong
 *
 */
public class ScheduleCloseException extends NestedRuntimeException{

	public ScheduleCloseException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

	public ScheduleCloseException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}

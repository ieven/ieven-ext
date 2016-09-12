package com.ieven.ext.time.exception;

import com.ieven.ext.util.excepiton.NestedCheckException;

/**
 * 相对时间计算异常
 * @author lichong
 *
 */
public class RelativeTimeException extends NestedCheckException{

	public RelativeTimeException(String msg, Throwable e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public RelativeTimeException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}

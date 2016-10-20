package com.ieven.ext.echarts.exception;

import com.ieven.ext.util.excepiton.NestedCheckException;

/**
 * 操作系统类型不支持异常
 * 
 * @author lichong
 *
 */
public class OSTypeUnsupportException extends NestedCheckException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OSTypeUnsupportException(String msg, Throwable e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public OSTypeUnsupportException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}

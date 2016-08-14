package com.ieven.ext.util.excepiton;

/**
 * 初始化对象异常错误
 * @author lichong
 *
 */
public class InstanceException extends NestedRuntimeException{

	public InstanceException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}
	
	public InstanceException(String msg)
	{
		super(msg);
	}
}

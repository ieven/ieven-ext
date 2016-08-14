package com.ieven.ext.util.excepiton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义检查异常，项目所有异常的父类
 * @author lichong
 *
 */
public abstract class NestedCheckException extends Exception{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 构造函数继承Exception的构造方法
	 * @param msg 抛出的异常信息
	 */
	public NestedCheckException(String msg)
	{
		super(msg);
		logger.error(msg,this);
	}
	
	/**
	 * 构造函数继承Exception的构造方法
	 * @param msg 抛出的异常信息
	 * @param e 嵌套的异常
	 */
	public NestedCheckException(String msg,Throwable e)
	{
		super(msg, e);
		logger.error(msg,e);
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return buildMessage(super.getMessage(), getCause());
	}
	
	/**
	 * 寻找引发此异常的最根本异常
	 * @return
	 */
	public Throwable getRootCause() {
		Throwable rootCause = null;
		Throwable cause = getCause();
		while (cause != null && cause != rootCause) {
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause;
	}
	
	/**
	 * 获取最具体的异常，如果有更深异常则返回更深异常，没有则返回当前异常
	 * @return
	 */
	public Throwable getMostSpecificCause() {
		Throwable rootCause = getRootCause();
		return (rootCause != null ? rootCause : this);
	}
	
	/**
	 * 检查此异常中是否嵌套了入参异常，如：未找到文件异常等
	 * @param exType
	 * @return
	 */
	public boolean contains(Class exType) {
		if (exType == null) {
			return false;
		}
		if (exType.isInstance(this)) {
			return true;
		}
		Throwable cause = getCause();
		if (cause == this) {
			return false;
		}
		if (cause instanceof NestedRuntimeException) {
			return ((NestedRuntimeException) cause).contains(exType);
		}
		else {
			while (cause != null) {
				if (exType.isInstance(cause)) {
					return true;
				}
				if (cause.getCause() == cause) {
					break;
				}
				cause = cause.getCause();
			}
			return false;
		}
	}
	
	/**
	 * 绑定一个异常信息，并将引起此异常的异常打印
	 * @param message
	 * @param cause
	 * @return
	 */
	private String buildMessage(String message, Throwable cause) {
		if (cause != null) {
			StringBuilder sb = new StringBuilder();
			if (message != null) {
				sb.append(message).append("; ");
			}
			sb.append("嵌套的异常为 ").append(cause);
			return sb.toString();
		}
		else {
			return message;
		}
	}
	
}

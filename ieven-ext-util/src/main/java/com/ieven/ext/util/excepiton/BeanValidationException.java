package com.ieven.ext.util.excepiton;

/**
 * bean属性校验异常
 * 
 * @author lichong
 *
 */
public class BeanValidationException extends NestedCheckException {

	public BeanValidationException(String msg, Throwable e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public BeanValidationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}

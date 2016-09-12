package com.ieven.ext.mongo.exception;

import com.ieven.ext.util.excepiton.NestedRuntimeException;

/**
 * mongo初始化异常
 * @author lichong
 *
 */
public class MongoInitException extends NestedRuntimeException{

	public MongoInitException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

	public MongoInitException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}

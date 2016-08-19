package com.ieven.ext.http.exception;

import com.ieven.ext.util.excepiton.NestedCheckException;

/**
 * http协议异常
 * @author lichong
 *
 */
public class HttpProtocolException extends NestedCheckException{

	public HttpProtocolException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

	public HttpProtocolException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}

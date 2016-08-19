package com.ieven.ext.http.exception;

import com.ieven.ext.util.excepiton.NestedCheckException;

/**
 * 当上送请求（httpclient）返回异常时抛出此异常，并调用报警接口报警
 * @author lichong
 *
 */
public class ResponseException extends NestedCheckException{

	public ResponseException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

	public ResponseException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}

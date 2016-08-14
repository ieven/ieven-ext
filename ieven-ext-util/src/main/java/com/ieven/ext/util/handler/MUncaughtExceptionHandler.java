package com.ieven.ext.util.handler;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 线程池多线程情况下runtimeException无法抛出，故此使用如下方式
 * @author lichong
 *
 */
public class MUncaughtExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		try {
			beforeThrowException(t, e);
			throw e;
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 在抛出异常之前做些操作
	 * @param t 发生异常的线程
	 * @param e 线程抛出的异常
	 */
	public void beforeThrowException(Thread t, Throwable e)
	{
		
	}
	
}

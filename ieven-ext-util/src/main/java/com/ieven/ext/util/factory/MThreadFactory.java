package com.ieven.ext.util.factory;

import java.util.concurrent.ThreadFactory;

import com.ieven.ext.util.handler.MUncaughtExceptionHandler;

/**
 * IOMP threadFactory
 * @author lichong
 *
 */
public class MThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(r);
		thread.setUncaughtExceptionHandler(new MUncaughtExceptionHandler());
		return thread;
	}

}

package com.ieven.ext.util.excepiton;

/**
 * 配置文件异常
 * @author lichong
 *
 */
public class ConfigException extends NestedRuntimeException{

	public ConfigException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public ConfigException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}

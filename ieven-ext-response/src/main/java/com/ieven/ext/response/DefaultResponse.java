package com.ieven.ext.response;

import com.ieven.ext.response.conf.ResponseConfigProxy;

/**
 * 默认返回，结果为{code:code msg:msg}
 * @author lichong
 *
 */
public class DefaultResponse implements MResponse{
	/**
	 * 获取配置字典
	 */
	protected static final ResponseConfigProxy codeDic = new ResponseConfigProxy();
	
	private String msg;
	private int code;

	public DefaultResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public DefaultResponse(int code) {
		this.code = code;
		this.msg = (String) codeDic.getConfigMap().get(code+"");
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{code:"+code+" msg:"+msg+"}";
	}
}

package com.ieven.ext.util.io;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * 注册统一接口
 * @author lichong
 *
 */
public interface RegisterConfig {

	/**
	 * 注册
	 */
	public void register(List<Element> list);
	
	/**
	 * 注册之前
	 */
	public void beforeRegister(List<Element> list);
	
	/**
	 * 注册之后
	 */
	public void afterRegister(List<Element> list);
	
	/**
	 * 获取配置文件内容
	 * @return
	 */
	public Map getConfigMap();
}

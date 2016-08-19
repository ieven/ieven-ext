package com.ieven.ext.util.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.po.RegistType;
import com.ieven.ext.util.util.StringUtils;
import com.ieven.ext.util.util.xml.XMLUtilWithClasspathResource;

/**
 * 注册配置文件抽象类，提供三种解析类型 RegistType.SPECIFIC（读取给定路径下一个配置文件中的内容（路径为具体一个文件））
 * RegistType.MULTIPLE（读取给定路径下所有配置文件中的内容（路径为配置文件夹路径））
 * RegistType.SPECIFIC_XPATH（读取给定路径下一个配置文件中的内容（路径为具体一个文件）并使用xpath进行筛选
 * 此种解析需要提供带有xpth的构造函数）
 * 
 * @author lichong
 *
 */
public abstract class AbstractRegisterConfigWithClasspathResource implements RegisterConfig {

	protected Map map;

	public AbstractRegisterConfigWithClasspathResource(RegistType registType, String XPath) {
		List<Element> list = null;
		if (registType.equals(RegistType.SPECIFIC)) {
			list = XMLUtilWithClasspathResource.getGivenConfigFileNodes(getConfigPath());
		} else if (registType.equals(RegistType.MULTIPLE)) {
			list = XMLUtilWithClasspathResource.getConfigFileNodes(getConfigPath());
		} else if (registType.equals(RegistType.SPECIFIC_XPATH)) {
			if (!StringUtils.hasText(XPath)) {
				throw new ConfigException("xpath路径不能为空");
			}
			list = XMLUtilWithClasspathResource.getNodesByXPath(getConfigPath(), XPath);
		} else {
			throw new ConfigException("无此类解析类型，请检查注册类型是否正确");
		}
		this.map = new HashMap(list.size());
		beforeRegister(list);
		register(list);
		afterRegister(list);
	}

	public AbstractRegisterConfigWithClasspathResource(RegistType registType) {
		List<Element> list = null;
		if (registType.equals(RegistType.SPECIFIC)) {
			list = XMLUtilWithClasspathResource.getGivenConfigFileNodes(getConfigPath());
		} else if (registType.equals(RegistType.MULTIPLE)) {
			list = XMLUtilWithClasspathResource.getConfigFileNodes(getConfigPath());
		} else if (registType.equals(RegistType.SPECIFIC_XPATH)) {
			throw new ConfigException("此类解析类型需要提供xpth入参，请使用其他构造函数");
		} else {
			throw new ConfigException("无此类解析类型，请检查注册类型是否正确");
		}
		this.map = new HashMap(list.size());
		beforeRegister(list);
		register(list);
		afterRegister(list);
	}

	/**
	 * 设定读取配置文件位置
	 * 
	 * @return
	 */
	public abstract String getConfigPath();

	@Override
	public Map getConfigMap() {
		// TODO Auto-generated method stub
		return this.map;
	}

	@Override
	public void beforeRegister(List<Element> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRegister(List<Element> list) {
		// TODO Auto-generated method stub

	}

}

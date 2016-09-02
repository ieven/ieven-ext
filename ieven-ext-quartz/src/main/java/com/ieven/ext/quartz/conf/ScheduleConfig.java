package com.ieven.ext.quartz.conf;

import java.io.IOException;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.io.AbstractRegisterConfigWithClasspathResource;
import com.ieven.ext.util.io.ClassPathResource;
import com.ieven.ext.util.io.Resource;
import com.ieven.ext.util.po.RegistType;
import com.ieven.ext.util.util.xml.XMLUtilWithClasspathResource;

/**
 * 读取配置文件schedule.xml工具类
 * 
 * @author lichong
 *
 */
public class ScheduleConfig extends AbstractRegisterConfigWithClasspathResource {

	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/ScheduleConf/Schedule.xml";
	
	private static final String CONFIG_FILE_NAME = "Schedule.xml";

	public ScheduleConfig() {
		// 用来设定需要进行的注册类型
		this(RegistType.SPECIFIC_XPATH, "content/leaf");
	}

	private ScheduleConfig(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for (Element element : list) {
			if (element.attributeValue("key") == null || element.attributeValue("key").equals("")) {
				throw new ConfigException(CONFIG_FILE_NAME + "中key标签为空");
			} else if (element.attributeValue("value") == null || element.attributeValue("value").equals("")) {
				throw new ConfigException(CONFIG_FILE_NAME + "中value标签为空");
			}
			try {
				this.map.put(element.attributeValue("key"), element.attributeValue("value"));
			} catch (Exception e) {
				// TODO: handle exception
				throw new ConfigException(CONFIG_FILE_NAME + "中key：" + element.attributeValue("key") + "或value: "
						+ element.attributeValue("value") + "不合法");
			}
		}
	}

	@Override
	public void beforeRegister(List<Element> list) {
		// TODO Auto-generated method stub
		Resource resource = new ClassPathResource(CONFIG_PATH);
		XMLUtilWithClasspathResource xmlUtil = null;
		try {
			xmlUtil = new XMLUtilWithClasspathResource(resource.getInputStream());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			// 此处直接忽略
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// 此处直接忽略
		}
		if (list.get(0).attributeValue("value").equalsIgnoreCase("off")) {
			list.clear();
			list.addAll(xmlUtil.getNodesByXPath("content/common/leaf"));
		} else if (list.get(0).attributeValue("value").equalsIgnoreCase("on")) {
			list.clear();
			list.addAll(xmlUtil.getNodesByXPath("content/cluster/leaf"));
		} else {
			throw new ConfigException(CONFIG_FILE_NAME + "中cluster-switch配置有问题，职能配置为：on或off");
		}
	}

	@Override
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return CONFIG_PATH;
	}

}

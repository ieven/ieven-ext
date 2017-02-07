package com.ieven.ext.response.conf;

import java.util.List;

import org.dom4j.Element;

import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.io.AbstractRegisterConfigWithClasspathResource;
import com.ieven.ext.util.po.RegistType;

/**
 * 读取配置文件httpConfig工具类
 * 
 * @author lichong
 *
 */
public class ResponseConfig extends AbstractRegisterConfigWithClasspathResource {

	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/response/CodeDic.xml";

	/**
	 * 配置文件名称
	 */
	private static final String CONFIG_FILE_NAME = "CodeDic.xml";

	public ResponseConfig() {
		// 用来设定需要进行的注册类型
		this(RegistType.SPECIFIC);
	}

	private ResponseConfig(RegistType registType) {
		super(registType);
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
				throw new ConfigException(CONFIG_FILE_NAME + "中" + element.attributeValue("key") + "的value: "
						+ element.attributeValue("value") + "不合法");
			}
		}
	}

	@Override
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return CONFIG_PATH;
	}

}

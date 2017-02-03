package com.ieven.ext.http.conf;

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
public class HttpConfig extends AbstractRegisterConfigWithClasspathResource {

	public HttpConfig() {
		// 用来设定需要进行的注册类型
		this(RegistType.SPECIFIC);
	}

	private HttpConfig(RegistType registType) {
		super(registType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for (Element element : list) {
			if (element.attributeValue("key") == null || element.attributeValue("key").equals("")) {
				throw new ConfigException(ConfigProperty.CONFIG_FILE_NAME + "中key标签为空");
			} else if (element.attributeValue("value") == null || element.attributeValue("value").equals("")) {
				throw new ConfigException(ConfigProperty.CONFIG_FILE_NAME + "中value标签为空");
			}
			try {
				this.map.put(element.attributeValue("key"), Integer.parseInt(element.attributeValue("value")));
			} catch (Exception e) {
				// TODO: handle exception
				throw new ConfigException(ConfigProperty.CONFIG_FILE_NAME + "中" + element.attributeValue("key") + "的value: "
						+ element.attributeValue("value") + "不合法");
			}
		}
	}

	@Override
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return ConfigProperty.CONFIG_PATH;
	}

}

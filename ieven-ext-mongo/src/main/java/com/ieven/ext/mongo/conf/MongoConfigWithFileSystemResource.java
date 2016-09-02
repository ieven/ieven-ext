package com.ieven.ext.mongo.conf;

import java.util.List;

import org.dom4j.Element;

import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.io.AbstractRegisterConfigWithFileSystemResource;
import com.ieven.ext.util.po.RegistType;

/**
 * 读取配置文件MongoConfig工具类
 * @author lichong
 *
 */
public class MongoConfigWithFileSystemResource extends AbstractRegisterConfigWithFileSystemResource {

	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/db/MongoConfig.xml";

	private static final String CONFIG_FILE_NAME = "MongoConfig.xml";

	public MongoConfigWithFileSystemResource() {
		this(RegistType.SPECIFIC);
	}

	private MongoConfigWithFileSystemResource(RegistType registType) {
		super(registType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for (Element element : list) {
			if (element.attributeValue("key") == null || element.attributeValue("key").equals("")) {
				throw new ConfigException(CONFIG_FILE_NAME + "中key标签为空");
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
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return CONFIG_PATH;
	}

}

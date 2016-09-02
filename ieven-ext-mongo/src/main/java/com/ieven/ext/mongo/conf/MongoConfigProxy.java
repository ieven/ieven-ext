package com.ieven.ext.mongo.conf;

import java.util.Map;

import com.ieven.ext.util.io.FileSystemResource;
import com.ieven.ext.util.io.RegisterConfig;
import com.ieven.ext.util.io.Resource;

/**
 * 配置文件代理
 * @author lichong
 *
 */
public class MongoConfigProxy {
	
	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/db/MongoConfig.xml";
	
	private RegisterConfig config;
	
	public MongoConfigProxy() {
		// TODO Auto-generated constructor stub
		//若存在外部文件conf/db/MongoConfig.xml则使用FileSystemResource初始化
		config = new MongoConfig();
		Resource resource = new FileSystemResource(CONFIG_PATH);
		if(resource.exists()){
			RegisterConfig configTemp = new MongoConfigWithFileSystemResource();
			config.getConfigMap().putAll(configTemp.getConfigMap());
		}
	}
	
	public Map getConfigMap(){
		return this.config.getConfigMap();
	}
}

package com.ieven.ext.http.conf;

import java.util.Map;

import com.ieven.ext.util.io.FileSystemResource;
import com.ieven.ext.util.io.RegisterConfig;
import com.ieven.ext.util.io.Resource;

/**
 * 配置文件代理
 * @author lichong
 *
 */
public class HttpConfigProxy {
	
	private RegisterConfig config;
	
	public HttpConfigProxy() {
		// TODO Auto-generated constructor stub
		//若存在外部文件conf/http/HttpConfig.xml则使用FileSystemResource初始化
		config = new HttpConfig();
		Resource resource = new FileSystemResource(ConfigProperty.CONFIG_PATH);
		if(resource.exists()){
			RegisterConfig configTemp = new HttpConfigWithFileSystemResource();
			config.getConfigMap().putAll(configTemp.getConfigMap());
		}
	}
	
	public Map getConfigMap(){
		return this.config.getConfigMap();
	}
}

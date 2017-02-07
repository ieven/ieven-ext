package com.ieven.ext.response.conf;

import java.util.Map;

import com.ieven.ext.util.io.FileSystemResource;
import com.ieven.ext.util.io.RegisterConfig;
import com.ieven.ext.util.io.Resource;

/**
 * 配置文件代理
 * @author lichong
 *
 */
public class ResponseConfigProxy {
	
	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/response/CodeDic.xml";
	
	private RegisterConfig config;
	
	public ResponseConfigProxy() {
		// TODO Auto-generated constructor stub
		//若存在外部文件conf/response/ResponseConfig.xml则使用FileSystemResource初始化
		config = new ResponseConfig();
		Resource resource = new FileSystemResource(CONFIG_PATH);
		if(resource.exists()){
			RegisterConfig configTemp = new ResponseConfigWithFileSystemResource();
			config.getConfigMap().putAll(configTemp.getConfigMap());
		}
	}
	
	public Map getConfigMap(){
		return this.config.getConfigMap();
	}
}

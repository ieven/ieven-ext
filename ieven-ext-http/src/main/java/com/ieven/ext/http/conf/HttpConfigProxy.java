package com.ieven.ext.http.conf;

import java.io.IOException;
import java.util.Map;

import com.ieven.ext.util.io.FileSystemResource;
import com.ieven.ext.util.io.RegisterConfig;
import com.ieven.ext.util.io.Resource;
import com.ieven.ext.util.util.ResourceUtils;

/**
 * 配置文件代理
 * @author lichong
 *
 */
public class HttpConfigProxy {
	
	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/http/HttpConfig.xml";
	
	private RegisterConfig config;
	
	public HttpConfigProxy() {
		// TODO Auto-generated constructor stub
		//若存在外部文件conf/http/HttpConfig.xml则使用FileSystemResource初始化
		Resource resource = new FileSystemResource(CONFIG_PATH);
		if(!resource.exists()){
			config = new HttpConfig();
		}
		else {
			config = new HttpConfigWithFileSystemResource();
		}
	}
	
	public Map getConfigMap(){
		return this.config.getConfigMap();
	}
}

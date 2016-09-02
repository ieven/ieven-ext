package com.ieven.ext.quartz.conf;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Map;

import com.ieven.ext.util.io.ConfigListener;
import com.ieven.ext.util.io.FileSystemResource;
import com.ieven.ext.util.io.RegisterConfig;
import com.ieven.ext.util.io.Resource;

/**
 * job配置文件代理
 * 
 * @author lichong
 *
 */
public class JobConfigProxy {
	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/job/JobTactics.xml";

	private RegisterConfig config;

	public JobConfigProxy() {
		// TODO Auto-generated constructor stub
		config = new JobConfig();
		Resource resource = new FileSystemResource(CONFIG_PATH);
		if (resource.exists()) {
			RegisterConfig configTemp = new JobConfigWithFileSystemResource();
			config.getConfigMap().putAll(configTemp.getConfigMap());
		}
	}

	public Map getConfigMap() {
		return this.config.getConfigMap();
	}
}

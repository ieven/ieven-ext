package com.ieven.ext.quartz.conf;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Map;

import com.ieven.ext.util.io.ConfigListener;
import com.ieven.ext.util.io.FileSystemResource;
import com.ieven.ext.util.io.RegisterConfig;
import com.ieven.ext.util.io.Resource;

/**
 * 配置文件代理
 * 
 * @author lichong
 *
 */
public class ScheduleConfigProxy {

	/**
	 * 配置文件路径
	 */
	private static final String CONFIG_PATH = "conf/ScheduleConf/Schedule.xml";

	private RegisterConfig config;

	public ScheduleConfigProxy() {
		// TODO Auto-generated constructor stub
		// 若存在外部文件conf/mainconf/Schedule.xml则使用FileSystemResource初始化
		config = new ScheduleConfig();
		Resource resource = new FileSystemResource(CONFIG_PATH);
		if (resource.exists()) {
			RegisterConfig configTemp = new ScheduleConfigWithFileSystemResource();
			config.getConfigMap().putAll(configTemp.getConfigMap());
		}
	}

	public Map getConfigMap() {
		return this.config.getConfigMap();
	}
}

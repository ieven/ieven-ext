package com.ieven.ext.quartz.conf;

import java.util.Map;

import com.ieven.ext.quartz.po.JobBean;
import com.ieven.ext.quartz.po.TriggerBean;

/**
 * 配置文件代理类
 * 
 * @author lichong
 *
 */
public class ConfigProxy {

	/**
	 * 待初始化的配置
	 */
	private static ScheduleConfigProxy scheduleConfig;
	private static JobConfigProxy jobConfig;
	private static TriggerConfigProxy triggerConfig;
	
	/**
	 * 使用静态块确保实例只存在一个，并且能够保证执行顺序
	 */
	static {
		scheduleConfig = new ScheduleConfigProxy();
		jobConfig = new JobConfigProxy();
		triggerConfig = new TriggerConfigProxy();
	}

	/**
	 * 获取配置文件JobTactics.xml的trigger map
	 * 
	 * @return
	 */
	public static final Map<String, TriggerBean> getTriggerConfigMap() {
		return triggerConfig.getConfigMap();
	}

	/**
	 * 获取配置文件JobTactics.xml的job map
	 * 
	 * @return
	 */
	public static final Map<String, JobBean> getJobConfigMap() {
		return jobConfig.getConfigMap();
	}

	/**
	 * 获取配置文件Schedule.xml的map
	 * 
	 * @return
	 */
	public static final Map<String, String> getScheduleConfigMap() {
		return scheduleConfig.getConfigMap();
	}

}

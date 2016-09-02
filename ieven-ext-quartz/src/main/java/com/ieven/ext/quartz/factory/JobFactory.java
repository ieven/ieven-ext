package com.ieven.ext.quartz.factory;

import java.util.Properties;

import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.ieven.ext.quartz.conf.ConfigProxy;
import com.ieven.ext.quartz.exception.ScheduleInitException;

/**
 * 任务调度工厂，本质作用是将原来以properties的配置形式，转化为xml配置，进而统一配置管理
 * 
 * @author lichong
 *
 */
public class JobFactory extends StdSchedulerFactory {
	public JobFactory() {
		Properties properties = new Properties();
		properties.putAll(ConfigProxy.getScheduleConfigMap());
		properties.putAll(System.getProperties());
		try {
			super.initialize(properties);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new ScheduleInitException("任务调度初始化异常", e);
		}
	}
}

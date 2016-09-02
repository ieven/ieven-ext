package com.ieven.ext.quartz.service;

import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

/**
 * 任务调度service接口，定义任务调度service最基本功能
 * 
 * @author lichong
 *
 */
public interface ScheduleService {

	/**
	 * 关闭任务调度
	 */
	public void shutdown();

	/**
	 * 获取调度工厂
	 */
	public SchedulerFactory getSchedulerFactory();

	/**
	 * 获取调度执行程序
	 * 
	 * @return
	 */
	public Scheduler getScheduler();

	/**
	 * 启动任务调度
	 * 
	 * @throws SchedulerException
	 */
	public void start() throws SchedulerException;

	/**
	 * 绑定任务和触发器
	 * 
	 * @param jobDetail
	 * @param triggersForJob
	 * @return
	 * @throws SchedulerException
	 */
	public boolean bindJobWithTrigger(JobDetail jobDetail, Set<Trigger> triggersForJob);

	/**
	 * 重启任务调度任务
	 * @throws SchedulerException
	 */
	public void restart() throws SchedulerException;
}

package com.ieven.ext.quartz.service;

import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ieven.ext.quartz.exception.ScheduleCloseException;
import com.ieven.ext.quartz.exception.ScheduleInitException;
import com.ieven.ext.quartz.factory.JobFactory;

/**
 * 抽象任务调度服务
 * 
 * @author lichong
 *
 */
public abstract class AbstractScheduleService implements ScheduleService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SchedulerFactory schedulerFactory;

	private Scheduler scheduler;

	/**
	 * 使用jobFactory初始化
	 */
	public AbstractScheduleService() {
		this(new JobFactory());
	}

	/**
	 * 自定义SchedulerFactory，如自定义配置文件位置内容等
	 * 
	 * @param factory
	 */
	public AbstractScheduleService(SchedulerFactory factory) {
		this.schedulerFactory = factory;
		try {
			this.scheduler = schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new ScheduleInitException("获取scheduler失败", e);
		}
	}

	@Override
	public SchedulerFactory getSchedulerFactory() {
		return this.schedulerFactory;
	}

	@Override
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	@Override
	public void start() throws SchedulerException {
		scheduler.start();
	}

	@Override
	public boolean bindJobWithTrigger(JobDetail jobDetail, Set<Trigger> triggersForJob){
		try {
			scheduler.scheduleJob(jobDetail, triggersForJob, true);
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			getLogger().error(printBindErrorMsg(jobDetail, triggersForJob),e);
			return false;
		}
	}

	/**
	 * 打印绑定时的错误信息
	 * @param jobDetail
	 * @param triggersForJob
	 * @return
	 */
	protected String printBindErrorMsg(JobDetail jobDetail, Set<Trigger> triggersForJob)
	{
		StringBuilder builder = new StringBuilder("在任务： ");
		builder.append(jobDetail.getKey().toString());
		builder.append(" 上绑定触发器：");
		for(Trigger trigger : triggersForJob)
		{
			builder.append(trigger.getKey().toString());
			builder.append(" ");
		}
		builder.append("失败");
		return builder.toString();
	}
	
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		try {
			// 关闭任务调度
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new ScheduleCloseException("任务调度关闭失败", e);
		}
	}

	public Logger getLogger()
	{
		return this.logger;
	}
}

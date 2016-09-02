package com.ieven.ext.quartz.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.ieven.ext.quartz.conf.ConfigProxy;
import com.ieven.ext.quartz.exception.ScheduleInitException;
import com.ieven.ext.quartz.po.JobBean;
import com.ieven.ext.quartz.po.TriggerBean;

/**
 * 默认任务调度service，读取配置文件信息并使用jobFactory初始化任务调度
 * @author lichong
 *
 */
public class DefaultScheduleService extends AbstractScheduleService {
	
	public DefaultScheduleService() {
		super();
	}

	@Override
	public void start() throws SchedulerException {
		// TODO Auto-generated method stub
		super.start();
		bind();
	}

	/**
	 * 将读取到的调度信息进行绑定
	 */
	private void bind()
	{
		// 获取job map
		Map<String, JobBean> jobMap = ConfigProxy.getJobConfigMap();
		// 遍历map
		for (Map.Entry<String, JobBean> entry : jobMap.entrySet()) {
			JobBean jobBean = entry.getValue();
			Job job = null;
			try {
				//讲具体的job实现类初始化
				job = (Job) Class.forName(jobBean.getClazz()).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				throw new ScheduleInitException("初始化"+jobBean.getClazz()+"失败，请检查配置是否正确", e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new ScheduleInitException("初始化"+jobBean.getClazz()+"失败，请检查配置是否正确", e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new ScheduleInitException("初始化"+jobBean.getClazz()+"失败，请检查配置是否正确", e);
			}
			//初始化jobDetail
			JobDetail jobDetail = JobBuilder.newJob(job.getClass())
					//设定job的name和group
					.withIdentity(jobBean.getName(), jobBean.getGroup())
					//设定job的描述信息
					.withDescription(jobBean.getDes())
					//绑定到调度引擎
					.build();
			//创建需要绑定到job上的触发器set
			Set<Trigger> triggersForJob = new HashSet<Trigger>(jobBean.getTrigger().length);
			//遍历job上需要绑定的trigger
			for(String triggerName : jobBean.getTrigger())
			{
				//根据名字从代理类中获取对应trigger详细信息
				TriggerBean triggerBean = ConfigProxy.getTriggerConfigMap().get(triggerName);
				//如果没有找到对应trigger信息则抛出异常
				if(triggerBean==null)
				{
					throw new ScheduleInitException("配置文件中找不到name为"+triggerName+"的trigger");
				}
				//开始实例化trigger
				Trigger trigger = TriggerBuilder.newTrigger()
												//绑定描述信息
												.withDescription(triggerBean.getDes())
												//绑定name和group
												.withIdentity(triggerBean.getName(), triggerBean.getGroup())
												//绑定触发策略
												.withSchedule(CronScheduleBuilder.cronSchedule(triggerBean.getCron()))
												.build();
				triggersForJob.add(trigger);
			}
			if(!super.bindJobWithTrigger(jobDetail, triggersForJob))
			{
				//由于当前使用的配置文件形式在启动的时候就进行绑定，所以在绑定不成功时抛出运行时异常，直接不让项目启动起来
				throw new ScheduleInitException(printBindErrorMsg(jobDetail, triggersForJob));
			}
		}
	}

	@Override
	public void restart() throws SchedulerException {
		// TODO Auto-generated method stub
		//当前由于任务调度关闭之后不能再次启动，日后会补充其他方式实现重启
	}
	
}

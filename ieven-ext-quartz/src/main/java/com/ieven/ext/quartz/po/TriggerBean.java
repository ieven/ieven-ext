package com.ieven.ext.quartz.po;

public class TriggerBean {
	private String name;
	
	private String group;
	
	private String des;
	
	private String cron;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}
	
	public TriggerBean withName(String name)
	{
		setName(name);
		return this;
	}
	
	public TriggerBean withGroup(String group)
	{
		setGroup(group);
		return this;
	}
	
	public TriggerBean withDes(String des)
	{
		setDes(des);
		return this;
	}
	
	public TriggerBean withCron(String cron)
	{
		setCron(cron);
		return this;
	}
}

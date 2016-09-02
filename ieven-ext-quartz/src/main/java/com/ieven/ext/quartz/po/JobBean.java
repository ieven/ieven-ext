package com.ieven.ext.quartz.po;

import java.util.List;

public class JobBean {
	private String name;
	
	private String des;
	
	private String group;
	
	private String clazz;
	
	private String[] trigger;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	
	public String[] getTrigger() {
		return trigger;
	}

	public void setTrigger(String[] trigger) {
		this.trigger = trigger;
	}

	public JobBean withName(String name)
	{
		setName(name);
		return this;
	}
	
	public JobBean withDes(String des)
	{
		setDes(des);
		return this;
	}
	
	public JobBean withGroup(String group)
	{
		setGroup(group);
		return this;
	}
	
	public JobBean withClazz(String clazz)
	{
		setClazz(clazz);
		return this;
	}
	
	public JobBean withTrigger(String[] trigger)
	{
		setTrigger(trigger);
		return this;
	}
}

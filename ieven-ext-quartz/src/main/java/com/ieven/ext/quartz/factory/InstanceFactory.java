package com.ieven.ext.quartz.factory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 实例工厂类，用于统一管理同一线程中的各个的实例。
 */
public class InstanceFactory {
	
	// 创建一个对象池，并初始化容器
	private static ConcurrentHashMap<String, Object>objPool = new ConcurrentHashMap<>();;
	
	/*
	 * 根据类的包路径名称，返回该类的一个实例。
	 */
	public static <T> T getInstance(String clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		T obj = null;
		if (objPool.containsKey(clazz)) {// 如果对象池中已存在，则直接从对象池中获取。
			obj = (T) objPool.get(clazz);
		} else {
			// 如果对象池中不存在，则动态创建一个该类的实例，并将新创建的实例放入对象池。
			obj = (T) Class.forName(clazz).newInstance();
			objPool.put(clazz, obj);
		}
		return obj;
	}
}

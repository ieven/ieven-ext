package com.ieven.ext.util.po;

public enum RegistType {
	/**
	 * 读取给定路径下一个配置文件中的内容（路径为具体一个文件）
	 */
	SPECIFIC,
	/**
	 * 读取给定路径下所有配置文件中的内容（路径为配置文件夹路径）
	 */
	MULTIPLE,
	/**
	 * 读取给定路径下一个配置文件中的内容（路径为具体一个文件）并使用xpath进行筛选
	 * 此种解析需要提供带有xpth的构造函数
	 */
	SPECIFIC_XPATH
}

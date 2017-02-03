package com.ieven.ext.http.conf;

/**
 * 定义配置文件的各个属性
 * 
 * @author lichong
 *
 */
public interface ConfigProperty {

	/**
	 * 对应环境的配置文件
	 */
	public static String environment = "";

	/**
	 * 配置文件名称
	 */
	public static String CONFIG_FILE_NAME = "HttpConfig" + environment + ".xml";
	
	/**
	 * 配置文件路径前缀
	 */
	public static String CONFIG_PATH_HTTP_PRE = "conf/http/";
	
	/**
	 * 配置文件路径
	 */
	public static String CONFIG_PATH = CONFIG_PATH_HTTP_PRE + CONFIG_FILE_NAME;

}

package com.ieven.ext.echarts.util;

import com.ieven.ext.echarts.phantomjs.OSType;

/**
 * 操作系统类型工具
 * 
 * @author lichong
 *
 */
public class OSTypeUtil {
	
	/**
	 * 获取当前操作系统类型
	 * @return
	 */
	public static OSType getOSType() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.indexOf("windows") >= 0) {
			return OSType.WINDOWS;
		} else if (osName.indexOf("linux") >= 0) {
			return OSType.LINUX;
		} else {
			return OSType.NUKNOWN;
		}
	}
}

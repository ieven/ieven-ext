package com.ieven.ext.util.io;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 配置文件监听接口
 * @author lichong
 *
 */
public interface ConfigListener {
	/**
	 * 当监听点出现增加操作时触发
	 * @param e
	 */
	public void onCreate(WatchEvent<Path> e);
	/**
	 * 当监听点出现删除操作时触发
	 * @param e
	 */
	public void onDelete(WatchEvent<Path> e);
	
	/**
	 * 当监听点出现修改操作时触发
	 * @param e
	 */
	public void onModify(WatchEvent<Path> e);
}

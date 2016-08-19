package com.ieven.ext.util.io;

import java.nio.file.Paths;
import java.util.List;

import org.dom4j.Element;

import com.ieven.ext.util.po.RegistType;

/**
 * 添加了watcher的抽象配置文件注册类
 * 
 * @author lichong
 *
 */
public abstract class AbstractRegisterConfigWithFileSystemListener
		extends AbstractRegisterConfigWithFileSystemResource {

	public AbstractRegisterConfigWithFileSystemListener(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	public AbstractRegisterConfigWithFileSystemListener(RegistType registType) {
		super(registType);
		// TODO Auto-generated constructor stub
	}

	public void addListener(ConfigListener listener) {
		ConfigWatcherService.getInstance().addListener(
				Paths.get(new FileSystemResource(getConfigPath()).getFile().getParentFile().getAbsolutePath()),
				listener);
	}

	@Override
	public void beforeRegister(List<Element> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRegister(List<Element> list) {
		// TODO Auto-generated method stub

	}
}

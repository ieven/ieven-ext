package com.ieven.ext.util.io;

import java.nio.file.Paths;

import com.ieven.ext.util.po.RegistType;

public abstract class AbstractRegisterConfigWithListener extends AbstractRegisterConfigWithClasspathResource{

	public AbstractRegisterConfigWithListener(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	public AbstractRegisterConfigWithListener(RegistType registType) {
		super(registType);
		// TODO Auto-generated constructor stub
	}
	
	public void addListener(ConfigListener listener){
		ConfigWatcherService.getInstance().addListener(Paths.get(new FileSystemResource(getConfigPath()).getFile().getParentFile().getAbsolutePath()), listener);
	}
}

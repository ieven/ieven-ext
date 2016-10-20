package com.ieven.ext.echarts.phantomjs;

import java.io.IOException;

import org.junit.Test;

import com.ieven.ext.echarts.exception.OSTypeUnsupportException;
import com.ieven.ext.util.io.ClassPathResource;
import com.ieven.ext.util.io.Resource;

public class PhantomjsTest {
	
	private Phantomjs phantomjsUtil = new Phantomjs();
	
	@Test
	public void test() throws IOException{
		Resource data = new ClassPathResource("phantomjs/data/options");
		try {
			phantomjsUtil.hatchPic(OSType.WINDOWS, data.getFile().getPath(), "d:/success1.png",1000,1000);
			phantomjsUtil.hatchPic(OSType.WINDOWS, data.getFile().getPath(), "d:/success2.png");
			phantomjsUtil.hatchPic(data.getFile().getPath(), "d:/success3.png");
		} catch (OSTypeUnsupportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

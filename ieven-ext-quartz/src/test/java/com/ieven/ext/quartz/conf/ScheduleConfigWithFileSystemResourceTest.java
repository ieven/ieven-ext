package com.ieven.ext.quartz.conf;

import java.util.Map;

import org.junit.Test;

public class ScheduleConfigWithFileSystemResourceTest {
	
	@Test
	public void test(){
		ScheduleConfigWithFileSystemResource config = new ScheduleConfigWithFileSystemResource();
		Map<String,Integer> map = config.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

package com.ieven.ext.response.conf;

import java.util.Map;

import org.junit.Test;

public class ResponseConfigTest {
	
	@Test
	public void httpConfigTest(){
		ResponseConfig httpConfig = new ResponseConfig();
		Map<String,Integer> map = httpConfig.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

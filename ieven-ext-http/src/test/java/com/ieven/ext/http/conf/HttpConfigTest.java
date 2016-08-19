package com.ieven.ext.http.conf;

import java.util.Map;

import org.junit.Test;

public class HttpConfigTest {
	
	@Test
	public void httpConfigTest(){
		HttpConfig httpConfig = new HttpConfig();
		Map<String,Integer> map = httpConfig.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

package com.ieven.ext.response.conf;

import java.util.Map;

import org.junit.Test;

public class ResponseConfigProxyTest {
	
	@Test
	public void httpConfigProxyTest(){
		ResponseConfigProxy configProxy = new ResponseConfigProxy();
		Map<String,Integer> map = configProxy.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

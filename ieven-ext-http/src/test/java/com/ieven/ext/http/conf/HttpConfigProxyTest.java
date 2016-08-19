package com.ieven.ext.http.conf;

import java.util.Map;

import org.junit.Test;

public class HttpConfigProxyTest {
	
	@Test
	public void httpConfigProxyTest(){
		HttpConfigProxy configProxy = new HttpConfigProxy();
		Map<String,Integer> map = configProxy.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

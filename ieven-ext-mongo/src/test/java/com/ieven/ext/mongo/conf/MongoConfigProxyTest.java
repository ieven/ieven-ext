package com.ieven.ext.mongo.conf;

import java.util.Map;

import org.junit.Test;

public class MongoConfigProxyTest {
	
	@Test
	public void httpConfigProxyTest(){
		MongoConfigProxy configProxy = new MongoConfigProxy();
		Map<String,Integer> map = configProxy.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

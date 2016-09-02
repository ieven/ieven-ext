package com.ieven.ext.mongo.conf;

import java.util.Map;

import org.junit.Test;

public class MongoConfigTest {
	
	@Test
	public void httpConfigTest(){
		MongoConfig config = new MongoConfig();
		Map<String,Integer> map = config.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

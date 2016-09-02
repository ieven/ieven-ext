package com.ieven.ext.mongo.conf;

import java.util.Map;

import org.junit.Test;

public class MongoConfigWithFileSystemResourceTest {
	
	@Test
	public void httpConfigTest(){
		MongoConfigWithFileSystemResource configWithFileSystemResource = new MongoConfigWithFileSystemResource();
		Map<String,Integer> map = configWithFileSystemResource.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

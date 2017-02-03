package com.ieven.ext.response.conf;

import java.util.Map;

import org.junit.Test;

public class ResponseConfigWithFileSystemResourceTest {
	
	@Test
	public void httpConfigTest(){
		ResponseConfigWithFileSystemResource httpConfigWithFileSystemResource = new ResponseConfigWithFileSystemResource();
		Map<String,Integer> map = httpConfigWithFileSystemResource.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

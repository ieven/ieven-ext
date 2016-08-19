package com.ieven.ext.http.conf;

import java.util.Map;

import org.junit.Test;

public class HttpConfigWithFileSystemResourceTest {
	
	@Test
	public void httpConfigTest(){
		HttpConfigWithFileSystemResource httpConfigWithFileSystemResource = new HttpConfigWithFileSystemResource();
		Map<String,Integer> map = httpConfigWithFileSystemResource.getConfigMap();
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}

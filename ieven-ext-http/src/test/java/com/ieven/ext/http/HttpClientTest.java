package com.ieven.ext.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.databind.JsonNode;

public class HttpClientTest extends HttpClientWithJsonResponseHandler{

	/**
	 * 使用时可自定义配置文件，放在classpath或者包同目录下即可，文件名为：conf/http/HttpConfig.xml
	 * 
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientTest clientTest = new HttpClientTest();
		clientTest.test();
	}

	/**
	 * 使用方法简介
	 */
	public void test(){
		Map<String, String> param = new HashMap<String, String>();
		param.put("address", "上海虹桥机场");
		HttpGet httpGet = new HttpGet("http://api.map.baidu.com/geocoder?output=json");
		httpGet.setURI(addParamToURL(httpGet.getURI(), param));
		JsonNode result = executeHttp(httpGet);
		System.out.println(result);
	}
}

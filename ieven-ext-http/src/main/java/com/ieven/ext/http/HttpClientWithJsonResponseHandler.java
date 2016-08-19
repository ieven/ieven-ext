package com.ieven.ext.http;

import org.apache.http.client.methods.HttpRequestBase;

import com.fasterxml.jackson.databind.JsonNode;
import com.ieven.ext.http.handler.JsonResponseHandler;

/**
 * httpClient并以jsonhandler处理返回结果
 * @author lichong
 *
 */

public class HttpClientWithJsonResponseHandler extends HttpClientWithPool{
	
	private JsonResponseHandler jsonResponseHandler;

	public HttpClientWithJsonResponseHandler() {
		super();
		this.jsonResponseHandler = new JsonResponseHandler();
		// TODO Auto-generated constructor stub
	}
	
	public JsonNode executeHttp(HttpRequestBase httpRequest){
		return super.executeHttp(httpRequest, this.jsonResponseHandler);
	}
}

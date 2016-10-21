package com.ieven.ext.http.handler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实现httpclient ResponseHandler接口，以json形式接受返回信息，当返回不正常错误码时抛出异常
 * @author lichong
 *
 */
public class JsonResponseHandler implements ResponseHandler<JsonNode>{

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Override
	public JsonNode handleResponse(HttpResponse response) throws ClientProtocolException, IOException, HttpResponseException {
		// TODO Auto-generated method stub
		HttpEntity entity = response.getEntity();
        try {
            InputStream stream = entity.getContent();
            return MAPPER.readTree(stream);
        } finally {
            EntityUtils.consume(entity);
        }
        //由于有人会自定义返回码，不能按照常理出牌
//		int status = response.getStatusLine().getStatusCode();
//		if (status >= 200 && status < 300) {
//			HttpEntity entity = response.getEntity();
//	        try {
//	            InputStream stream = entity.getContent();
//	            return MAPPER.readTree(stream);
//	        } finally {
//	            EntityUtils.consume(entity);
//	        }
//		}
//		else
//		{
//			//当返回非正常状态时抛出异常
//			throw new HttpResponseException(status, "请求失败");
//		}
	}

}

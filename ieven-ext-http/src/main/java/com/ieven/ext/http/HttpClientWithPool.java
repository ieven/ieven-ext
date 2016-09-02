package com.ieven.ext.http;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ieven.ext.http.conf.HttpConfigProxy;
import com.ieven.ext.http.exception.HttpProtocolException;
import com.ieven.ext.http.exception.ResponseException;
import com.ieven.ext.http.util.HttpUtil;

/**
 * 根据配置信息初始化一个httpclient
 * 
 * @author lichong
 *
 */
public class HttpClientWithPool extends HttpUtil{

	private static HttpConfigProxy httpConfig = new HttpConfigProxy();

	protected CloseableHttpClient httpclient;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public Logger getLogger() {
		return logger;
	}

	public HttpClientWithPool() {
		/*
		 * 根据配置文件设置httpclient各项属性 begin
		 */
		Map<String, Integer> httpConfigMap = httpConfig.getConfigMap();
		RequestConfig requestConfig = RequestConfig.custom()
				// 使用连接池时才有用，从连接池中获取连接的最长等待时间
				.setConnectionRequestTimeout(httpConfigMap.get("connectionRequestTimeout"))
				// 连接建立时间
				.setConnectTimeout(httpConfigMap.get("connectTimeout"))
				// 连接成功后等待数据返回时间
				.setSocketTimeout(httpConfigMap.get("socketTimeout")).build();
		// 设置httpclient连接池属性
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
		manager.setMaxTotal(httpConfigMap.get("maxTotal"));
		manager.setDefaultMaxPerRoute(httpConfigMap.get("defaultMaxPerRoute"));
		// 根据已经设定好的各个配置初始化httpclient
		httpclient = HttpClients.custom().setConnectionManager(manager).setDefaultRequestConfig(requestConfig).build();
		/*
		 * 根据配置文件设置httpclient各项属性 end
		 */
		logger.info("connectionRequestTimeout:"+httpConfigMap.get("connectionRequestTimeout"));
		logger.info("connectTimeout:"+httpConfigMap.get("connectTimeout"));
		logger.info("socketTimeout:"+httpConfigMap.get("socketTimeout"));
		logger.info("maxTotal:"+httpConfigMap.get("maxTotal"));
		logger.info("defaultMaxPerRoute:"+httpConfigMap.get("defaultMaxPerRoute"));
	}

	public <T> T executeHttp(HttpRequestBase httpRequest,ResponseHandler<T> handler){
		try {
			logger.info("执行请求：" + httpRequest.getRequestLine());
			T result = (T) httpclient.execute(httpRequest, handler);
			logger.debug("请求返回的数据为：" + result);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			try {
				throw new HttpProtocolException("执行" + httpRequest.getRequestLine() + " 时发生http协议异常,请检查URL是否正确:", e);
			} catch (HttpProtocolException e1) {
				// TODO Auto-generated catch block
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			responseExceptionHandler(e, httpRequest);
			return null;
		}
	}
	
	/**
	 * 根据返回的异常信息作出对应处理
	 * 
	 * @param e
	 * @param httpGet
	 */
	protected void responseExceptionHandler(IOException e, HttpRequestBase httpRequest) {
		if (e.getClass().toString().contains("java.net.UnknownHostException")) {
			try {
				throw new ResponseException("无法解析ip地址为：" + httpRequest.getURI() + "未知的ip，请检查url配置是否正确", e);
			} catch (ResponseException e1) {
				// TODO Auto-generated catch block
				// 直接或略
			}
		} else if (e.getClass().toString().contains("org.apache.http.conn.ConnectTimeoutException")) {
			try {
				throw new ResponseException("连接超时，请适当增大ConnectTimeout、优化当前网络环境或检查目标地址是否通畅", e);
			} catch (ResponseException e1) {
				// TODO Auto-generated catch block
				// 直接或略
			}
		} else if (e.getClass().toString().contains("java.net.SocketTimeoutException")) {
			try {
				throw new ResponseException("读取时间超时，接口未能在指定时间内返回数据，请适当增大SocketTimeout或检查目标地址是否运行正常", e);
			} catch (ResponseException e1) {
				// TODO Auto-generated catch block
				// 直接或略
			}
		} else if (e.getClass().toString().contains("org.apache.http.conn.ConnectionPoolTimeoutException")) {
			try {
				throw new ResponseException(
						"等待队列等待超时，可适当增大配置文件中maxTotal、增大最长等待时间connectionRequestTimeout或检查目标地址是否工作正常，此项异常信息请结合业务量和接口反应时间总和分析",
						e);
			} catch (ResponseException e1) {
				// TODO Auto-generated catch block
				// 直接或略
			}
		} else if (e.getClass().toString().contains("org.apache.http.client.HttpResponseException")) {
			try {
				throw new ResponseException("请求地址: " + httpRequest.getURI() + "返回异常，错误码为："
						+ ((HttpResponseException) e).getStatusCode() + ",请对照http错误码确定问题所在。", e);
			} catch (ResponseException e1) {
				// TODO Auto-generated catch block
				// 直接或略
			}
		} else if (e.getClass().toString().contains("org.apache.http.conn.HttpHostConnectException")) {
			try {
				throw new ResponseException("请求地址: " + httpRequest.getURI() + "连接拒绝，请检查地址配置是否正确", e);
			} catch (ResponseException e1) {
				// TODO Auto-generated catch block
				// 直接或略
			}
		} else {
			try {
				throw new ResponseException("出现了为解析的异常，请根据异常堆栈信息推断问题，并提醒研发人员解决", e);
			} catch (ResponseException e1) {
				// TODO Auto-generated catch block
				// 直接或略
			}
		}
	}
	
}

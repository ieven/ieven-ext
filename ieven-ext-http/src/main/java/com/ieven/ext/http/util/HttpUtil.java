package com.ieven.ext.http.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ieven.ext.util.util.StringUtils;

/**
 * http小工具
 * 
 * @author lichong
 *
 */
public class HttpUtil {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String ENCODE = "utf-8";

	/**
	 * 将uri按照规定编码格式进行URLEncode
	 * 
	 * @param uri
	 * @return
	 */
	public String encode(String str) {
		try {
			return URLEncoder.encode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("转码失败，无法将 " + str + "进行 " + ENCODE + " URLEncode");
			return str;
		}
	}

	/**
	 * 将uri按照规定编码格式进行URLEncode
	 * 
	 * @param str
	 * @param charset
	 * @return
	 */
	public String encode(String str, String charset) {
		try {
			return URLEncoder.encode(str, charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("转码失败，无法将 " + str + "进行 " + charset + " URLEncode");
			return str;
		}
	}
	
	/**
	 * 向当前URI中加入参数(如：localhost:80/test?userid=renrenche&userpwd=kuXwB添加参数为：
	 * localhost:80/test?userid=renrenche&userpwd=kuXwB&test1=test&test2=test2)
	 * 
	 * @param uri
	 * @param param
	 * @return
	 */
	public URI addParamToURL(URI uri, Map<String, String> param) {
		StringBuilder builder = new StringBuilder(uri + "&");
		for (Map.Entry<String, String> entry : param.entrySet()) {
			if (StringUtils.hasLength(entry.getValue())) {
				builder.append(entry.getKey());
				builder.append("=");
				builder.append(entry.getValue());
				builder.append("&");
			}
		}
		return URI.create(builder.substring(0, builder.length() - 1));
	}
}

package com.ieven.ext.echarts.phantomjs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ieven.ext.echarts.exception.OSTypeUnsupportException;
import com.ieven.ext.echarts.util.OSTypeUtil;
import com.ieven.ext.util.io.ClassPathResource;
import com.ieven.ext.util.io.Resource;
import com.ieven.ext.util.io.support.ResourcePatternResolver;
import com.ieven.ext.util.util.ResourceUtils;

/**
 * 使用phantomjs生产对应echarts图片
 * 
 * @author lichong
 *
 */
public class Phantomjs {

	private static final Logger logger = LoggerFactory.getLogger(Phantomjs.class);
	
	private static final int WIDTH = 600;
	
	private static final int HEIGHT = 600;
	
	/**
	 * 根据指定参数生成图片
	 * @param type
	 * @param filePath
	 * @param outPath
	 * @param width
	 * @param height
	 * @throws OSTypeUnsupportException
	 */
	public void hatchPic(OSType type, String filePath, String outPath,int width,int height) throws OSTypeUnsupportException {
		Runtime rt = Runtime.getRuntime();
		Process p;
		String phantomjsLocation = "phantomjs/%1$s/phantomjs";
		switch (type) {
		case LINUX:
			phantomjsLocation = String.format(phantomjsLocation, "linux");
			break;
		case WINDOWS:
			phantomjsLocation = String.format(phantomjsLocation, "windows");
			break;
		case NUKNOWN:
			throw new OSTypeUnsupportException("当前版本不支持此操作系统");
		}
		Resource phantomjsResource = new ClassPathResource(phantomjsLocation);
		Resource convertResource = new ClassPathResource("phantomjs/echarts-convert.js");
		try {
			String commodLine = phantomjsResource.getFile().getPath() + " " + convertResource.getFile().getPath() + " "
					+ "-infile " + filePath + " -outfile " + outPath+" -width "+width+" -height "+height;
			logger.info("执行的命令行为：" + commodLine);
			p = rt.exec(commodLine);
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sbf = new StringBuffer();
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				sbf.append(tmp);
			}
			logger.info(sbf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("生成echarts报表失败", e);
		}
	}
	
	/**
	 * 根据指定参数生成图片
	 * @param type
	 * @param filePath
	 * @param outPath
	 * @throws OSTypeUnsupportException 
	 */
	public void hatchPic(OSType type, String filePath, String outPath) throws OSTypeUnsupportException{
		hatchPic(type, filePath, outPath, WIDTH, HEIGHT);
	}
	
	/**
	 * 根据指定参数生成图片
	 * @param filePath
	 * @param outPath
	 * @throws OSTypeUnsupportException 
	 */
	public void hatchPic(String filePath, String outPath) throws OSTypeUnsupportException{
		hatchPic(OSTypeUtil.getOSType(), filePath, outPath, WIDTH, HEIGHT);
	}
}

package com.ieven.ext.util.util.xml;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ieven.ext.util.excepiton.InstanceException;
import com.ieven.ext.util.io.ClassPathResource;
import com.ieven.ext.util.io.Resource;
import com.ieven.ext.util.util.Assert;
import com.ieven.ext.util.util.StringUtils;

/**
 * 使用dom4j读取配置文件依赖包：dom4j-1.6.1.jar jaxen.jar
 * 
 * @author lichong
 * 
 */
public class XMLUtilWithClasspathResource {

	private String filePath;
	private SAXReader reader;
	private Document document;

	/**
	 * 构造函数
	 * 
	 * @param filePath
	 *            需要处理的XML路径
	 * @throws DocumentException
	 */
	public XMLUtilWithClasspathResource(String filePath) throws DocumentException {
		// 创建saxReader对象
		this.reader = new SAXReader();
		// 通过read方法读取一个文件 转换成Document对象
		this.filePath = filePath;
		this.document = reader.read(new File(this.filePath));

	}
	
	/**
	 * 构造函数
	 * @param inputStream
	 * @throws DocumentException
	 */
	public XMLUtilWithClasspathResource(InputStream inputStream) throws DocumentException {
		// 创建saxReader对象
		this.reader = new SAXReader();
		this.document = reader.read(inputStream);
	}

	/**
	 * 根据节点名字查找对应值（lichong.lichong）
	 * 
	 * @param nodeTreeName
	 *            节点名字（lichong.lichong）
	 * @return 对应节点值
	 */
	public String getNodeData(String nodeTreeName) {
		// 获取根节点元素对象
		Element rootElement = this.document.getRootElement();
		String nodeMap[] = nodeTreeName.split("\\.");
		if (nodeMap.length == 1) {
			return rootElement.element(nodeMap[0]).getData().toString();
		} else if (nodeMap.length <= 0) {
			return "";
		} else {
			Element element = rootElement.element(nodeMap[0]);
			for (int i = 1; i < nodeMap.length; i++) {
				element = element.element(nodeMap[i]);
			}
			return element.getData().toString();
		}
	}

	/**
	 * 获取node下所有的属性值
	 * 
	 * @return
	 */
	public List getNodes(String rootNodeName) {
		return this.document.selectNodes("//" + rootNodeName);

	}
	
	/**
	 * 根据xpath表达式获取指定路径下xml文件中内容
	 * @param path xml文件路径
	 * @param XPath 
	 * @return
	 */
	public static List getNodesByXPath(String path,String XPath)
	{
		Assert.isTrue(StringUtils.hasText(path), "配置文件路径不能为空");
		XMLUtilWithClasspathResource xmlUtil = null;
		try {
			Resource resource = new ClassPathResource(path);
			xmlUtil = new XMLUtilWithClasspathResource(resource.getInputStream());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new InstanceException("", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new InstanceException(path+"文件不存在", e);
		}
		return xmlUtil.getNodesByXPath(XPath);
	}
	
	public List getNodesByXPath(String XPath)
	{
		return this.document.selectNodes(XPath);
	}

	/**
	 * 获取指定路径下所有配置文件leaf节点下的Element
	 * @param path
	 * @return
	 */
	public static List<Element> getConfigFileNodes(String path)
	{
		Assert.isTrue(StringUtils.hasText(path), "配置文件路径不能为空");
		List<Element> result = new ArrayList<Element>();
		try {
			Resource resource = new ClassPathResource(path);
			//获取文件列表
			File file = new File(resource.getURI());
			for(String fileName : file.list())
			{
				String pathStr = path +"/"+ fileName;
				resource = new ClassPathResource(pathStr);
				XMLUtilWithClasspathResource xmlUtil = new XMLUtilWithClasspathResource(resource.getInputStream());
				result.addAll(xmlUtil.getNodes("leaf"));
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new InstanceException("", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new InstanceException(path+"文件不存在", e);
		}
		return result;
	}
	
	/**
	 * 获取指定路径下配置文件leaf节点下的Element
	 * @param path
	 * @return
	 */
	public static List<Element> getGivenConfigFileNodes(String path)
	{
		Assert.isTrue(StringUtils.hasText(path), "配置文件路径不能为空");
		List<Element> result = new ArrayList<Element>();
		try {
			Resource resource = new ClassPathResource(path);
			XMLUtilWithClasspathResource xmlUtil = new XMLUtilWithClasspathResource(resource.getInputStream());
			result.addAll(xmlUtil.getNodes("leaf"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new InstanceException("", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new InstanceException(path+"文件不存在", e);
		}
		return result;
	}
	
	/**
	 * 获取指定路径下所有配置文件leaf节点下的Element,key为文件名称，value为文件内容
	 * @param path
	 * @return
	 */
	public static Map<String, List<Element>> getConfigFileNodesMap(String path)
	{
		Assert.isTrue(StringUtils.hasText(path), "配置文件路径不能为空");
		Map<String, List<Element>> result = null;
		try {
			Resource resource = new ClassPathResource(path);
			//获取文件列表
			File file = new File(resource.getURI());
			result = new HashMap<String, List<Element>>(file.list().length);
			for(String fileName : file.list())
			{
				String pathStr = path +"/"+ fileName;
				resource = new ClassPathResource(pathStr);
				XMLUtilWithClasspathResource xmlUtil = new XMLUtilWithClasspathResource(resource.getInputStream());
				result.put(fileName, xmlUtil.getNodes("leaf"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new InstanceException(path+"文件不存在", e);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new InstanceException("读取xml文件出错", e);
		}
		return result;
	}
}

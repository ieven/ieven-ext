package com.ieven.ext.util.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
/**
 * 资源类，为资源定义接口方法
 * 
 * @author lichong
 */
public interface Resource extends InputStreamSource {

	/**
	 * 检测当前资源是否存在
	 * @return 检测结果
	 */
	boolean exists();

	/**
	 * 检测当前资源是否可读
	 * @return 检测结果
	 */
	boolean isReadable();

	/**
	 * 返回当前资源是否有一个开放流的句柄。如果是true那么这个InputStream不能被多次读取，并且必须关闭，防止资源溢出
	 * @return
	 */
	boolean isOpen();

	/**
	 * 放回一个URL句柄为当前资源
	 * @return
	 * @throws IOException
	 */
	URL getURL() throws IOException;

	/**
	 * 放回一个URI句柄为当前资源
	 * @return
	 * @throws IOException
	 */
	URI getURI() throws IOException;

	/**
	 * 放回一个FIle句柄为当前资源
	 * @return
	 * @throws IOException
	 */
	File getFile() throws IOException;

	
	/**
	 * 确定当前资源的长度
	 * @return
	 * @throws IOException
	 */
	long contentLength() throws IOException;

	/**
	 * 确定该资源最后修改的时间戳
	 * @return
	 * @throws IOException
	 */
	long lastModified() throws IOException;

	/**
	 * 在当前资源的相对位置创建资源
	 * @param relativePath
	 * @return
	 * @throws IOException
	 */
	Resource createRelative(String relativePath) throws IOException;

	/**
	 * 获取资源名称
	 * @return
	 */
	String getFilename();

	/**
	 * 获取资源描述
	 * @return
	 */
	String getDescription();

}

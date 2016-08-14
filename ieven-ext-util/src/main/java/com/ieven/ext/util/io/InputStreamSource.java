package com.ieven.ext.util.io;

import java.io.IOException;
import java.io.InputStream;
/**
 * 资源类的总接口，返回inputStream
 * 
 * @author lichong
 */
public interface InputStreamSource {

	/**
	 * 获取资源的inputStream
	 * @return
	 * @throws IOException
	 */
	InputStream getInputStream() throws IOException;

}

package com.ieven.ext.util.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 可写资源接口
 * @author lichong
 *
 */
public interface WritableResource extends Resource {
	/**
	 * 是否可写
	 * @return
	 */
	boolean isWritable();

	/**
	 * 获取outputstream
	 * @return
	 * @throws IOException
	 */
	OutputStream getOutputStream() throws IOException;

}

package com.ieven.ext.util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.ieven.ext.util.util.Assert;
import com.ieven.ext.util.util.ResourceUtils;

/**
 * Resource的抽象实现，实现一些公用的方法，为下一层抽象做准备
 * 
 * @author lichong
 */
public abstract class AbstractResource implements Resource {

	/**
	 * 判断当前资源是否存在
	 */
	public boolean exists() {
		try {
			return getFile().exists();
		}
		catch (IOException ex) {
			try {
				InputStream is = getInputStream();
				is.close();
				return true;
			}
			catch (Throwable isEx) {
				return false;
			}
		}
	}

	/**
	 * 直接返回true
	 */
	public boolean isReadable() {
		return true;
	}

	/**
	 * 由于文件没有打开不打开，直接返回false
	 */
	public boolean isOpen() {
		return false;
	}

	/**
	 * 由于没有文件，故直接返回资源不存在，为getFile方法提供
	 */
	public URL getURL() throws IOException {
		throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
	}

	/**
	 * 根据URL获取URI
	 */
	public URI getURI() throws IOException {
		URL url = getURL();
		try {
			return ResourceUtils.toURI(url);
		}
		catch (URISyntaxException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 获取资源文件，由于目前没有，直接返回异常
	 */
	public File getFile() throws IOException {
		throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
	}

	/**
	 * 获取资源长度
	 */
	public long contentLength() throws IOException {
		InputStream is = this.getInputStream();
		Assert.state(is != null, "resource input stream must not be null");
		try {
			long size = 0;
			byte[] buf = new byte[255];
			int read;
			while ((read = is.read(buf)) != -1) {
				size += read;
			}
			return size;
		}
		finally {
			try {
				is.close();
			}
			catch (IOException ex) {
			}
		}
	}

	/**
	 * 获取最后一次文件修改时间
	 */
	public long lastModified() throws IOException {
		long lastModified = getFileForLastModifiedCheck().lastModified();
		if (lastModified == 0L) {
			throw new FileNotFoundException(getDescription() +
					" cannot be resolved in the file system for resolving its last-modified timestamp");
		}
		return lastModified;
	}

	/**
	 * 获取最新一次修改的资源
	 * @return
	 * @throws IOException
	 */
	protected File getFileForLastModifiedCheck() throws IOException {
		return getFile();
	}

	
	public Resource createRelative(String relativePath) throws IOException {
		throw new FileNotFoundException("Cannot create a relative resource for " + getDescription());
	}

	/**
	 * 由于没有文件，直接返回空
	 */
	public String getFilename() {
		return null;
	}


	/**
	 * 覆写toString
	 */
	@Override
	public String toString() {
		return getDescription();
	}

	/**
	 * 判断两个对象是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj == this ||
			(obj instanceof Resource && ((Resource) obj).getDescription().equals(getDescription())));
	}

	/**
	 * 返回描述信息的hashcode
	 */
	@Override
	public int hashCode() {
		return getDescription().hashCode();
	}

}

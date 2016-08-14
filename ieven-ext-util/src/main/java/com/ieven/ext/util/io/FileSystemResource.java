package com.ieven.ext.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import com.ieven.ext.util.util.Assert;
import com.ieven.ext.util.util.StringUtils;

/**
 * 
 * @author lichong
 *
 */
public class FileSystemResource extends AbstractResource implements WritableResource {

	private final File file;

	private final String path;


	/**
	 * 创建一个新的FileSystemResource，根据提供的File
	 * @param file
	 */
	public FileSystemResource(File file) {
		Assert.notNull(file, "文件不能为空");
		this.file = file;
		this.path = StringUtils.cleanPath(file.getPath());
	}

	/**
	 * 创建一个新的FileSystemResource，根据提供的路径
	 * @param path
	 */
	public FileSystemResource(String path) {
		Assert.notNull(path, "路径不能为空");
		this.file = new File(path);
		this.path = StringUtils.cleanPath(path);
	}


	/**
	 * 获取当前资源的路径
	 * @return
	 */
	public final String getPath() {
		return this.path;
	}


	/**
	 * 判断当前文件是否存在
	 */
	@Override
	public boolean exists() {
		return this.file.exists();
	}

	/**
	 * 判断当前资源是否可读
	 */
	@Override
	public boolean isReadable() {
		return (this.file.canRead() && !this.file.isDirectory());
	}

	/**
	 * 获取inputstream
	 */
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	/**
	 * 返回资源的URL
	 */
	@Override
	public URL getURL() throws IOException {
		return this.file.toURI().toURL();
	}
	
	/**
	 * 返回资源的URI
	 */
	@Override
	public URI getURI() throws IOException {
		return this.file.toURI();
	}

	/**
	 * 返回资源对应File
	 */
	@Override
	public File getFile() {
		return this.file;
	}

	@Override
	public long contentLength() throws IOException {
		return this.file.length();
	}

	@Override
	public Resource createRelative(String relativePath) {
		String pathToUse = StringUtils.applyRelativePath(this.path, relativePath);
		return new FileSystemResource(pathToUse);
	}

	/**
	 * 返回文件名字
	 */
	@Override
	public String getFilename() {
		return this.file.getName();
	}

	public String getDescription() {
		return "file [" + this.file.getAbsolutePath() + "]";
	}


	// implementation of WritableResource

	/**
	 * 当前文件是否被标记为可写
	 */
	public boolean isWritable() {
		return (this.file.canWrite() && !this.file.isDirectory());
	}

	/**
	 * 获取outputstream
	 */
	public OutputStream getOutputStream() throws IOException {
		return new FileOutputStream(this.file);
	}


	@Override
	public boolean equals(Object obj) {
		return (obj == this ||
			(obj instanceof FileSystemResource && this.path.equals(((FileSystemResource) obj).path)));
	}

	/**
	 * 返回当前文件路径的hashcode
	 */
	@Override
	public int hashCode() {
		return this.path.hashCode();
	}

}

package com.ieven.ext.util.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.ieven.ext.util.util.Assert;
import com.ieven.ext.util.util.ClassUtils;
import com.ieven.ext.util.util.ObjectUtils;
import com.ieven.ext.util.util.StringUtils;

/**
 * resources为classpath的实现
 * 使用时提供classloader或者class或者需要加载的资源路径
 * 
 * @author lichong
 */
public class ClassPathResource extends AbstractFileResolvingResource {

	private final String path;

	private ClassLoader classLoader;

	private Class<?> clazz;


	/**
	 * 创建一个新的classPathResource为ClassLoader使用
	 * @param path class path后的绝对路径，如com/mctech/test.xml
	 */
	public ClassPathResource(String path) {
		this(path, (ClassLoader) null);
	}

	/**
	 * 创建一个新的classPathResource为ClassLoader使用
	 * @param path class path后的绝对路径，如com/mctech/test.xml
	 * @param classLoader 加载此资源的classloader
	 */
	public ClassPathResource(String path, ClassLoader classLoader) {
		Assert.notNull(path, "Path must not be null");
		String pathToUse = StringUtils.cleanPath(path);
		if (pathToUse.startsWith("/")) {
			pathToUse = pathToUse.substring(1);
		}
		this.path = pathToUse;
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
	}

	/**
	 * 创建一个新的classPathResource为Class使用
	 * @param path class path后的绝对路径，如com/mctech/test.xml
	 * @param clazz 加载此资源的classloader
	 */
	public ClassPathResource(String path, Class<?> clazz) {
		Assert.notNull(path, "Path must not be null");
		this.path = StringUtils.cleanPath(path);
		this.clazz = clazz;
	}

	/**
	 * 创建一个新的classPathResource为Class和classloader使用
	 * @param path class path后的绝对路径，如com/mctech/test.xml
	 * @param classLoader 加载此资源的classloader
	 * @param clazz 加载此资源的classloader
	 */
	protected ClassPathResource(String path, ClassLoader classLoader, Class<?> clazz) {
		this.path = StringUtils.cleanPath(path);
		this.classLoader = classLoader;
		this.clazz = clazz;
	}

	/**
	 * 获取此资源路径
	 * @return
	 */
	public final String getPath() {
		return this.path;
	}

	/**
	 * 获取加载此资源的classloader，如果没有，则返回此类的classloader
	 * @return
	 */
	public final ClassLoader getClassLoader() {
		return (this.clazz != null ? this.clazz.getClassLoader() : this.classLoader);
	}

	/**
	 * 判断路径下的资源是否存在
	 */
	@Override
	public boolean exists() {
		return (resolveURL() != null);
	}

	/**
	 * 将classpath路径下的资源解析成URL
	 * @return
	 */
	protected URL resolveURL() {
		if (this.clazz != null) {
			return this.clazz.getResource(this.path);
		}
		else if (this.classLoader != null) {
			return this.classLoader.getResource(this.path);
		}
		else {
			return ClassLoader.getSystemResource(this.path);
		}
	}

	/**
	 * 打开一个inputStream为classpath下的资源
	 */
	public InputStream getInputStream() throws IOException {
		InputStream is;
		if (this.clazz != null) {
			is = this.clazz.getResourceAsStream(this.path);
		}
		else if (this.classLoader != null) {
			is = this.classLoader.getResourceAsStream(this.path);
		}
		else {
			is = ClassLoader.getSystemResourceAsStream(this.path);
		}
		if (is == null) {
			throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
		}
		return is;
	}

	/**
	 * 获取URL
	 */
	@Override
	public URL getURL() throws IOException {
		URL url = resolveURL();
		if (url == null) {
			throw new FileNotFoundException(getDescription() + " cannot be resolved to URL because it does not exist");
		}
		return url;
	}

	/**
	 * 在给定相对路径下创建一个ClassPathResource（非创建一个文件！！）
	 */
	@Override
	public Resource createRelative(String relativePath) {
		String pathToUse = StringUtils.applyRelativePath(this.path, relativePath);
		return new ClassPathResource(pathToUse, this.classLoader, this.clazz);
	}

	/**
	 * 获取资源文件名称
	 */
	@Override
	public String getFilename() {
		return StringUtils.getFilename(this.path);
	}

	/**
	 * 获取资源描述
	 */
	public String getDescription() {
		StringBuilder builder = new StringBuilder("class path resource [");
		String pathToUse = path;
		if (this.clazz != null && !pathToUse.startsWith("/")) {
			builder.append(ClassUtils.classPackageAsResourcePath(this.clazz));
			builder.append('/');
		}
		if (pathToUse.startsWith("/")) {
			pathToUse = pathToUse.substring(1);
		}
		builder.append(pathToUse);
		builder.append(']');
		return builder.toString();
	}

	/**
	 *判断两个classpathResource是否完全相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof ClassPathResource) {
			ClassPathResource otherRes = (ClassPathResource) obj;
			return (this.path.equals(otherRes.path) &&
					ObjectUtils.nullSafeEquals(this.classLoader, otherRes.classLoader) &&
					ObjectUtils.nullSafeEquals(this.clazz, otherRes.clazz));
		}
		return false;
	}

	/**
	 * 返回此资源的路径字符串的hashcode
	 */
	@Override
	public int hashCode() {
		return this.path.hashCode();
	}

}

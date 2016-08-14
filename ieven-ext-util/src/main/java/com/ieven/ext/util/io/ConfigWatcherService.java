package com.ieven.ext.util.io;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ieven.ext.util.excepiton.ConfigException;
import com.ieven.ext.util.factory.MThreadFactory;

/**
 * 配置文件监控服务
 * 
 * @author lichong
 *
 */
public class ConfigWatcherService {

	private static ExecutorService service = Executors.newCachedThreadPool(new MThreadFactory());

	/**
	 * 用于存储指定路径下的监听器 key：文件夹路径 value：注册的监听
	 */
	private Map<String, ConfigListener> map = new ConcurrentHashMap<>();

	/**
	 * jdk7监视服务
	 */
	private WatchService watcher;

	/**
	 * 当前监控状态
	 */
	private volatile boolean running = false;

	private ConfigWatcherService() {
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ConfigException("文件监控初始化失败", e);
		}
	}

	private static class ConfigWatcherServiceInnner {
		private static ConfigWatcherService service = new ConfigWatcherService();
	}

	/**
	 * 获取监控服务实例
	 * 
	 * @return
	 */
	public static ConfigWatcherService getInstance() {
		return ConfigWatcherServiceInnner.service;
	}

//	/**
//	 * 启动监听服务
//	 */
//	public void start() {
//		running = true;
//		while (true) {
//			WatchKey key = null;
//			try {
//				key = watcher.take();
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				throw new ConfigException("配置文件监控被中断了", e1);
//			}
//			for (WatchEvent<?> event : key.pollEvents()) {
//				WatchEvent.Kind kind = event.kind();
//
//				if (kind == OVERFLOW) {// 事件可能lost or discarded
//					continue;
//				}
//				WatchEvent<Path> e = (WatchEvent<Path>) event;
//				noticeObserver(key, e);
//			}
//			if (!key.reset()) {
//				break;
//			}
//		}
//	}

//	/**
//	 * 在指定路径上添加监听
//	 * 
//	 * @param path
//	 */
//	protected void attach(Path path) {
//		try {
//			path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
//			if (!running) {
//				start();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			throw new ConfigException("注册监控失败", e);
//		}
//	}

	/**
	 * 当状态发生变化时通知指定listener
	 * 
	 * @param key
	 * @param e
	 */
	public void noticeObserver(WatchKey key, WatchEvent<Path> e) {
		if (e.kind().name().equals("ENTRY_MODIFY")) {
			map.get(((Path) key.watchable()).toString()).onModify(e);
		} else if (e.kind().name().equals("ENTRY_CREATE")) {
			map.get(((Path) key.watchable()).toString()).onCreate(e);
		} else if (e.kind().name().equals("ENTRY_DELETE")) {
			map.get(((Path) key.watchable()).toString()).onDelete(e);
		}
	}

	/**
	 * 添加listener
	 * 
	 * @param path
	 * @param listener
	 */
	public void addListener(Path path, ConfigListener listener) {
		map.put(path.toString(), listener);
		service.submit(new WatcherThread(path));
	}

	private class WatcherThread implements Runnable {

		private Path path;

		public WatcherThread(Path path) {
			// TODO Auto-generated constructor stub
			this.path = path;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			while (true) {
				WatchKey key = null;
				try {
					key = watcher.take();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					throw new ConfigException("配置文件监控被中断了", e1);
				}
				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind kind = event.kind();

					if (kind == OVERFLOW) {// 事件可能lost or discarded
						continue;
					}
					WatchEvent<Path> e = (WatchEvent<Path>) event;
					noticeObserver(key, e);
				}
				if (!key.reset()) {
					break;
				}
			}
		}

	}
}

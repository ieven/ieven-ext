package com.ieven.ext.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface MongoManager {
	/**
	 * 获取mongoClient
	 * 
	 * @return
	 */
	public MongoClient getMongoClient();

	/**
	 * 获取mongoDatabase
	 * 
	 * @return
	 */
	public MongoDatabase getMongoDatabase();

	/**
	 * 初始化
	 */
	public void init();
}

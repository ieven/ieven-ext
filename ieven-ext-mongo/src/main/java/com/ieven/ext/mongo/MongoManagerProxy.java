package com.ieven.ext.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * mongo数据库管理者代理
 * @author lichong
 *
 */
public class MongoManagerProxy {
	/**
	 * 获取mongoClient
	 * @return
	 */
	public static MongoClient getMongoClient(){
		return MongoManager.getInstance().getMongoClient();
	}
	
	/**
	 * 获取mongoDatabase
	 * @return
	 */
	public static MongoDatabase getMongoDatabase(){
		return MongoManager.getInstance().getDatabase();
	}
	
	public static void init(){
		MongoManager.getInstance();
	}
}

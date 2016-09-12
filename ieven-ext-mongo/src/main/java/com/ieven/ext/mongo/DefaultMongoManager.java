package com.ieven.ext.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 默认mongo数据库管理者
 * @author lichong
 *
 */
public class DefaultMongoManager implements MongoManager{
	
	@Override
	public MongoClient getMongoClient(){
		return MongoInstance.getInstance().getMongoClient();
	}
	
	@Override
	public MongoDatabase getMongoDatabase(){
		return MongoInstance.getInstance().getDatabase();
	}
	
	@Override
	public void init(){
		MongoInstance.getInstance();
	}
}

package com.ieven.ext.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ieven.ext.mongo.exception.MongoInitException;
import com.ieven.ext.mongo.po.MongoBean;
import com.ieven.ext.util.util.StringUtils;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

/**
 * mongo连接管理者
 * @author lichong
 *
 */
public class MongoManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoManager.class);
	
	private MongoClient mongoClient;
	
	private MongoDatabase database;
	
	/**
	 * 通过构造函数初始化数据连接
	 */
	private MongoManager(){
		MongoBean bean = new MongoBean().init();
		MongoCredential credential = null;
		if(bean.isAuthentication()){
			credential = MongoCredential.createCredential(bean.getUserName(),bean.getAuthenticationDbName(),bean.getPassword().toCharArray());
		}
		//此项为设置数据库连接属性，如连接池，暂时不设置
		Builder builder = MongoClientOptions.builder();
		if(StringUtils.hasLength(bean.getDescription())){
			builder.description(bean.getDescription());
		}
		if(bean.getMinConnectionsPerHost()!=-1){
			builder.minConnectionsPerHost(bean.getMinConnectionsPerHost());
		}
		if(bean.getConnectionsPerHost()!=-1){
			builder.connectionsPerHost(bean.getConnectionsPerHost());
		}
		if(bean.getThreadsAllowedToBlockForConnectionMultiplier()!=-1){
			builder.threadsAllowedToBlockForConnectionMultiplier(bean.getThreadsAllowedToBlockForConnectionMultiplier());
		}
		if(bean.getServerSelectionTimeout()!=-1){
			builder.serverSelectionTimeout(bean.getServerSelectionTimeout());
		}
		if(bean.getMaxWaitTime()!=-1){
			builder.maxWaitTime(bean.getMaxWaitTime());
		}
		if(bean.getMaxConnectionIdleTime()!=-1){
			builder.maxConnectionIdleTime(bean.getMaxConnectionIdleTime());
		}
		if(bean.getMaxConnectionLifeTime()!=-1){
			builder.maxConnectionLifeTime(bean.getMaxConnectionLifeTime());
		}
		if(bean.getConnectTimeout()!=-1){
			builder.connectTimeout(bean.getConnectTimeout());
		}
		if(bean.getSocketTimeout()!=-1){
			builder.socketTimeout(bean.getSocketTimeout());
		}
		if(bean.isSocketKeepAlive()!=null){
			builder.socketKeepAlive(bean.isSocketKeepAlive());
		}
		if(StringUtils.hasLength(bean.getReadPreference())){
			if(bean.getReadPreference().equalsIgnoreCase("primary")){
				builder.readPreference(ReadPreference.primary());
			}
			else if(bean.getReadPreference().equalsIgnoreCase("primaryPreferred")){
				builder.readPreference(ReadPreference.primaryPreferred());
			}
			else if(bean.getReadPreference().equalsIgnoreCase("secondary")){
				builder.readPreference(ReadPreference.secondary());
			}
			else if(bean.getReadPreference().equalsIgnoreCase("secondaryPreferred")){
				builder.readPreference(ReadPreference.secondaryPreferred());
			}
			else if(bean.getReadPreference().equalsIgnoreCase("nearest")){
				builder.readPreference(ReadPreference.nearest());
			}
		}
		if(StringUtils.hasLength(bean.getWriteConcern())){
			if(bean.getWriteConcern().equals("ACKNOWLEDGED")){
				builder.writeConcern(WriteConcern.ACKNOWLEDGED);
			}
			else if(bean.getWriteConcern().equals("FSYNC_SAFE")){
				builder.writeConcern(WriteConcern.FSYNC_SAFE);
			}
			else if(bean.getWriteConcern().equals("FSYNCED")){
				builder.writeConcern(WriteConcern.FSYNCED);
			}
			else if(bean.getWriteConcern().equals("JOURNAL_SAFE")){
				builder.writeConcern(WriteConcern.JOURNAL_SAFE);
			}
			else if(bean.getWriteConcern().equals("JOURNALED")){
				builder.writeConcern(WriteConcern.JOURNALED);
			}
			else if(bean.getWriteConcern().equals("MAJORITY")){
				builder.writeConcern(WriteConcern.MAJORITY);
			}
			else if(bean.getWriteConcern().equals("NORMAL")){
				builder.writeConcern(WriteConcern.NORMAL);
			}
			else if(bean.getWriteConcern().equals("REPLICA_ACKNOWLEDGED")){
				builder.writeConcern(WriteConcern.REPLICA_ACKNOWLEDGED);
			}
		}
		if(StringUtils.hasLength(bean.getReadConcern())){
			if(bean.getReadConcern().equals("DEFAULT")){
				builder.readConcern(ReadConcern.DEFAULT);
			}
			else if(bean.getReadConcern().equals("LOCAL")){
				builder.readConcern(ReadConcern.LOCAL);
			}
			else if(bean.getReadConcern().equals("MAJORITY")){
				builder.readConcern(ReadConcern.MAJORITY);
			}
		}
		MongoClientOptions options = builder.build();
		List<ServerAddress> serverAddresses = new ArrayList<>();
		for(int i=0;i<bean.getMongoUrl().size();i++){
			serverAddresses.add(new ServerAddress(bean.getMongoUrl().get(i), bean.getMongoPort().get(i)));
		}
		if(bean.isAuthentication())
		{
			mongoClient = new MongoClient(serverAddresses, Arrays.asList(credential),options);
		}
		else
		{
			mongoClient = new MongoClient(serverAddresses,options);
		}
		database = mongoClient.getDatabase(bean.getMongoDbName());
		LOGGER.info("mongo的配置为：\n"+bean.toString());
		LOGGER.info("当前database中的表列表为：\n");
		try {
			for(String name : database.listCollectionNames()){
				LOGGER.info(name+"\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new MongoInitException("mongo初始化失败", e);
		}
	}
	
	private static class MongoManagerInner{
		private static MongoManager mongoManager = new MongoManager();
	}
	
	public static MongoManager getInstance(){
		return MongoManagerInner.mongoManager;
	}

	/**
	 * 获取mongoclient
	 * @return
	 */
	public MongoClient getMongoClient() {
		return mongoClient;
	}

	/**
	 * 获取设定的database
	 * @return
	 */
	public MongoDatabase getDatabase() {
		return database;
	}
}

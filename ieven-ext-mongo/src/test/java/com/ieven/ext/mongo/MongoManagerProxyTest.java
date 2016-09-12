package com.ieven.ext.mongo;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoManagerProxyTest {
	
	private static MongoManagerProxyTest managerProxyTest = new MongoManagerProxyTest();
	
	public static void main(String[] args) {
		Document doc = new Document("mongo_id", 10001).append("mongo_text", "success");
		managerProxyTest.getCollection().insertOne(doc);
		Document result = managerProxyTest.getCollection().find(eq("mongo_id", 10001)).first();
		System.out.println(result.get("mongo_id"));
		System.out.println(result.get("mongo_text"));
	}
	
	/**
	 * 获取集合，相当于关系数据库中的表
	 * @return
	 */
	private MongoCollection<Document> getCollection() {
		return MongoManagerProxy.getMongoDatabase().getCollection("ieven-mongo");
	}
}

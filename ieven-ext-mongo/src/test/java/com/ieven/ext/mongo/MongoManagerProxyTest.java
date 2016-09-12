package com.ieven.ext.mongo;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoManagerProxyTest {
	
	public static void main(String[] args) {
		MongoManager manager = new DefaultMongoManager();
		//获取集合，相当于关系型数据库的获取表
		MongoCollection<Document> collection = manager.getMongoDatabase().getCollection("ieven-mongo");
		
		Document doc = new Document("mongo_id", 10001).append("mongo_text", "success");
		collection.insertOne(doc);
		Document result = collection.find(eq("mongo_id", 10001)).first();
		System.out.println(result.get("mongo_id"));
		System.out.println(result.get("mongo_text"));
	}
	
}

package com.ieven.ext.mongo.po;

public class MongoBeanTest {
	
	public static void main(String[] args) {
		MongoBean bean = new MongoBean();
		bean.init();
		System.out.println(bean.toString());
	}
	
}

package com.danielev86.mongoexample.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.mongodb.client.MongoClient;

@ComponentScan(basePackages="com.danielev86")
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppMain.class);
		MongoClient mongoClient = ctx.getBean(MongoClient.class);
		for (String coll : mongoClient.listDatabaseNames()) {
			System.out.println(coll);
		}
		mongoClient.close();
	}

}

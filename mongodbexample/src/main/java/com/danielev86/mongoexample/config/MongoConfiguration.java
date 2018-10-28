package com.danielev86.mongoexample.config;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfiguration implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CONNECTION_MONGO_STRING = "mongodb://localhost:27017";

	@Bean
	public MongoClient mongoClient() {
		ConnectionString connectionString = new ConnectionString(CONNECTION_MONGO_STRING);
		MongoClient mongoClient = MongoClients.create(connectionString);
		return mongoClient;
	}

}

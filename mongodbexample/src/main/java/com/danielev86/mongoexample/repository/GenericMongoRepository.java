package com.danielev86.mongoexample.repository;

import java.io.Serializable;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class GenericMongoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private ConversionService converter;

	private MongoDatabase database;

	public void initializeDbConfiguration(String dbName) {
		setDatabase(mongoClient.getDatabase(dbName));
	}

	public MongoCollection<Document> getCollectionByName(String collectionName) {
		return getDatabase().getCollection(collectionName);
	}
	
	public Long removeOneElement(Bson deleteQuery, String collectioName) {
		
		return getCollectionByName(collectioName)
			.deleteOne(deleteQuery)
			.getDeletedCount();
	}
	
	public Long removeManyElement(Bson deleteQuery, String collectionName) {
		
		return getCollectionByName(collectionName)
				.deleteMany(deleteQuery)
				.getDeletedCount();
		
	}
	
	public void addElement(Document insertQuery, String collectionName) {
		getCollectionByName(collectionName)
			.insertOne(insertQuery);
	}

	public void closeConnection() {
		mongoClient.close();
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public ConversionService getConverter() {
		return converter;
	}

	public void setConverter(ConversionService converter) {
		this.converter = converter;
	}

}

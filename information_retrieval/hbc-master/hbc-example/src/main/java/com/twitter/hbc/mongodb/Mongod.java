package com.twitter.hbc.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;

import static com.mongodb.client.model.Filters.*;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Projections.fields;

import java.util.List;
import java.util.Arrays;

public class Mongod {
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> curCollection;
	
	//instantiate mongodb
	public Mongod(String dbname) {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(dbname);
	}
	
	//create handler
	public void createCollection(String colname) {
		database.createCollection(colname);
	}
	
	//list all handlers
	public MongoIterable<String> listCollectionName() {
		return database.listCollectionNames();
	}
	
	//select the handler
	public void setCurCollection(String colname) {
		curCollection = database.getCollection(colname);
	}
	
	//insert docs
	public void insertDocs(List<Document> docs) {
		curCollection.insertMany(docs);
	}
	
	//create indexes for the field "location"
	public void indexLocation() {
		curCollection.createIndex(Indexes.ascending("location"));
		
	}
	
	//create indexes for the field "text"
	public void indexText() {
		curCollection.createIndex(Indexes.text("text"));
	}
	
	//find the relevant items with fields that we care about
	public FindIterable<Document> releCollection(String text, Point refPoint, double minD, double maxD) {
		return curCollection.find(and(Filters.text(text), Filters.near("location", refPoint, minD, maxD))).projection(fields(include(Arrays.asList("text", "location")), excludeId()));
	}
	
	//drop the current collection
	public void dropCurCollection() {
		curCollection.drop();
	}
	
	

}

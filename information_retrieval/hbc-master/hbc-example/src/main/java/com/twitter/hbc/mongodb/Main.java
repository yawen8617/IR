package com.twitter.hbc.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.twitter.hbc.phtis.PostDatum;
import com.twitter.hbc.phtis.UserDatum;

import java.time.LocalDateTime;
import org.json.JSONObject;

import com.twitter.hbc.example.FilterStream;



public class Main {
	
	public static void main(final String[] args) throws InterruptedException {
		//initiate a server
		Server server = new Server();
		List<Document> documents = new ArrayList<Document>();
		
		//initiate a client and collect to the local server
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		
		//get handle to "mydb" database
		MongoDatabase database = mongoClient.getDatabase("mydb");
		
		//get a handle to the "test" collenction
		MongoCollection<Document> collection = database.getCollection("test");
		
		//drop all the data in it
		collection.drop();
		
		//instantiate a FilterStream
		FilterStream filterStream = new FilterStream(args[0], args[1], args[2], args[3]);
		BlockingQueue<String> queue = filterStream.instantiateCC();
		for (int msgRead = 0; msgRead < 500; msgRead++) {
			String msg = queue.take();
			JSONObject json = new JSONObject(msg);
			Document userDoc = server.writeUserDoc(json);
			Document postDoc = server.writePostDoc(json);
			documents.add(userDoc);
			documents.add(postDoc);
		}
		
		collection.insertMany(documents);
	}
	
	
	
	

}

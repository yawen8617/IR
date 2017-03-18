package com.twitter.hbc.mongodb;

import com.twitter.hbc.phtis.PostDatum;
import com.twitter.hbc.phtis.UserDatum;

import java.time.LocalDateTime;

import org.json.JSONObject;

import org.bson.Document;
/**
 * 
 * @author jianyusu
 * harmonize data
 *
 */


public class Server {

	// store JSON users
	public UserDatum processUserDatum(JSONObject usrJSON) {
		String usrId =  usrJSON.getJSONObject("user").getString("id"); 
		String location = usrJSON.getJSONObject("user").getString("location");
		UserDatum usrDatum = new UserDatum(usrId, location);
		return usrDatum;
	}
	
	// store JSON posts
	public PostDatum processPostDatum(JSONObject postJSON) {
		String usrId =  postJSON.getJSONObject("user").getString("id"); 
		String postId = postJSON.getString("id");
		String text = postJSON.getString("text");
		String geo = postJSON.getString("geo");
		String createdTime = postJSON.getString("created_at");
		LocalDateTime created_at = LocalDateTime.parse(createdTime);
		PostDatum postDatum = new PostDatum(usrId, postId, text, created_at);
		return postDatum;
		
	}
	
	//transfer usrJSON to doc for database
	public Document writeUserDoc(JSONObject usrJSON) {
		String usrId =  usrJSON.getJSONObject("user").getString("id"); 
		String location = usrJSON.getJSONObject("user").getString("location");
		Document doc = new Document("userid", usrId)
                .append("location", location);
        return doc;      
	}
	
	//transfer usrJSON to doc for database
	public Document writePostDoc(JSONObject postJSON) {
		String usrId =  postJSON.getJSONObject("user").getString("id"); 
		String geo = postJSON.getJSONObject("user").getString("geo");
		String postId = postJSON.getString("id");
		String text = postJSON.getString("text");
		String createdTime = postJSON.getString("created_at");
		
		Document doc = new Document("userid", usrId)
                .append("postId", postId )
                .append("createdTime", createdTime)
                .append("text", text);
        return doc;      
	}
	
	
	

}

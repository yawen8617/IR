package com.twitter.hbc.phtis;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PostDatum extends Datum {
	private String usrId;
	private String postId;
	private String text;
	private LocalDateTime createdTime;
	private JSONParser parser = new JSONParser();

	public PostDatum(String usrId, String postId, String text, LocalDateTime createdTime) {
		super(usrId);
		this.postId = postId;
		this.text = text;
		this.createdTime = createdTime;
	}
	
	public String getPostId() {
		return postId;
	}
	
	public String getText() {
		return text;
	}
	
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	
	public JSONObject toJSON() throws ParseException {
		String str = "[\n" +
				"{" + "userId" +":" + usrId + ",\n" +
				"postId" +":" + postId + ",\n" +
				"text" +":" + text + ",\n" +
				"createdTime" +":" + createdTime + ",\n" +
				"}\n" +
			 "]";
		JSONObject json = new JSONObject(str);
		return json;
	}

}

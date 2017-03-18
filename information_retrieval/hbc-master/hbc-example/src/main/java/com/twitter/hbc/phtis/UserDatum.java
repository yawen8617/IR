package com.twitter.hbc.phtis;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserDatum extends Datum {
	private String usrId;
	private String location;
	private JSONParser parser = new JSONParser();
	
	public UserDatum(String usrId, String location) {
		super(usrId);
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public JSONObject toJSON() throws ParseException {
		String str = "[\n" +
				"{" + "userId" +":" + usrId + ",\n" +
				"location" + ":" + location + ",\n" +
				"}\n" +
			 "]";
		JSONObject json = new JSONObject(str);
		return json;
		
	}
	
	

}

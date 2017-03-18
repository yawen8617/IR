package com.twitter.hbc.phtis;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Datum {
	private String usrId;
	private JSONParser parser = new JSONParser();
	
	public Datum (String usrId) {
		this.usrId = usrId;
	}
	
	public void setUsrId (String usrId) {
		this.usrId = usrId;
	}
	
	public JSONObject toJSON() throws ParseException {
		String str = "[\n" +
						"{" + "userId" +":" + usrId + ",\n" +
						"}\n" +
					 "]";
		JSONObject json = new JSONObject(str);
		return json;			
	}
}

package com.twitter.hbc.phtis;

import java.util.ArrayList;

import com.twitter.hbc.phtis.Datum;
import com.twitter.hbc.phtis.PostDatum;
import com.twitter.hbc.phtis.UserDatum;


public class LocalDataStore {
	private ArrayList<Datum> data;
	
	public LocalDataStore() {
		data = new ArrayList<Datum>();
	}
	
	public void newReadingHasArrived(Datum datum) {
		data.add(datum);
		System.out.println("a new datum is added");
	}
	
	public ArrayList<PostDatum> getPostData() {
		
		ArrayList<PostDatum> PostData = new ArrayList<PostDatum>();
		
		for(int i = 0; i < data.size(); ++i) {
			if(data.get(i) instanceof PostDatum) {
				PostData.add((PostDatum)data.get(i));
			}
		}
		return PostData;
	}
	
	public ArrayList<UserDatum> getUserData() {
		
		ArrayList<UserDatum> UserData = new ArrayList<UserDatum>();
		
		for(int i = 0; i < data.size(); ++i) {
			if(data.get(i) instanceof UserDatum) {
				UserData.add((UserDatum)data.get(i));
			}
		}
		return UserData;
	}
}

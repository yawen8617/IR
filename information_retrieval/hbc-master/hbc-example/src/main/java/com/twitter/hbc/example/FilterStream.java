
package com.twitter.hbc.example;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import com.twitter.hbc.core.endpoint.Location;

public class FilterStream {
	private String consumerKey;
	private String consumerSecret;
	private String token;
	private String secret;
	private List<Location> location;
	private List<String> keyWords;
	private BlockingQueue<String> queue;
	
	//overloading constructors
	public FilterStream(String consumerKey, String consumerSecret, String token, String secret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.token = token;
		this.secret = secret;
		this.queue = new LinkedBlockingQueue<String>(10000);
	    
	}
	
	public FilterStream(String consumerKey, String consumerSecret, String token, String secret, List<String> keywords) {
		this(consumerKey, consumerSecret, token, secret);
		this.keyWords = keywords;
	}
	
	public FilterStream(String consumerKey, String consumerSecret, String token, String secret, List<String> keywords, List<Location> location) {
		this(consumerKey, consumerSecret, token, secret, keywords);
		this.location = location;
	}
	
	public BlockingQueue<String> instantiateCC() {
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
	    if(!keyWords.isEmpty()) {
	    	endpoint.trackTerms(keyWords);
	    }
	    if(location != null) {
	    	endpoint.locations(location);
	    }
	    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
	    // Authentication auth = new BasicAuth(username, password);

	    // Create a new BasicClient. By default gzip is enabled.
	    Client client = new ClientBuilder()
	            .hosts(Constants.STREAM_HOST)
	            .endpoint(endpoint)
	            .authentication(auth)
	            .processor(new StringDelimitedProcessor(queue))
	            .build();

	    // Establish a connection
	    client.connect();
	    
	    return queue;
	    }
	}



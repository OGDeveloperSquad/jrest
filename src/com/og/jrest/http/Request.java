package com.og.jrest.http;

import com.og.jrest.logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is a model of an HTTP message. Contains things like Headers,
 * method (i.e. GET, POST, PUT, or DELETE), and request body. Pretty
 * straightforward
 * 
 * @author matthew.shoemaker
 * @author Hussain
 *
 */
public class Request {

	
	public String verb;
	public String uri;
	public String httpVersion;
	public Map<String, String[]> headers;
	public String body;
	
	
	
	public Request(String httpRequest ) {
		
		this.headers = new HashMap<String, String[]>();
		this.parseRequest(httpRequest);
		Log.debug("New request class instantiated!");
		
	}
	
	
	/*
	 * 
	 * Parses a given string http request
	 * 
	 * @param httpRequest
	 * 
	 */
	private void  parseRequest(String httpRequest) {
		Scanner req  = new Scanner(httpRequest);

		//parse request line assuming format request-method-name request-URI HTTP-version
		this.verb = req.next();
		this.uri = req.next();
		this.httpVersion = req.next();

		req.nextLine();

		//parse request headers into map
		String headerLine = req.nextLine();
		while (!headerLine.equals("")){
			
			int colonI = headerLine.indexOf(":");
			String name = headerLine.substring(0, colonI);
			String vals = headerLine.substring(colonI + 1);

			String[] values = vals.split(",");
			
			//trim values of whitespaces
			for(int i = 0; i < values.length; i++) {
				values[i] = values[i].trim();
			}

			this.headers.put(name, values);

			headerLine = req.nextLine();
			
		}

		String body = "";
		while(req.hasNext()){
			body = body + req.nextLine();
		}
		
		this.body = body;

		
	


		req.close();
	}


	

}
package com.og.jrest.http;

import com.og.jrest.exceptions.HttpClientErrorException;
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

	
	private String verb;
	private String uri;
	private String httpVersion;
	private Map<String, String[]> headers;
	private String body;
	
	
	
	public Request(String httpRequest ) {
		
		this.headers = new HashMap<String, String[]>();
		try {
			this.parseRequest(httpRequest);
		} catch (HttpClientErrorException e) {
			// TODO Auto-generated catch block
			System.out.println(e.errorCode);
			System.out.println(e.getErrorResponseMessage());

			
		}
		//for fun
		Log.debug("New request class instantiated!");
		
	}
	
	
	/*
	 * 
	 * Parses a given string http request
	 * 
	 * @param httpRequest
	 * 
	 */
	private void  parseRequest(String httpRequest) throws HttpClientErrorException{
		try {
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
	
			//parse body into string if there is one
			String body = "";
			while(req.hasNext()){
				body = body + req.nextLine();
			}
			
			this.body = body;
			req.close();
		}catch(Exception e) {
			throw new HttpClientErrorException(400);
		}
		
	
		

		
	}

	public String getVerb() {
		return this.verb;
	}
	
	public String getURI() {
		return this.uri;
	}
	
	public String getHttpVersion() {
		return this.httpVersion;
	}
	
	public Map<String, String[]> getHeaders(){
		return this.headers;
	}
	
	public String getBody() {
		return this.body;
	}
	
	
	

}

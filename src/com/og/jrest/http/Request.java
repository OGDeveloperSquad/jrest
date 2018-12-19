package com.og.jrest.http;

import com.og.jrest.logging.Log;

/**
 * This class is a model of an HTTP message. Contains things like Headers,
 * method (i.e. GET, POST, PUT, or DELETE), and request body. Pretty
 * straightforward
 * 
 * @author matthew.shoemaker
 *
 */
public class Request {

	public Request() {
		// Just for fun
		Log.debug("New request class instantiated!");
	}

	public String host;
	public String verb = "GET";

}

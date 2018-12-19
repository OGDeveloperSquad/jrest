package com.og.jrest.http;

import java.util.Map;

import com.og.jrest.logging.Log;

/**
 * This class is a model for the http response sent back to the end user.
 * 
 * @author matthew.shoemaker
 *
 */
public abstract class Response {

	public Response() {
		// Just for fun
		Log.debug("Response class instantiated!");
	}

	public Map<String, String> headers;

}

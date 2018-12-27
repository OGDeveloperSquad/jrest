package com.og.jrest.http;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a model for an HTTP response code. It contains the response
 * code and its corresponding reason phrase.
 * 
 * @author matthew.shoemaker
 *
 */
public class ResponseCode {

	private int statusCode;
	private String reasonPhrase;

	/**
	 * Repository to hold response codes and their corresponding reason phrases.
	 */
	static Map<Integer, String> statusCodeMap;

	static {
		statusCodeMap = new HashMap<Integer, String>();
		statusCodeMap.put(200, "OK");
		statusCodeMap.put(201, "Created");
		statusCodeMap.put(204, "No Content");
		statusCodeMap.put(304, "Not Modified");
		statusCodeMap.put(400, "Bad Request");
		statusCodeMap.put(401, "Unauthorized");
		statusCodeMap.put(403, "Forbidden");
		statusCodeMap.put(404, "Not Found");
		statusCodeMap.put(409, "Conflict");
		statusCodeMap.put(500, "Internal Server Error");
	}

	public ResponseCode(int statusCode) {
		this.statusCode = statusCode;
		this.reasonPhrase = ResponseCode.statusCodeMap.get(statusCode);
	}

	@Override
	public String toString() {
		return String.format("%s %s", Integer.toString(this.statusCode), this.reasonPhrase);
	}

}

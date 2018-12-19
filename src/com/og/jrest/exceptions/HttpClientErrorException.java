package com.og.jrest.exceptions;

import java.util.HashMap;
import java.util.Map;

public class HttpClientErrorException extends HttpException{
	
	private static Map<Integer, String> errorResponses;
	static {
		errorResponses = new HashMap<Integer,String>();
		errorResponses.put(400, "Bad Request");
		errorResponses.put(401, "Unauthorized");
		errorResponses.put(403, "Forbidden");
		errorResponses.put(404, "Not Found");
		errorResponses.put(409, "Conflict");
		
	}
	
		

	public HttpClientErrorException(int status) {
		super(status);
	}
	
	public String getErrorResponseMessage() {
		return errorResponses.get(this.getErrorCode());
	}

}

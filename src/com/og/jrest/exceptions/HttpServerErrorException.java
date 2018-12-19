package com.og.jrest.exceptions;

import java.util.HashMap;
import java.util.Map;

public class HttpServerErrorException extends HttpException{

	
	private static Map<Integer, String> errorResponses;
	static {
		errorResponses = new HashMap<Integer,String>();
		errorResponses.put(500, "Internal Server Error");
		
		
	}
	
	public HttpServerErrorException(int status) {
		super(status);
		
	}

}

package com.og.jrest.exceptions;

public class HttpException extends Exception{
	
	private int errorCode;



	public HttpException(int status) {
		this.errorCode = status;
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
	
	
	
}

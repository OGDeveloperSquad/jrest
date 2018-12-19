package com.og.jrest.exceptions;

public class HttpException extends Exception{
	
	public int errorCode;



	public HttpException(int status) {
		this.errorCode = status;
	}
	
	
	
}

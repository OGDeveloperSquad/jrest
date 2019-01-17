package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class InvalidRouteException extends JRestException {

	public InvalidRouteException(String message) {
		super(message);
	}

	public InvalidRouteException(String message, Throwable cause) {
		super(message, cause);
	}

}

package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class InvalidRouteException extends JRestException {

	private static final int STATUS_CODE = 500;

	public InvalidRouteException(String message) {
		super(message);
	}

	public InvalidRouteException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}

}

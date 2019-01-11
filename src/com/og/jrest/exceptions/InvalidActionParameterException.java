package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class InvalidActionParameterException extends JRestException {

	private static final int STATUS_CODE = 500;

	public InvalidActionParameterException(String message) {
		super(message);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}

}

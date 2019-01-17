package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ParameterBindingException extends JRestServerException {

	private static final int STATUS_CODE = 500;

	public ParameterBindingException(String message) {
		super(message);
	}

	public ParameterBindingException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}

}

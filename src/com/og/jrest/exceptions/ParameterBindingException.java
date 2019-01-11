package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ParameterBindingException extends JRestException {

	private static final int STATUS_CODE = 500;

	public ParameterBindingException(String message) {
		super(message);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}

}

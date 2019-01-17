package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ModelNotFoundException extends JRestServerException {

	private static final int STATUS_CODE = 500;

	public ModelNotFoundException(String message) {
		super("Unable to find a model with the name '" + message + "'");
	}

	public ModelNotFoundException(String message, Throwable e) {
		super("Unable to find a model with the name '" + message + "'", e);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}

}

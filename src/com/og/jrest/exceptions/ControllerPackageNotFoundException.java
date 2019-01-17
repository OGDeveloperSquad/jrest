package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ControllerPackageNotFoundException extends JRestServerException {

	private static final int STATUS_CODE = 500;

	public ControllerPackageNotFoundException(String message) {
		super(message);
	}

	public ControllerPackageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}

}

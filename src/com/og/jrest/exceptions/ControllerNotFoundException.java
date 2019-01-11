package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ControllerNotFoundException extends JRestException {

	private static final int STATUS_CODE = 404;

	public ControllerNotFoundException(String controllerName) {
		super(String.format("Unable to find controller with name '%sController'", controllerName));
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}
}

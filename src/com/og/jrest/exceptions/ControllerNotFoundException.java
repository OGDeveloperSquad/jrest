package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ControllerNotFoundException extends JRestException {

	private static final int STATUS_CODE = 404;

	public ControllerNotFoundException(String controllerName) {
		super(String.format("Unable to find controller with name '%sController'", controllerName));
	}

	public ControllerNotFoundException(String controllerName, Throwable cause) {
		super(String.format("Unable to find controller with name '%sController'", controllerName), cause);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}
}

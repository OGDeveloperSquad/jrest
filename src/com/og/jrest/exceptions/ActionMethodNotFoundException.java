package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ActionMethodNotFoundException extends JRestServerException {

	private static final int STATUS_CODE = 404;

	public ActionMethodNotFoundException(String controllerName, String actionName) {
		super(String.format("Unable to find action method '%s' on controller '%sController'", actionName,
				controllerName));
	}

	public ActionMethodNotFoundException(String controllerName, String actionName, Throwable cause) {
		super(String.format("Unable to find action method '%s' on controller '%sController'", actionName,
				controllerName), cause);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}
}

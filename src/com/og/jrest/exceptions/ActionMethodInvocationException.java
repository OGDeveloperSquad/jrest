package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class ActionMethodInvocationException extends JRestServerException {

	private static final int STATUS_CODE = 500;

	public ActionMethodInvocationException(String message) {
		super(message);
	}

	public ActionMethodInvocationException(String message, Exception cause) {
		super(message, cause);
	}

	@Override
	public StatusCode getStatusCode() {
		return new StatusCode(STATUS_CODE);
	}

}

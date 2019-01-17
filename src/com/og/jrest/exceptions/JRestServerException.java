package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public abstract class JRestServerException extends JRestException {

	public JRestServerException(String message) {
		super(message);
	}

	public JRestServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract StatusCode getStatusCode();

}

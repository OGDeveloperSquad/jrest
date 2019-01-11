package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;
import com.og.jrest.logging.Log;

@SuppressWarnings("serial")
public abstract class JRestException extends Exception {

	protected StatusCode statusCode;
	private String message;

	private static final String HEADER_SEPARATOR = ":    ";

	public JRestException(String message) {
		super(message);
		this.initialize(message);
	}

	public JRestException(String message, Throwable cause) {
		super(message, cause);
		this.initialize(message);
	}

	private void initialize(String message) {
		this.message = message;
		Log.exception(this);
	}

	@Override
	public String getMessage() {
		String exceptionType = this.getClass().getSimpleName();
		String message = exceptionType + HEADER_SEPARATOR + this.message;

		return message;
	}

	public abstract StatusCode getStatusCode();

}

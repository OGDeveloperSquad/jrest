package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public abstract class JRestException extends Exception {

	protected StatusCode statusCode;
	private String message;

	private static final String HEADER_SEPARATOR = ":    ";

	public JRestException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		String header = this.getClass().getSimpleName();
		String message = header + HEADER_SEPARATOR + this.message;

		return message;
	}

	public abstract StatusCode getStatusCode();

}

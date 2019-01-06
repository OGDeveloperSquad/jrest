package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public class JRestException extends Exception {

	private StatusCode statusCode;

	public JRestException(String message) {
		super(message);
	}

	public StatusCode getStatusCode() {
		return this.statusCode;
	}

	protected void setStatusCode(int code) {
		this.statusCode = new StatusCode(code);
	}
}

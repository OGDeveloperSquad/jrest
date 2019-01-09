package com.og.jrest.exceptions;

import com.og.jrest.http.StatusCode;

@SuppressWarnings("serial")
public abstract class JRestException extends Exception {

	protected StatusCode statusCode;

	public JRestException(String message) {
		super(message);
	}

	public StatusCode getStatusCode() {
		return this.statusCode;
	}

	protected abstract int getStatusCodeInteger();

}

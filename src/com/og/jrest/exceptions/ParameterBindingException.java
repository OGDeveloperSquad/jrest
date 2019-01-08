package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ParameterBindingException extends JRestException {

	public ParameterBindingException(String message) {
		super(message);
		this.setStatusCode(500);
	}

}

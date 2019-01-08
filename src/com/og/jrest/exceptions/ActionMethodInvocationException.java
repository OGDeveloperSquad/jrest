package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ActionMethodInvocationException extends JRestException {

	public ActionMethodInvocationException(String message) {
		super(message);
		this.setStatusCode(500);
	}

}

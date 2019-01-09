package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ActionMethodInvocationException extends JRestException {

	private static final String HEADER = "ActionMethodInvocationException:   ";
	private static final int STATUS_CODE = 500;

	public ActionMethodInvocationException(String message) {
		super(HEADER + message);
	}

	@Override
	protected int getStatusCodeInteger() {
		return STATUS_CODE;
	}

}

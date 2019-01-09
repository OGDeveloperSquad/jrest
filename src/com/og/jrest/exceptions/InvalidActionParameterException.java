package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class InvalidActionParameterException extends JRestException {

	private static final String HEADER = "InvalidActionParameterException:   ";
	private static final int STATUS_CODE = 500;

	public InvalidActionParameterException(String message) {
		super(HEADER + message);
	}

	@Override
	protected int getStatusCodeInteger() {
		return STATUS_CODE;
	}

}

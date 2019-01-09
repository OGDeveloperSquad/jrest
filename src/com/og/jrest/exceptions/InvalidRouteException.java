package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class InvalidRouteException extends JRestException {

	private static final String HEADER = "InvalidRouteException:   ";
	private static final int STATUS_CODE = 500;

	public InvalidRouteException(String message) {
		super(HEADER + message);
	}

	@Override
	protected int getStatusCodeInteger() {
		return STATUS_CODE;
	}

}

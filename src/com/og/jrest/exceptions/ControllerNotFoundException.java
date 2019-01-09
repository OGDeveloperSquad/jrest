package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ControllerNotFoundException extends JRestException {

	private static final String HEADER = "ControllerNotFoundException:   ";
	private static final int STATUS_CODE = 404;

	public ControllerNotFoundException(String controllerName) {
		super(String.format("%sUnable to find controller with name '%sController'", HEADER, controllerName));
	}

	@Override
	protected int getStatusCodeInteger() {
		return STATUS_CODE;
	}
}

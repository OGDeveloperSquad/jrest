package com.og.jrest.exceptions;

@SuppressWarnings("serial")
public class ParameterBindingException extends JRestException {

	private static final String HEADER = "ParameterBindingException:   ";
	private static final int STATUS_CODE = 500;

	public ParameterBindingException(String message) {
		super(HEADER + message);
	}

	@Override
	protected int getStatusCodeInteger() {
		return STATUS_CODE;
	}

}

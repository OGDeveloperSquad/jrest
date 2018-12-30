package com.og.jrest.logging;

public class ThrowableLogHandler extends LogHandler {

	public ThrowableLogHandler() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.err;
	}

}

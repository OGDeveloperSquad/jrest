package com.og.jrest.logging;

public class ThrowableLogHandler extends BaseLogHandler {

	public ThrowableLogHandler() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.err;
	}

}

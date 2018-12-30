package com.og.jrest.logging;

/**
 * Implementation of the BaseLogger to handle logging of errors.
 * 
 * @author Matthew.Shoemaker
 *
 */
class ErrorLogHandler extends LogHandler {

	protected ErrorLogHandler() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.err;
	}

}

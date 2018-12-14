package com.og.jrest.logging;

/**
 * Implementation of the BaseLogger to handle logging of errors.
 * 
 * @author Matthew.Shoemaker
 *
 */
class ErrorLogger extends BaseLogger {

	protected ErrorLogger() {
		super();
	}

	@Override
	protected void log(String message) {
		this.log(message, output);
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.err;
	}

}

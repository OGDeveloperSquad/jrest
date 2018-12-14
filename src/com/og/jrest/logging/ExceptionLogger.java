package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle logging Exceptions
 * 
 * @author Matthew.Shoemaker
 *
 */
class ExceptionLogger extends BaseLogger {

	protected ExceptionLogger() {
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

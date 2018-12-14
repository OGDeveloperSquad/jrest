package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle info logging.
 * 
 * @author Matthew.Shoemaker
 *
 */
class InfoLogger extends BaseLogger {

	protected InfoLogger() {
		super();
	}

	@Override
	protected void log(String message) {
		this.log(message, output);
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.out;
	}

}

package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle logging Exceptions
 * 
 * @author Matthew.Shoemaker
 *
 */
class ExceptionLogHandler extends BaseLogHandler {

	protected ExceptionLogHandler() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.err;
	}

}

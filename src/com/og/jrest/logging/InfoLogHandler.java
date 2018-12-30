package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle info logging.
 * 
 * @author Matthew.Shoemaker
 *
 */
class InfoLogHandler extends LogHandler {

	protected InfoLogHandler() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.out;
	}

}

package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle info logging.
 * 
 * @author Matthew.Shoemaker
 *
 */
class InfoLogWriter extends LogWriter {

	protected InfoLogWriter() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.out;
	}

}

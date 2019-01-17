package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle info logging.
 * 
 * @author Matthew.Shoemaker
 *
 */
class LogWriterInfo extends LogWriter {

	protected LogWriterInfo() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.out;
	}

}

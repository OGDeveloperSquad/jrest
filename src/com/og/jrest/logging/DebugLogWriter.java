package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle logging of Debug messages;
 * 
 * @author Matthew.Shoemaker
 *
 */
class DebugLogWriter extends LogWriter {

	protected DebugLogWriter() {
		super();
	}

	@Override
	public void setToDefaultOutput() {
		this.output = System.out;
	}

}

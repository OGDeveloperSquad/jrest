package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle logging of Debug messages;
 * 
 * @author Matthew.Shoemaker
 *
 */
class DebugLogger extends BaseLogger {

	protected DebugLogger() {
		super();
	}

	@Override
	public void setToDefaultOutput() {
		this.output = System.out;
	}

}

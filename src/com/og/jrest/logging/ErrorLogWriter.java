package com.og.jrest.logging;

/**
 * Implementation of the BaseLogger to handle logging of errors.
 * 
 * @author Matthew.Shoemaker
 *
 */
class ErrorLogWriter extends LogWriter {

	protected ErrorLogWriter() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.err;
	}

	protected static String formatErrorMessage(Error error) {
		StringBuilder result = new StringBuilder(error.getMessage() + System.lineSeparator());
		for (StackTraceElement element : error.getStackTrace()) {
			result.append("\t" + element.toString() + System.lineSeparator());
		}

		return result.toString();
	}

}

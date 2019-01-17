package com.og.jrest.logging;

/**
 * Implementation of BaseLogger to handle logging Exceptions
 * 
 * @author Matthew.Shoemaker
 *
 */
class LogWriterException extends LogWriter {

	protected LogWriterException() {
		super();
	}

	@Override
	protected void setToDefaultOutput() {
		this.output = System.err;
	}

	/**
	 * Given an Exception, build a string containing the text that will be logged
	 * for the exception.
	 * 
	 * @param ex exception for which a string message will be built
	 * @return message explaining the exception for logging purposes
	 */
	protected static String formatExceptionMessage(Throwable ex) {
		StringBuilder result = new StringBuilder(ex.getMessage() + System.lineSeparator());
		for (StackTraceElement element : ex.getStackTrace()) {
			result.append("\t" + element.toString() + System.lineSeparator());
		}

		return result.toString();
	}
}

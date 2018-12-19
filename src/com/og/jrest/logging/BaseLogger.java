package com.og.jrest.logging;

abstract class BaseLogger implements ILogger {

	protected BaseLogHandler exceptionLogHandler;
	protected BaseLogHandler errorLogHandler;
	protected BaseLogHandler debugLogHandler;
	protected BaseLogHandler infoLogHandler;

	public BaseLogger() {
		this.exceptionLogHandler = LoggerFactory.getExceptionLogger();
		this.errorLogHandler = LoggerFactory.getErrorLogger();
		this.debugLogHandler = LoggerFactory.getDebugLogger();
		this.infoLogHandler = LoggerFactory.getInfoLogger();
	}

	/**
	 * Given an Exception, build a string containing the text that will be logged
	 * for the exception.
	 * 
	 * @param ex
	 *            exception for which a string message will be built
	 * @return message explaining the exception for logging purposes
	 */
	protected String formatExceptionMessage(Exception ex) {
		StringBuilder result = new StringBuilder(ex.getMessage() + "\n");
		for (StackTraceElement element : ex.getStackTrace()) {
			result.append("\t" + element.toString() + "\n");
		}

		return result.toString();
	}

}

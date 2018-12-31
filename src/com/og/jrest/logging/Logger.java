package com.og.jrest.logging;

abstract class Logger implements ILogger {

	protected LogWriter exceptionLogWriter;
	protected LogWriter errorLogWriter;
	protected LogWriter debugLogWriter;
	protected LogWriter infoLogWriter;
	protected LogWriter throwableLogWriter;

	public Logger() {
		this.exceptionLogWriter = Log.getExceptionLogger();
		this.errorLogWriter = Log.getErrorLogger();
		this.debugLogWriter = Log.getDebugLogger();
		this.infoLogWriter = Log.getInfoLogger();
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
		StringBuilder result = new StringBuilder(ex.getMessage() + System.lineSeparator());
		for (StackTraceElement element : ex.getStackTrace()) {
			result.append("\t" + element.toString() + System.lineSeparator());
		}

		return result.toString();
	}

	protected String formatErrorMessage(Error error) {
		StringBuilder result = new StringBuilder(error.getMessage() + System.lineSeparator());
		for (StackTraceElement element : error.getStackTrace()) {
			result.append("\t" + element.toString() + System.lineSeparator());
		}

		return result.toString();
	}

	protected String formatThrowableMessage(Throwable throwable) {
		StringBuilder result = new StringBuilder(throwable.getMessage() + System.lineSeparator());
		for (StackTraceElement element : throwable.getStackTrace()) {
			result.append("\t" + element.toString() + System.lineSeparator());
		}
		return result.toString();
	}

}

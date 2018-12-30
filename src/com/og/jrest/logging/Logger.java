package com.og.jrest.logging;

abstract class Logger implements ILogger {

	protected LogHandler exceptionLogHandler;
	protected LogHandler errorLogHandler;
	protected LogHandler debugLogHandler;
	protected LogHandler infoLogHandler;
	protected LogHandler throwableLogHandler;

	public Logger() {
		this.exceptionLogHandler = Logger.getExceptionLogger();
		this.errorLogHandler = Logger.getErrorLogger();
		this.debugLogHandler = Logger.getDebugLogger();
		this.infoLogHandler = Logger.getInfoLogger();
		this.throwableLogHandler = Logger.getThrowableLogger();
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

	/*********************************
	 * Factory Methods
	 *********************************
	 */

	/**
	 * Returns a new instance of a class implemented specifically to handle error
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle error
	 *         logging
	 */
	protected static LogHandler getErrorLogger() {
		return new ErrorLogHandler();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle
	 * exception logging.
	 * 
	 * @return new instance of a class implemented specifically to handle exception
	 *         logging.
	 */
	protected static LogHandler getExceptionLogger() {
		return new ExceptionLogHandler();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle debug
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle debug
	 *         logging.
	 */
	protected static LogHandler getDebugLogger() {
		return new DebugLogHandler();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle info
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle info
	 *         logging.
	 */
	protected static LogHandler getInfoLogger() {
		return new InfoLogHandler();
	}

	/**
	 * Returns a new instance of an implementation of the ILogger interface.
	 * 
	 * @return new instance of an ILogger implementation.
	 */
	protected static ILogger getLogger() {
		return new StandardLogger();
	}

	protected static LogHandler getThrowableLogger() {
		return new ThrowableLogHandler();
	}

}

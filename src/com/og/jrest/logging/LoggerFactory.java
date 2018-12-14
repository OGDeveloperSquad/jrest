package com.og.jrest.logging;

/**
 * Factory for obtaining new instances of logging implementations to enforce
 * single point of control.
 * 
 * @author Matthew.Shoemaker
 *
 */
class LoggerFactory {

	protected static BaseLogger getErrorLogger() {
		return new ErrorLogger();
	}

	protected static BaseLogger getExceptionLogger() {
		return new ExceptionLogger();
	}

	protected static BaseLogger getDebugLogger() {
		return new DebugLogger();
	}

	protected static BaseLogger getInfoLogger() {
		return new InfoLogger();
	}

}

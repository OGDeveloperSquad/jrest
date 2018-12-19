package com.og.jrest.logging;

/**
 * Factory for obtaining new instances of logging implementations to enforce
 * single point of control.
 * 
 * @author Matthew.Shoemaker
 *
 */
class LoggerFactory {

	/**
	 * Returns a new instance of a class implemented specifically to handle error
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle error
	 *         logging
	 */
	protected static BaseLogHandler getErrorLogger() {
		return new ErrorLogHandler();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle
	 * exception logging.
	 * 
	 * @return new instance of a class implemented specifically to handle exception
	 *         logging.
	 */
	protected static BaseLogHandler getExceptionLogger() {
		return new ExceptionLogHandler();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle debug
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle debug
	 *         logging.
	 */
	protected static BaseLogHandler getDebugLogger() {
		return new DebugLogHandler();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle info
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle info
	 *         logging.
	 */
	protected static BaseLogHandler getInfoLogger() {
		return new InfoLogHandler();
	}

	/**
	 * Returns a new instance of an implementation of the ILogger interface.
	 * 
	 * @return new instance of an ILogger implementation.
	 */
	public static ILogger getLogger() {
		return new StandardLogger();
	}

}

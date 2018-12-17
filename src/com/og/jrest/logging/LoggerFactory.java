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
	protected static BaseLogger getErrorLogger() {
		return new ErrorLogger();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle
	 * exception logging.
	 * 
	 * @return new instance of a class implemented specifically to handle exception
	 *         logging.
	 */
	protected static BaseLogger getExceptionLogger() {
		return new ExceptionLogger();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle debug
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle debug
	 *         logging.
	 */
	protected static BaseLogger getDebugLogger() {
		return new DebugLogger();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle info
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle info
	 *         logging.
	 */
	protected static BaseLogger getInfoLogger() {
		return new InfoLogger();
	}

}

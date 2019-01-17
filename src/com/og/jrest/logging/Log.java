package com.og.jrest.logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Global logging api. Singleton implementation of logging utilities for the
 * various logging levels. For local logging (i.e. separate from the global
 * logging scheme), use the factory methods in this class to obtain new
 * instances of the logger.
 * 
 * @author Matthew.Shoemaker
 *
 */
public class Log {

	private static ILogger logger;

	static {
		Log.logger = Log.newInstance();
	}

	/**
	 * Log the given exception to the exception output stream.
	 * 
	 * @param throwable
	 *            exception to be logged
	 */
	public static void exception(Throwable throwable) {
		Log.logger.exception(throwable);
	}

	public static void exception(Exception ex, OutputStream output) {
		ILogger log = Log.newInstance();
		log.setOutput(output);
		log.exception(ex);
		try {
			log.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exception(Exception ex, File file) {
		ILogger log = Log.newInstance();
		try {
			log.setOutput(file);
			log.exception(ex);
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given message to the current debug output stream.
	 * 
	 * @param message
	 *            text to be written to the log output
	 */
	public static void debug(String message) {
		Log.logger.debug(message);
	}

	/**
	 * Log the given debug message to the given output stream.
	 * 
	 * @param message
	 *            text to be logged
	 * @param output
	 *            stream to which the log will be written
	 */
	public static void debug(String message, OutputStream output) {
		ILogger log = Log.newInstance();
		log.setOutput(output);
		log.debug(message);
		try {
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given debug message to the given file.
	 * 
	 * @param message
	 *            text to be logged
	 * @param file
	 *            location to which the log will be written
	 */
	public static void debug(String message, File file) {
		ILogger log = Log.newInstance();
		try {
			log.setOutput(file);
			log.debug(message);
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given message to the current error output stream.
	 * 
	 * @param message
	 *            text to be written to the log output
	 */
	public static void error(String message) {
		Log.logger.error(message);
	}

	/**
	 * Log the given error to the current error output stream.
	 * 
	 * @param message
	 *            error to be written to the log output
	 */
	public static void error(Error error) {
		Log.logger.error(error);
	}

	/**
	 * Log the given error message to the given output stream.
	 * 
	 * @param message
	 *            text to be logged
	 * @param output
	 *            stream to which the log will be written
	 */
	public static void error(String message, OutputStream output) {
		ILogger log = Log.newInstance();
		log.setOutput(output);
		log.error(message);
		try {
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given error to the given output stream.
	 * 
	 * @param error
	 *            error to be logged
	 * @param output
	 *            stream to which the log will be written
	 */
	public static void error(Error error, OutputStream output) {
		ILogger log = Log.newInstance();
		log.setOutput(output);
		log.error(error);
		try {
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given error message to the given file.
	 * 
	 * @param message
	 *            text to be logged
	 * @param file
	 *            location to which the log will be written
	 */
	public static void error(String message, File file) {
		ILogger log = Log.newInstance();
		try {
			log.setOutput(file);
			log.error(message);
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given error message to the given file.
	 * 
	 * @param error
	 *            text to be logged
	 * @param file
	 *            location to which the log will be written
	 */
	public static void error(Error error, File file) {
		ILogger log = Log.newInstance();
		try {
			log.setOutput(file);
			log.error(error);
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given message to the current info output stream.
	 * 
	 * @param message
	 *            text to be written to the log output text to be written to the log
	 *            output
	 */
	public static void info(String message) {
		Log.logger.info(message);
	}

	/**
	 * Log the given info message to the given output stream.
	 * 
	 * @param message
	 *            text to be logged
	 * @param output
	 *            stream to which the log will be written
	 */
	public static void info(String message, OutputStream output) {
		ILogger log = Log.newInstance();
		log.setOutput(output);
		log.info(message);
		try {
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the given info message to the given file.
	 * 
	 * @param message
	 *            text to be logged
	 * @param file
	 *            location to which the log will be written
	 */
	public static void info(String message, File file) {
		ILogger log = Log.newInstance();
		try {
			log.setOutput(file);
			log.info(message);
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/****************************************
	 * Factory Methods
	 ***************************************/

	/**
	 * Returns a new instance of a class implemented specifically to handle error
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle error
	 *         logging
	 */
	protected static LogWriter getErrorLogWriter() {
		return new LogWriterError();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle
	 * exception logging.
	 * 
	 * @return new instance of a class implemented specifically to handle exception
	 *         logging.
	 */
	protected static LogWriter getExceptionLogWriter() {
		return new LogWriterException();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle debug
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle debug
	 *         logging.
	 */
	protected static LogWriter getDebugLogWriter() {
		return new LogWriterDebug();
	}

	/**
	 * Returns a new instance of a class implemented specifically to handle info
	 * logging.
	 * 
	 * @return new instance of a class implemented specifically to handle info
	 *         logging.
	 */
	protected static LogWriter getInfoLogWriter() {
		return new LogWriterInfo();
	}

	/**
	 * Returns a new instance of an implementation of the ILogger interface.
	 * 
	 * @return new instance of an ILogger implementation.
	 */
	public static ILogger newInstance() {
		return new LoggerDefault();
	}
}

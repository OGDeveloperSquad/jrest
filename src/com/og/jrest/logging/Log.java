package com.og.jrest.logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Global logging utility. Singleton implementations of logging utilities for
 * the various logging levels. For local logging (i.e. separate from the global
 * logging scheme), use the inner class methods in Log.Local
 * 
 * @author Matthew.Shoemaker
 *
 */
public class Log {

	private static ILogger logger;

	static {
		Log.logger = LoggerFactory.getLogger();
	}

	/**
	 * Returns a new instance an implementation of the ILogger interface.
	 * 
	 * @return New instance of an implementation of the ILogger interface.
	 */
	public static ILogger getInstance() {
		return LoggerFactory.getLogger();
	}

	/**
	 * Log the given exception to the default exception output stream.
	 * 
	 * @param ex
	 *            exception to be logged
	 */
	public static void exception(Exception ex) {
		Log.logger.exception(ex);
	}

	public static void exception(Exception ex, OutputStream output) {
		ILogger log = Log.getInstance();
		log.setOutput(output);
		log.exception(ex);
		try {
			log.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exception(Exception ex, File file) {
		ILogger log = Log.getInstance();
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
		ILogger log = Log.getInstance();
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
		ILogger log = Log.getInstance();
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
	 * Log the given error message to the given output stream.
	 * 
	 * @param message
	 *            text to be logged
	 * @param output
	 *            stream to which the log will be written
	 */
	public static void error(String message, OutputStream output) {
		ILogger log = Log.getInstance();
		log.setOutput(output);
		log.error(message);
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
		ILogger log = Log.getInstance();
		try {
			log.setOutput(file);
			log.error(message);
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
		ILogger log = Log.getInstance();
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
		ILogger log = Log.getInstance();
		try {
			log.setOutput(file);
			log.info(message);
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

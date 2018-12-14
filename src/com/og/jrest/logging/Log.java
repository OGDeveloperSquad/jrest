package com.og.jrest.logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Logging utility.
 * 
 * @author Matthew.Shoemaker
 *
 */
public class Log {

	private static BaseLogger errorLogger = LoggerFactory.getErrorLogger();
	private static BaseLogger debugLogger = LoggerFactory.getDebugLogger();
	private static BaseLogger exceptionLogger = LoggerFactory.getExceptionLogger();
	private static BaseLogger infoLogger = LoggerFactory.getInfoLogger();

	/**
	 * Log the given exception to the current OutputStream.
	 * 
	 * @param ex
	 */
	public static void exception(Exception ex) {
		Log.exceptionLogger.log(ex.getMessage());
		for (StackTraceElement element : ex.getStackTrace()) {
			Log.exceptionLogger.log(element.toString());
		}
	}

	/**
	 * Log the given message to the current debug output stream.
	 * 
	 * @param message
	 */
	public static void debug(String message) {
		Log.debugLogger.log(message);
	}

	/**
	 * Log the given message to the current error output stream.
	 * 
	 * @param message
	 */
	public static void error(String message) {
		Log.errorLogger.log(message);
	}

	/**
	 * Log the given message to the current info output stream.
	 * 
	 * @param message
	 */
	public static void info(String message) {
		Log.infoLogger.log(message);
	}

	/**
	 * Set all logging output streams to the given output.
	 * 
	 * @param output
	 */
	public static void setAllOutput(OutputStream output) {
		Log.errorLogger.setOutput(output);
		Log.debugLogger.setOutput(output);
		Log.exceptionLogger.setOutput(output);
		Log.infoLogger.setOutput(output);
	}

	/**
	 * Set all logging output streams to write to the given file.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void setAllOutput(File file) throws IOException {
		Log.errorLogger.setOutput(file);
		Log.debugLogger.setOutput(file);
		Log.exceptionLogger.setOutput(file);
		Log.infoLogger.setOutput(file);
	}

	/**
	 * Set the error logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setErrorOutput(OutputStream output) {
		Log.errorLogger.setOutput(output);
	}

	/**
	 * Set the error logging output stream to write to the given file.
	 * 
	 * @param file
	 */
	public static void setErrorOutput(File file) throws IOException {
		Log.errorLogger.setOutput(file);
	}

	/**
	 * Set the error logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setDebugOutput(OutputStream output) {
		Log.debugLogger.setOutput(output);
	}

	/**
	 * Set the debug logging output stream to write to the given file.
	 * 
	 * @param file
	 */
	public static void setDebugOutput(File file) throws IOException {
		Log.debugLogger.setOutput(file);
	}

	/**
	 * Set the exception logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setExceptionOutput(OutputStream output) {
		Log.exceptionLogger.setOutput(output);
	}

	/**
	 * Set the exception logging output stream to write to the given file.
	 * 
	 * @param file
	 */
	public static void setExceptionOutput(File file) throws IOException {
		Log.exceptionLogger.setOutput(file);
	}

	/**
	 * Set the info logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setInfoOutput(OutputStream output) {
		Log.infoLogger.setOutput(output);
	}

	/**
	 * Set the info logging output stream to write to the given file.
	 * 
	 * @param file
	 */
	public static void setInfoOutput(File file) throws IOException {
		Log.infoLogger.setOutput(file);
	}

	/**
	 * Set all logging output streams to their default locations.
	 * 
	 * @param output
	 */
	public static void setAllToDefault(OutputStream output) {
		Log.errorLogger.setToDefaultOutput();
		Log.exceptionLogger.setToDefaultOutput();
		Log.debugLogger.setToDefaultOutput();
		Log.infoLogger.setToDefaultOutput();
	}

	/**
	 * Set error logging to its default output stream.
	 */
	public static void setErrorToDefault() {
		Log.errorLogger.setToDefaultOutput();
	}

	/**
	 * Set debug message logging to its default output stream.
	 */
	public static void setDebugToDefault() {
		Log.debugLogger.setToDefaultOutput();
	}

	/**
	 * Set exception logging to its default output stream.
	 */
	public static void setExceptionToDefault() {
		Log.exceptionLogger.setToDefaultOutput();
	}

	/**
	 * Set info logging to its deafults output stream.
	 */
	public static void setInfoToDefault() {
		Log.infoLogger.setToDefaultOutput();
	}

}

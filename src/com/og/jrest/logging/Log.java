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
	 * Log the given message to the current error output stream.
	 * 
	 * @param message
	 *            text to be written to the log output
	 */
	public static void error(String message) {
		Log.logger.error(message);
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
	 * Set all logging output streams to the given output.
	 * 
	 * @param output
	 */
	public static void setOutput(OutputStream output) {
		Log.logger.setOutput(output);
	}

	/**
	 * Set all logging output streams to write to the given file, overwriting any
	 * existing contents.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @throws IOException
	 */
	public static void setOutput(File file) throws IOException {
		Log.logger.setOutput(file);
	}

	/**
	 * Set all logging output streams to write to the given file, appending to
	 * existing contents if append is true.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @param append
	 *            if true, contents of log will be appended to existing file
	 *            contents rather than overwriting
	 * @throws IOException
	 */
	public static void setOutput(File file, boolean append) throws IOException {
		Log.logger.setOutput(file, append);
	}

	/**
	 * Set the error logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setErrorOutput(OutputStream output) {
		Log.logger.setErrorOutput(output);
	}

	/**
	 * Set the error logging output stream to write to the given file, overwriting
	 * existing file contents.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @throws IOException
	 */
	public static void setErrorOutput(File file) throws IOException {
		Log.logger.setErrorOutput(file);
	}

	/**
	 * Set the error logging output stream to write to the given file, appending to
	 * existing file contents if append is true.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @param append
	 *            if true, contents of log will be appended to existing file
	 *            contents rather than overwriting
	 * @throws IOException
	 * 
	 */
	public static void setErrorOutput(File file, boolean append) throws IOException {
		Log.logger.setErrorOutput(file, append);
	}

	/**
	 * Set the error logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setDebugOutput(OutputStream output) {
		Log.logger.setDebugOutput(output);
	}

	/**
	 * Set the debug logging output stream to write to the given file, overwriting
	 * existing contents.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @throws IOException
	 */
	public static void setDebugOutput(File file) throws IOException {
		Log.logger.setDebugOutput(file);
	}

	/**
	 * Set the debug logging output stream to write to the given file, appending to
	 * existing file contents if append is true. .
	 * 
	 * @param file
	 *            file with the location of the output
	 * @param append
	 *            if true, contents of log will be appended to existing file
	 *            contents rather than overwriting
	 * @throws IOException
	 */
	public static void setDebugOutput(File file, boolean append) throws IOException {
		Log.logger.setDebugOutput(file, append);
	}

	/**
	 * Set the exception logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setExceptionOutput(OutputStream output) {
		Log.logger.setExceptionOutput(output);
	}

	/**
	 * Set the exception logging output stream to write to the given file,
	 * overwriting existing contents.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @throws IOException
	 */
	public static void setExceptionOutput(File file) throws IOException {
		Log.logger.setExceptionOutput(file);
	}

	/**
	 * Set the exception logging output stream to write to the given file, appending
	 * to existing file contents if append is true.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @param append
	 *            if true, contents of log will be appended to existing file
	 *            contents rather than overwriting
	 * @throws IOException
	 */
	public static void setExceptionOutput(File file, boolean append) throws IOException {
		Log.logger.setExceptionOutput(file, append);
	}

	/**
	 * Set the info logging output stream to the given output stream.
	 * 
	 * @param output
	 */
	public static void setInfoOutput(OutputStream output) {
		Log.logger.setOutput(output);
	}

	/**
	 * Set the info logging output stream to write to the given file, overwriting
	 * existing contents.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @throws IOException
	 */
	public static void setInfoOutput(File file) throws IOException {
		Log.logger.setInfoOutput(file);
	}

	/**
	 * Set the info logging output stream to write to the given file, appending to
	 * contents if append is true.
	 * 
	 * @param file
	 *            file with the location of the output
	 * @param append
	 *            if true, contents of log will be appended to existing file
	 *            contents rather than overwriting
	 * @throws IOException
	 */
	public static void setInfoOutput(File file, boolean append) throws IOException {
		Log.logger.setInfoOutput(file);
	}

	/**
	 * Set all logging output streams to their default locations.
	 */
	public static void setAllToDefault() {
		Log.logger.setToDefaultOutput();
	}

	/**
	 * Set error logging to its default output stream.
	 */
	public static void setErrorToDefault() {
		Log.logger.setErrorToDefaultOutput();
	}

	/**
	 * Set debug message logging to its default output stream.
	 */
	public static void setDebugToDefault() {
		Log.logger.setDebugToDefaultOutput();
	}

	/**
	 * Set exception logging to its default output stream.
	 */
	public static void setExceptionToDefault() {
		Log.logger.setExceptionToDefaultOutput();
	}

	/**
	 * Set info logging to its deafults output stream.
	 */
	public static void setInfoToDefault() {
		Log.logger.setInfoToDefaultOutput();
	}

}

package com.og.jrest.logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface ILogger extends AutoCloseable {

	/**
	 * Close all connections and relinquish resources.
	 * 
	 * @throws IOException
	 */
	@Override
	public void close() throws IOException;

	/**
	 * Log the given exception to the current exception OutputStream. Defaults to
	 * System.err if no output stream has been set.
	 * 
	 * @param ex exception to be logged
	 */
	void exception(Exception ex);

	/**
	 * Log the given message to the current error output stream. Defaults to
	 * System.err if no output stream has been set.
	 * 
	 * @param message text to be logged.
	 */
	void error(String message);

	/**
	 * Log the given error to the current error output stream. Defaults to
	 * System.err if no output stream has been set.
	 * 
	 * @param error the error to be logged.
	 */
	void error(Error error);

	/**
	 * Log the given throwable to the current throwable output stream. Defaults to
	 * System.err if no output stream has been set.
	 * 
	 * @param error the error to be logged.
	 */
	void throwable(Throwable throwable);

	/**
	 * Log the given message to the current debug output stream. Defaults to
	 * System.out if no output stream has been set.
	 * 
	 * @param message text to be logged.
	 */
	void debug(String message);

	/**
	 * Log the given message to the current info output stream. Defaults to
	 * System.out if no output stream has been set.
	 * 
	 * @param message text to be logged.
	 */
	void info(String message);

	/**
	 * Write all logs to the given output stream.
	 * 
	 * @param output stream to which all logs will be written.
	 */
	void setOutput(OutputStream output);

	/**
	 * Write all logs to the given file. Existing file contents will be overwritten.
	 * 
	 * @param file file to which all logs will be written
	 * @throws IOException
	 */
	void setOutput(File file) throws IOException;

	/**
	 * Write all exception logs to the given output stream.
	 * 
	 * @param output stream to which exception logs will be written.
	 */
	void setExceptionOutput(OutputStream output);

	/**
	 * Write all exception logs to the given file. Existing file contents will be
	 * overwritten.
	 *
	 * @param file file to which exception logs will be written
	 * @throws IOException
	 */
	void setExceptionOutput(File file) throws IOException;

	/**
	 * Write all error logs to the given output stream.
	 * 
	 * @param output stream to which error logs will be written.
	 */
	void setErrorOutput(OutputStream output);

	/**
	 * Write all error logs to the given file. Existing file contents will be
	 * overwritten.
	 *
	 * @param file file to which error logs will be written
	 * @throws IOException
	 */
	void setErrorOutput(File file) throws IOException;

	/**
	 * Write all throwable logs to the given output stream.
	 * 
	 * @param output stream to which throwable logs will be written.
	 */
	void setThrowableOutput(OutputStream output);

	/**
	 * Write all throwable logs to the given file. Existing file contents will be
	 * overwritten.
	 *
	 * @param file file to which throwable logs will be written
	 * @throws IOException
	 */
	void setThrowableOutput(File file) throws IOException;

	/**
	 * Write all debug logs to the given output stream.
	 * 
	 * @param output stream to which debug logs will be written.
	 */
	void setDebugOutput(OutputStream output);

	/**
	 * Write all debug logs to the given file. Existing file contents will be
	 * overwritten.
	 *
	 * @param file file to which debug logs will be written
	 * @throws IOException
	 */
	void setDebugOutput(File file) throws IOException;

	/**
	 * Write all info logs to the given output stream.
	 * 
	 * @param output stream to which info logs will be written.
	 */
	void setInfoOutput(OutputStream output);

	/**
	 * Write all info logs to the given file. Existing file contents will be
	 * overwritten.
	 *
	 * @param file file to which info logs will be written
	 * @throws IOException
	 */
	void setInfoOutput(File file) throws IOException;

	/**
	 * Set all logging levels to their default output streams.
	 */
	void setToDefaultOutput();

	/**
	 * Set error logging to its default output stream.
	 */
	void setErrorToDefaultOutput();

	void setThrowableToDefaultOutput();

	/**
	 * Set debug logging to its default output stream.
	 */
	void setDebugToDefaultOutput();

	/**
	 * Set exception logging to its default output stream.
	 */
	void setExceptionToDefaultOutput();

	/**
	 * Set info logging to its default output stream.
	 */
	void setInfoToDefaultOutput();

	/**
	 * Set exception logging output to write to the given file. If append is true,
	 * then logs will be appended to existing file contents.
	 * 
	 * @param file   file to which exception logs will be written
	 * @param append if true, existing file contents will be appended to
	 * @throws IOException
	 */
	void setExceptionOutput(File file, boolean append) throws IOException;

	/**
	 * Set debug logging output to write to the given file. If append is true, then
	 * logs will be appended to existing file contents.
	 * 
	 * @param file   file to which debug logs will be written
	 * @param append if true, existing file contents will be appended to
	 * @throws IOException
	 */
	void setDebugOutput(File file, boolean append) throws IOException;

	/**
	 * Set error logging output to write to the given file. If append is true, then
	 * logs will be appended to existing file contents.
	 * 
	 * @param file   file to which error logs will be written
	 * @param append if true, existing file contents will be appended to
	 * @throws IOException
	 */
	void setErrorOutput(File file, boolean append) throws IOException;

	/**
	 * Set throwable logging output to write to the given file. If append is true,
	 * then logs will be appended to existing file contents.
	 * 
	 * @param file   file to which throwable logs will be written
	 * @param append if true, existing file contents will be appended to
	 * @throws IOException
	 */
	void setThrowableOutput(File file, boolean append) throws IOException;

	/**
	 * Set all logging output to write to the given file. If append is true, then
	 * logs will be appended to existing file contents.
	 * 
	 * @param file   file to which all logs will be written
	 * @param append if true, existing file contents will be appended to
	 * @throws IOException
	 */
	void setOutput(File file, boolean append) throws IOException;

	/**
	 * Set info logging output to write to the given file. If append is true, then
	 * logs will be appended to existing file contents.
	 * 
	 * @param file   file to which info logs will be writting
	 * @param append if true, existing file contents will be appended to
	 * @throws IOException
	 */
	void setInfoOutput(File file, boolean append) throws IOException;

}

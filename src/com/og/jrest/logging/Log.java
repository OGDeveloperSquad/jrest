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

	private static BaseLogger errorLogger;
	private static BaseLogger exceptionLogger;
	private static BaseLogger debugLogger;
	private static BaseLogger infoLogger;

	static {
		Log.errorLogger = LoggerFactory.getErrorLogger();
		Log.exceptionLogger = LoggerFactory.getExceptionLogger();
		Log.debugLogger = LoggerFactory.getDebugLogger();
		Log.infoLogger = LoggerFactory.getInfoLogger();
	}

	/**
	 * Logging functionality to provide logging that is independent of the global
	 * logging scheme. These methods instantiate temporary instances of logging
	 * level implementations, allowing the logging of data to locations other than
	 * the globally defined scheme and without changing the global logging scheme.
	 * 
	 * @author Matthew.Shoemaker
	 *
	 */
	public static class Local {

		public static void exception(Exception ex) {
			LoggerFactory.getExceptionLogger().log(Log.formatExceptionMessage(ex));
		}

		public static void exception(Exception ex, OutputStream output) {
			// Use try-with-resource for the auto-close functionality since this is a local
			// logging resource. We could close stream manually but that's no fun when you
			// have cool interfaces to implement (i.e. AutoCloseable)
			try (BaseLogger logger = LoggerFactory.getExceptionLogger()) {
				logger.setOutput(output);
				logger.log(Log.formatExceptionMessage(ex));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public static void exception(Exception ex, File file) throws IOException {
			// Use try-with-resource for the auto-close functionality since this is a local
			// logging resource. We could close stream manually but that's no fun when you
			// have cool interfaces to implement (i.e. AutoCloseable)
			try (BaseLogger logger = LoggerFactory.getExceptionLogger()) {
				logger.setOutput(file);
				logger.log(Log.formatExceptionMessage(ex));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void debug(String message) {
			// Use try-with-resource for the auto-close functionality since this is a local
			// logging resource. We could close stream manually but that's no fun when you
			// have cool interfaces to implement (i.e. AutoCloseable)
			try (BaseLogger logger = LoggerFactory.getDebugLogger()) {
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void debug(String message, OutputStream output) {
			try (BaseLogger logger = LoggerFactory.getDebugLogger()) {
				logger.setOutput(output);
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void debug(String message, File file) throws IOException {
			try (BaseLogger logger = LoggerFactory.getDebugLogger()) {
				logger.setOutput(file);
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void error(String message) {
			try (BaseLogger logger = LoggerFactory.getErrorLogger()) {
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void error(String message, OutputStream output) {
			try (BaseLogger logger = LoggerFactory.getErrorLogger()) {
				logger.setOutput(output);
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void error(String message, File file) throws IOException {
			try (BaseLogger logger = LoggerFactory.getErrorLogger()) {
				logger.setOutput(file);
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void info(String message) {
			try (BaseLogger logger = LoggerFactory.getInfoLogger()) {
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void info(String message, OutputStream output) {
			try (BaseLogger logger = LoggerFactory.getInfoLogger()) {
				logger.setOutput(output);
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void info(String message, File file) throws IOException {
			try (BaseLogger logger = LoggerFactory.getInfoLogger()) {
				logger.setOutput(file);
				logger.log(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static String formatExceptionMessage(Exception ex) {
		StringBuilder result = new StringBuilder(ex.getMessage() + "\n");
		for (StackTraceElement element : ex.getStackTrace()) {
			result.append("\t" + element.toString() + "\n");
		}

		return result.toString();
	}

	/**
	 * Log the given exception to the current OutputStream.
	 * 
	 * @param ex
	 */
	public static void exception(Exception ex) {
		Log.exceptionLogger.log(Log.formatExceptionMessage(ex));
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
	 * Set all logging output streams to write to the given file, overwriting any
	 * existing contents.
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
	 * Set all logging output streams to write to the given file, appending to
	 * existing contents if append is true.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void setAllOutput(File file, boolean append) throws IOException {
		Log.errorLogger.setOutput(file, append);
		Log.debugLogger.setOutput(file, append);
		Log.exceptionLogger.setOutput(file, append);
		Log.infoLogger.setOutput(file, append);
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
	 * Set the error logging output stream to write to the given file, overwriting
	 * existing file contents.
	 * 
	 * @param file
	 */
	public static void setErrorOutput(File file) throws IOException {
		Log.errorLogger.setOutput(file);
	}

	/**
	 * Set the error logging output stream to write to the given file, appending to
	 * existing file contents if append is true.
	 * 
	 * @param file
	 */
	public static void setErrorOutput(File file, boolean append) throws IOException {
		Log.errorLogger.setOutput(file, append);
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
	 * Set the debug logging output stream to write to the given file, overwriting
	 * existing contents.
	 * 
	 * @param file
	 */
	public static void setDebugOutput(File file) throws IOException {
		Log.debugLogger.setOutput(file);
	}

	/**
	 * Set the debug logging output stream to write to the given file, appending to
	 * existing file contents if append is true. .
	 * 
	 * @param file
	 */
	public static void setDebugOutput(File file, boolean append) throws IOException {
		Log.debugLogger.setOutput(file, append);
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
	 * Set the exception logging output stream to write to the given file,
	 * overwriting existing contents.
	 * 
	 * @param file
	 */
	public static void setExceptionOutput(File file) throws IOException {
		Log.exceptionLogger.setOutput(file);
	}

	/**
	 * Set the exception logging output stream to write to the given file, appending
	 * to existing file contents if append is true.
	 * 
	 * @param file
	 */
	public static void setExceptionOutput(File file, boolean append) throws IOException {
		Log.exceptionLogger.setOutput(file, append);
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
	 * Set the info logging output stream to write to the given file, overwriting
	 * existing contents.
	 * 
	 * @param file
	 */
	public static void setInfoOutput(File file) throws IOException {
		Log.infoLogger.setOutput(file);
	}

	/**
	 * Set the info logging output stream to write to the given file, appending to
	 * contents if append is true.
	 * 
	 * @param file
	 */
	public static void setInfoOutput(File file, boolean append) throws IOException {
		Log.infoLogger.setOutput(file);
	}

	/**
	 * Set all logging output streams to their default locations.
	 * 
	 * @param output
	 */
	public static void setAllToDefault() {
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

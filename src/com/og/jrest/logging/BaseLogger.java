package com.og.jrest.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Common functionality which all logging implementations must have. All loggers
 * must extend this class.
 * 
 * @author Matthew.Shoemaker
 *
 */
abstract class BaseLogger {

	/**
	 * The stream to which all logging output will be written.
	 */
	protected OutputStream output;

	/**
	 * Default constructor for loggers. Subclasses should call this constructor in
	 * addition to any work they do in their own constructors.
	 */
	protected BaseLogger() {
		this.setToDefaultOutput();
	}

	/**
	 * Log a message to the current output stream.
	 * 
	 * @param message
	 */
	protected abstract void log(String message);

	/**
	 * Set the output stream for this to its default state.
	 */
	protected abstract void setToDefaultOutput();

	/**
	 * Write the given message to the given output stream.
	 * 
	 * @param message
	 * @param output
	 */
	protected void log(String message, OutputStream output) {
		try {
			message += "\n";
			output.write(message.getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Change the OutputStream for this to write to the given file. If the file does
	 * not exist, it will be created. All logs will be appended to the existing
	 * file's contents. (it does not overwrite data).
	 * 
	 * @param file
	 * @throws IOException
	 */
	protected void setOutput(File file) throws IOException {
		file.createNewFile();
		OutputStream output = new FileOutputStream(file, false);
		this.output = output;
	}
	
	protected void setOutput(File file, boolean append) throws IOException {
		file.createNewFile();
		OutputStream output = new FileOutputStream(file, append);
		this.output = output;
	}

	/**
	 * Change the OutputStream for this to write to the given output.
	 * 
	 * @param output
	 */
	protected void setOutput(OutputStream output) {
		this.output = output;
	}
}

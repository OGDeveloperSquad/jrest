package com.og.jrest.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Common functionality which all logging implementations must have. All loggers
 * must extend this class.
 * 
 * @author Matthew.Shoemaker
 *
 */
abstract class BaseLogger implements AutoCloseable {

	/**
	 * The stream to which all logging output will be written.
	 */
	protected OutputStream output;

	/**
	 * Close the output stream for this logger.
	 * 
	 * @throws IOException
	 */
	@Override
	public void close() throws IOException {
		// Make sure anything still in the writer isn't lost
		if (this.output != null)
			this.output.flush();
		// We don't want to close the system streams, java handles that. Ignore null as
		// well
		if (this.output != null && output != System.out && output != System.err) {
			this.output.close();
		}
	}

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
	protected void log(String message) {
		/*
		 * Since we'll be dealing exclusively with text and never binary data, wrap the
		 * output stream in a PrintWriter to write to log files. PrintWriter is better
		 * at handling textual data.
		 */
		PrintWriter writer = new PrintWriter(this.output, true);
		writer.println(message);
	}

	/**
	 * Set the output stream for this to its default state.
	 */
	protected abstract void setToDefaultOutput();

	/**
	 * Write the given message to the given output stream.
	 * 
	 * @param message
	 * @param output
	 * @throws IOException
	 */
	protected void log(String message, OutputStream output) {
		/*
		 * Accept output stream for its polymorphic properties (specifically in dealing
		 * with System.out and System.err), but since we'll be dealing exclusively with
		 * text and never binary data, wrap the output stream in a PrintWriter to write
		 * to log files. PrintWriter is better at handling textual data.
		 */
		PrintWriter writer = new PrintWriter(output, true);
		writer.println(message);
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

	/**
	 * Set this logger to write to the given file. If append is false, the contents
	 * of the file will be overwritten. If append is true, then the logs will be
	 * appended to the existing content.
	 * 
	 * @param file
	 * @param append
	 * @throws IOException
	 */
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

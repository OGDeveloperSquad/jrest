package com.og.jrest.logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class LoggerDefault extends Logger {

	public LoggerDefault() {
		super();
	}

	@Override
	public void exception(Throwable ex) {
		this.exceptionLogWriter.log(LogWriterException.formatExceptionMessage(ex));
	}

	@Override
	public void error(String message) {
		this.errorLogWriter.log(message);
	}

	@Override
	public void debug(String message) {
		this.debugLogWriter.log(message);
	}

	@Override
	public void info(String message) {
		this.infoLogWriter.log(message);
	}

	@Override
	public void setOutput(OutputStream output) {
		this.exceptionLogWriter.setOutput(output);
		this.errorLogWriter.setOutput(output);
		this.debugLogWriter.setOutput(output);
		this.infoLogWriter.setOutput(output);
	}

	@Override
	public void setOutput(File file) throws IOException {
		this.exceptionLogWriter.setOutput(file);
		this.errorLogWriter.setOutput(file);
		this.debugLogWriter.setOutput(file);
		this.infoLogWriter.setOutput(file);
	}

	@Override
	public void setExceptionOutput(OutputStream output) {
		this.exceptionLogWriter.setOutput(output);
	}

	@Override
	public void setExceptionOutput(File file) throws IOException {
		this.exceptionLogWriter.setOutput(file);
	}

	@Override
	public void setErrorOutput(OutputStream output) {
		this.errorLogWriter.setOutput(output);
	}

	@Override
	public void setErrorOutput(File file) throws IOException {
		this.errorLogWriter.setOutput(file);
	}

	@Override
	public void setDebugOutput(OutputStream output) {
		this.debugLogWriter.setOutput(output);
	}

	@Override
	public void setDebugOutput(File file) throws IOException {
		this.debugLogWriter.setOutput(file);
	}

	@Override
	public void setInfoOutput(OutputStream output) {
		this.infoLogWriter.setOutput(output);
	}

	@Override
	public void setInfoOutput(File file) throws IOException {
		this.infoLogWriter.setOutput(file);
	}

	@Override
	public void setToDefaultOutput() {
		this.exceptionLogWriter.setToDefaultOutput();
		this.errorLogWriter.setToDefaultOutput();
		this.debugLogWriter.setToDefaultOutput();
		this.infoLogWriter.setToDefaultOutput();

	}

	@Override
	public void setErrorToDefaultOutput() {
		this.errorLogWriter.setToDefaultOutput();
	}

	@Override
	public void setDebugToDefaultOutput() {
		this.debugLogWriter.setToDefaultOutput();
	}

	@Override
	public void setExceptionToDefaultOutput() {
		this.exceptionLogWriter.setToDefaultOutput();
	}

	@Override
	public void setInfoToDefaultOutput() {
		this.infoLogWriter.setToDefaultOutput();
	}

	@Override
	public void setExceptionOutput(File file, boolean append) throws IOException {
		this.exceptionLogWriter.setOutput(file, append);
	}

	@Override
	public void setDebugOutput(File file, boolean append) throws IOException {
		this.debugLogWriter.setOutput(file, append);
	}

	@Override
	public void setErrorOutput(File file, boolean append) throws IOException {
		this.errorLogWriter.setOutput(file, append);
	}

	@Override
	public void setInfoOutput(File file, boolean append) throws IOException {
		this.infoLogWriter.setOutput(file, append);
	}

	@Override
	public void setOutput(File file, boolean append) throws IOException {
		this.exceptionLogWriter.setOutput(file, append);
		this.errorLogWriter.setOutput(file, append);
		this.debugLogWriter.setOutput(file, append);
		this.errorLogWriter.setOutput(file, append);
	}

	@Override
	public void close() throws IOException {
		this.exceptionLogWriter.close();
		this.errorLogWriter.close();
		this.debugLogWriter.close();
		this.errorLogWriter.close();
	}

	@Override
	public void error(Error error) {
		this.errorLogWriter.log(LogWriterError.formatErrorMessage(error));
	}

}

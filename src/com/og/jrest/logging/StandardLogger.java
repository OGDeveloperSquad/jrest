package com.og.jrest.logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class StandardLogger extends Logger {

	public StandardLogger() {
		super();
	}

	@Override
	public void exception(Exception ex) {
		this.exceptionLogHandler.log(this.formatExceptionMessage(ex));
	}

	@Override
	public void error(String message) {
		this.errorLogHandler.log(message);
	}

	@Override
	public void debug(String message) {
		this.debugLogHandler.log(message);
	}

	@Override
	public void info(String message) {
		this.infoLogHandler.log(message);
	}

	@Override
	public void setOutput(OutputStream output) {
		this.exceptionLogHandler.setOutput(output);
		this.errorLogHandler.setOutput(output);
		this.debugLogHandler.setOutput(output);
		this.infoLogHandler.setOutput(output);
	}

	@Override
	public void setOutput(File file) throws IOException {
		this.exceptionLogHandler.setOutput(file);
		this.errorLogHandler.setOutput(file);
		this.debugLogHandler.setOutput(file);
		this.infoLogHandler.setOutput(file);
	}

	@Override
	public void setExceptionOutput(OutputStream output) {
		this.exceptionLogHandler.setOutput(output);
	}

	@Override
	public void setExceptionOutput(File file) throws IOException {
		this.exceptionLogHandler.setOutput(file);
	}

	@Override
	public void setErrorOutput(OutputStream output) {
		this.errorLogHandler.setOutput(output);
	}

	@Override
	public void setErrorOutput(File file) throws IOException {
		this.errorLogHandler.setOutput(file);
	}

	@Override
	public void setDebugOutput(OutputStream output) {
		this.debugLogHandler.setOutput(output);
	}

	@Override
	public void setDebugOutput(File file) throws IOException {
		this.debugLogHandler.setOutput(file);
	}

	@Override
	public void setInfoOutput(OutputStream output) {
		this.infoLogHandler.setOutput(output);
	}

	@Override
	public void setInfoOutput(File file) throws IOException {
		this.infoLogHandler.setOutput(file);
	}

	@Override
	public void setToDefaultOutput() {
		this.exceptionLogHandler.setToDefaultOutput();
		this.errorLogHandler.setToDefaultOutput();
		this.debugLogHandler.setToDefaultOutput();
		this.infoLogHandler.setToDefaultOutput();

	}

	@Override
	public void setErrorToDefaultOutput() {
		this.errorLogHandler.setToDefaultOutput();
	}

	@Override
	public void setDebugToDefaultOutput() {
		this.debugLogHandler.setToDefaultOutput();
	}

	@Override
	public void setExceptionToDefaultOutput() {
		this.exceptionLogHandler.setToDefaultOutput();
	}

	@Override
	public void setInfoToDefaultOutput() {
		this.infoLogHandler.setToDefaultOutput();
	}

	@Override
	public void setExceptionOutput(File file, boolean append) throws IOException {
		this.exceptionLogHandler.setOutput(file, append);
	}

	@Override
	public void setDebugOutput(File file, boolean append) throws IOException {
		this.debugLogHandler.setOutput(file, append);
	}

	@Override
	public void setErrorOutput(File file, boolean append) throws IOException {
		this.errorLogHandler.setOutput(file, append);
	}

	@Override
	public void setInfoOutput(File file, boolean append) throws IOException {
		this.infoLogHandler.setOutput(file, append);
	}

	@Override
	public void setOutput(File file, boolean append) throws IOException {
		this.exceptionLogHandler.setOutput(file, append);
		this.errorLogHandler.setOutput(file, append);
		this.debugLogHandler.setOutput(file, append);
		this.errorLogHandler.setOutput(file, append);
	}

	@Override
	public void close() throws IOException {
		this.exceptionLogHandler.close();
		this.errorLogHandler.close();
		this.debugLogHandler.close();
		this.errorLogHandler.close();
	}

	@Override
	public void error(Error error) {
		this.errorLogHandler.log(this.formatErrorMessage(error));
	}

	@Override
	public void throwable(Throwable throwable) {
		this.throwableLogHandler.log(this.formatThrowableMessage(throwable));
	}

	@Override
	public void setThrowableOutput(OutputStream output) {
		this.throwableLogHandler.setOutput(output);
	}

	@Override
	public void setThrowableOutput(File file) throws IOException {
		this.throwableLogHandler.setOutput(file);
	}

	@Override
	public void setThrowableToDefaultOutput() {
		this.throwableLogHandler.setToDefaultOutput();
	}

	@Override
	public void setThrowableOutput(File file, boolean append) throws IOException {
		this.throwableLogHandler.setOutput(file, append);
	}

}

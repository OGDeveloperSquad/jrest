package com.og.jrest.logging;

public class Log {

	public static void exception(Exception ex) {
		ExceptionLogger.log(ex.getMessage() + ": \n" + ex.getStackTrace().toString());
	}

	public static void error(String message) {
		ErrorLogger.log(message);
	}

	public static void info(String message) {
		InfoLogger.log(message);
	}

	public static void debug(String message) {
		DebugLogger.log(message);
	}
}

package com.og.jrest.logging;

import java.io.OutputStream;

class ExceptionLogger {

	private static OutputStream output = System.err;

	public static void log(String message) {
		BaseLogger.log(message, output);
	}

}

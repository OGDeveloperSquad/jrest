package com.og.jrest.logging;

import java.io.OutputStream;

public class DebugLogger {

	private static OutputStream output = System.out;

	public static void log(String message) {
		BaseLogger.log(message, output);
	}

}

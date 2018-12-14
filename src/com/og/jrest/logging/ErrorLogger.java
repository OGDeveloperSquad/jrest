package com.og.jrest.logging;

import java.io.IOException;
import java.io.OutputStream;

public class ErrorLogger extends BaseLogger {

	private static OutputStream output = System.err;

	public static void Log(String message) {
		try {
			BaseLogger.log(message, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

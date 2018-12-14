package com.og.jrest.logging;

import java.io.IOException;
import java.io.OutputStream;

public class InfoLogger extends BaseLogger {

	private static OutputStream output = System.out;

	public void Log(String message) {
		try {
			BaseLogger.log(message, output);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

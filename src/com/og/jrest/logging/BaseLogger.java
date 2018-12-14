package com.og.jrest.logging;

import java.io.IOException;
import java.io.OutputStream;

abstract class BaseLogger {

	protected static void log(String message, OutputStream output) {
		try {
			output.write(message.getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

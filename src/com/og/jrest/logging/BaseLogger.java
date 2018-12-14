package com.og.jrest.logging;

import java.io.IOException;
import java.io.OutputStream;

abstract class BaseLogger {

	protected static void log(String message, OutputStream output) throws IOException {
		output.write(message.getBytes());
	}

}

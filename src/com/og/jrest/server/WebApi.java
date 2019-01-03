package com.og.jrest.server;

import java.io.IOException;

import com.og.jrest.logging.Log;

/**
 * This is the entry point for the server. This will continuously listen on a port to accept http requests.
 * 
 * @author matthew.shoemaker
 *
 */
public class WebApi {

	public static int openConnections;

	private static final int DEFAULT_PORT_NUMBER = 9090;

	static {
		WebApi.openConnections = 0;
	}

	public static void start() {
		WebApi.start(DEFAULT_PORT_NUMBER);
	}

	public static void start(int port) {
		try {
			RequestListener.listen(port);
		} catch (IOException e) {
			Log.exception(e);
		}
	}

}

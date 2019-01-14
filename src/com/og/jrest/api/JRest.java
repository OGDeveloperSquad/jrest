package com.og.jrest.api;

import java.io.IOException;

import com.og.jrest.logging.Log;
import com.og.jrest.reflection.controllers.ControllerTable;
import com.og.jrest.server.RequestListener;

/**
 * This is the entry point for the server. This will continuously listen on a
 * port to accept http requests.
 * 
 * @author matthew.shoemaker
 *
 */
public class JRest {

	public static int openConnections;
	private static final int DEFAULT_PORT_NUMBER = 9090;

	static {
		JRest.openConnections = 0;
	}

	public static void start() {
		JRest.start(DEFAULT_PORT_NUMBER);
	}

	public static void start(int port) {
		try {
			JRest.performStartup(port);
		} catch (IOException e) {
			Log.exception(e);
		} catch (InstantiationException e) {
			Log.exception(e);
		} catch (IllegalAccessException e) {
			Log.exception(e);
		}
	}

	private static void performStartup(int port) throws InstantiationException, IllegalAccessException, IOException {
		ControllerTable.registerControllers();
		RequestListener.listen(port);
	}

}

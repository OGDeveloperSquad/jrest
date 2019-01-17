package com.og.jrest.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.og.jrest.logging.Log;

public class RequestListener {

	public static void listen(int port) throws IOException {
		while (true) {
			try (ServerSocket listener = new ServerSocket(port)) {
				Socket socket = listener.accept();
				// This needs to be closed immediately or else it drops requests every
				// now and then. Blocks the port? idk
				listener.close();
				Log.info("Request received, starting new thread.\n");
				RequestHandler handler = new RequestHandler(socket);
				Thread thread = new Thread(handler);
				thread.start();
			} catch (Exception ex) {
				Log.exception(ex);
			} finally {
				try {
					// listener.close();
				} catch (Exception ex) {
					Log.exception(ex);
				}
			}
		}
	}

}

package com.og.jrest.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.og.jrest.logging.Log;

/**
 * This is the entry point for the server. This will continuously listen on a
 * port to accept http requests.
 * 
 * @author matthew.shoemaker
 *
 */
public class RequestListener {

	public static void main(String[] args) throws IOException {
		int clientNumber = 0;
		// Log.info("");
		// Log.setAllOutput(System.out);
		// Log.error("");
		// Log.exception(new Exception());
		// Log.debug("");

		Log.info("Oh hey the program has begun ");

		// Just listen forever!
		while (true) {
			clientNumber++;
			// TODO -- Use ServerSocket to listen on any port you like and wait for a
			// request to come in, then do something cool with it like hand off the request
			// to some kind of request handler class
			ServerSocket listener = new ServerSocket(9091);
			try {
				Log.info(" Getting new thread for client #." + clientNumber);

				// Don't daisy-chain things together like this. Makes debugging difficult and
				// its really hard to read. Generally frowned upon
				// new Thread(new RequestHandler(listener.accept(), clientNumber)).start();

				Socket socket = listener.accept();
				listener.close();
				RequestHandler handler = new RequestHandler(socket, clientNumber);
				Thread thread = new Thread(handler);
				thread.start();

			} finally {
				listener.close();
				Log.info("Connection with client #" + clientNumber + " closed");
			}
		}
	}

}

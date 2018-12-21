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

		Log.info("Oh hey the program has begun ");

		while (true) {
			clientNumber++;
			ServerSocket listener = new ServerSocket(9090);
			try {
				Log.info(" Getting new thread for client #." + clientNumber);

				// Don't daisy-chain things together like this. Makes debugging really difficult
				// and its really hard to read. Universally frowned upon
				// new Thread(new RequestHandler(listener.accept(), clientNumber)).start();

				Socket socket = listener.accept();
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

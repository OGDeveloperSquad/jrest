package com.og.jrest.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.og.jrest.logging.Log;
import com.og.jrest.routing.RouteTable;

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

		Log.info("Building Routes:\n");
		RouteTable.registerRoute("api/{controller=Test}/{action=testMethod}/{id?}");

		while (true) {
			clientNumber++;
			ServerSocket listener = new ServerSocket(9090);
			try {
				Log.info(" Getting new thread for client #." + clientNumber);

				Socket socket = listener.accept();
				listener.close();
				RequestHandler handler = new RequestHandler(socket, clientNumber);
				Thread thread = new Thread(handler);
				thread.start();
			} finally {
				// listener.close();
				Log.info("Connection with client #" + clientNumber + " closed");
			}
		}
	}

}

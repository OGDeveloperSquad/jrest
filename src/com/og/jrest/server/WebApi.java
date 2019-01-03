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

	public static int openConnections;

	static {
		RequestListener.openConnections = 0;
	}

	public static void main(String[] args) throws IOException {

		Log.info("Building Routes:\n");

		// Register a route with the routing engine
		RouteTable.registerRoute("Default", "api/{controller=Test}/{action=testMethod}/{id?}");

		while (true) {
			ServerSocket listener = new ServerSocket(9090);
			try {
				Socket socket = listener.accept();
				// Found out this needs to be closed immediately or else it drops requests every
				// now and then. Not entirely sure why. Probably something to do with blocking
				// the port?
				listener.close();
				RequestHandler handler = new RequestHandler(socket);
				Thread thread = new Thread(handler);
				Log.info("Request received, starting new thread.\n");
				thread.start();
			} catch (Exception ex) {
				Log.exception(ex);
			} finally {
				listener.close();
			}
		}
	}

}

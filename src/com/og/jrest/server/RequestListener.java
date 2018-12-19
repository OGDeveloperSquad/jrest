package com.og.jrest.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.og.jrest.logging.Log;

/**
 * This is the entry point for the server. This will continuously listen on a
 * port to accept http requests.
 * 
 * @author matthew.shoemaker
 *
 */
public class RequestListener {

	public static void main(String[] args) {

		Log.info("");
		Log.setAllOutput(System.out);
		Log.error("");
		Log.exception(new Exception());
		Log.debug("");

		Log.Local.info("");

		Log.Local.info("Oh hey the program has begun (btw this is a local log)");

		// Just listen forever!
		while (true) {

			// TODO -- Use ServerSocket to listen on any port you like and wait for a
			// request to come in, then do something cool with it like hand off the request
			// to some kind of request handler class
			try {
				Log.info("Listening for requests.....");

				ServerSocket socket = new ServerSocket();

				socket.accept();

				/*
				 * 
				 * 
				 */

				RequestHandler handler = new RequestHandler();

				Thread thread = new Thread(handler);
				thread.start();

				// Probably just accept requests here then hand them off to some kind of request
				// handler and listen for the next request?

				Log.Local.debug("Request received! Let the fun begin...");

				// Do some cool stuff and things

			} catch (IOException e) {
				// Uh-oh
				Log.error("Something went wrong! Check exception log for more info");
				// Use the logging framework to log the error
				Log.exception(e);
			}
		}

	}

}
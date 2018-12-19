package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.og.jrest.http.Request;
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

		Log.info("Oh hey the program has begun (btw this is a local log)");

		// Just listen forever!
		while (true) {

			// TODO -- Use ServerSocket to listen on any port you like and wait for a
			// request to come in, then do something cool with it like hand off the request
			// to some kind of request handler class
			try {
				Log.info("Listening for requests.....");
				
					// ?????????? does this need to have a port number?
				ServerSocket socket = new ServerSocket();

				// Probably just accept requests here then hand them off to some kind of request
				// handler and listen for the next request?

				Log.debug("Request received! Let the fun begin...");

				new RequestHandler(socket.accept()).run();
				

			} catch (IOException e) {
				// Uh-oh
				Log.error("Something went wrong! Check exception log for more info");
				// Use the logging framework to log the error
				Log.exception(e);
			}
		}

	}
	
	
}

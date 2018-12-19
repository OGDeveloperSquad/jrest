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

	public static void main(String[] args) throws IOException {

//		Log.info("");
//		Log.setAllOutput(System.out);
//		Log.error("");
//		Log.exception(new Exception());
//		Log.debug("");

	
		Log.info("Oh hey the program has begun (btw this is a local log)");

		// Just listen forever!
		while (true) {

			// TODO -- Use ServerSocket to listen on any port you like and wait for a
			// request to come in, then do something cool with it like hand off the request
			// to some kind of request handler class
			  ServerSocket listener = new ServerSocket(9898);
			 try {
		            while (true) {
		             new RequestHandler(listener.accept()).start();;
		            }
		        } finally {
		            listener.close();
		        }
			}
		}

	}


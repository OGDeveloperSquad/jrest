package com.og.jrest.server;

import com.og.jrest.logging.Log;

/**
 * This class handles the request received. Implements the Runnable interface so
 * that request handling can be done asynchronously (on a new thread), allowing
 * the server to handle many requests at once.
 * 
 * @author matthew.shoemaker
 *
 */
public class RequestHandler implements Runnable {

	/**
	 * This method runs when the new thread is started.
	 */
	@Override
	public void run() {

		Log.debug("New thread started to handle a request! How cool is that?!");

		// TODO -- Do whatever server-y things a server should do...

	}

}

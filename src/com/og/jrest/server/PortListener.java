package com.og.jrest.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This is the entry point for the server. This will continuously listen on a
 * port to accept http requests.
 * 
 * @author matthew.shoemaker
 *
 */
public class PortListener {

	public static void main(String[] args) {

		// TODO Use ServerSocket to listen on any port you like. Google some examples
		try {
			ServerSocket socket = new ServerSocket();

			// Do some cool stuff and things

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

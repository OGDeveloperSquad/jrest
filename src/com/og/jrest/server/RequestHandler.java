package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.og.jrest.http.Request;
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

	 private Socket socket;

     public RequestHandler(Socket socket) {
         this.socket = socket;
        // log("New connection with client# " + clientNumber + " at " + socket);
     }

		@Override
		public void run() {
			try {
				
				
				
				/*
				 * Do something to get the HTTP request and pass it into the Request class
				 * 
				 * 
				 */
				BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				 
				 while (true) {
	                   String input = inStream.readLine();
	                   Request req = new Request(input);
	                   
	                }
				
			}catch(IOException c) {
				Log.info(" IO ERROR");
			}

		}
}

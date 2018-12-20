package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
	private int clientNumber;

   
	public RequestHandler(Socket socket, int clientNumber) {
		this.socket = socket;
        this.clientNumber = clientNumber;
	}


	/**
     * Services this thread's client by first sending the
     * client a welcome message then repeatedly reading strings
     * and sending back the capitalized version of the string.
     */
    public void run() {
        try {
        	
            // Decorate the streams so we can send characters
            // and not just bytes.  Ensure output is flushed
            // after every newline.
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            
            
            /*this will need manual input or a file to get things. How to test this?*/
            String temp = in.readLine();
        
            Log.debug("....handling client");
            /*
             * DO something with HTTP request. 
             * 
             * 
             */
            
        } catch (IOException e) {
            Log.debug("Error handling client");
        }
    }
    
}

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
	 * Services this thread's client by first sending the client a welcome message
	 * then repeatedly reading strings and sending back the capitalized version of
	 * the string.
	 */
	public void run() {
		try {

			// Decorate the streams so we can send characters
			// and not just bytes. Ensure output is flushed
			// after every newline.
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(this.socket.getInputStream()));
			// InputStream readRequest = socket.getInputStream();
			// /*this will need manual input or a file to get things. How to test a file??*/
			// byte[] buf = new byte[4096];
			// readRequest.read(buf);
			//
			// String httpPayload = new String(buf, "UTF-8");

			/*****************************************************************************************
			 * How about this instead?
			 *****************************************************************************************/
			BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

			/*
			 * The way input stream works is kinda dumb, becuase we can only use readLine()
			 * until we get an empty line. This causes problems because every http request
			 * has a blank line between the reqeust body and the headers. So we have to read
			 * the request headers first, looking for Content-Length header which tells you
			 * the exact length of the request body. Then, if the length of the request body
			 * is greater than 0, we have to parse the request body using a character array
			 * and then converting that to a string. This took a lot of googling to figure
			 * out lol.
			 */
			int bodyLength = 0;

			// Read the header lines
			String line = in.readLine();
			String httpRaw = line;
			while (line != null && line.length() > 0) {
				httpRaw += line + "\n";
				line = in.readLine();
				/*
				 * Check to see if this line is the Content-Length header, and if so, get the
				 * content length and save to bodyLength
				 */
				if (line.toLowerCase().startsWith("content-length:")) {
					int endOfHeader = line.indexOf(":") + 1;
					String contentLength = line.substring(endOfHeader, line.length()).trim();
					bodyLength = Integer.parseInt(contentLength);
				}
			}

			/*
			 * If the request has a body, we have to read it into a character array then
			 * into a string.
			 */
			if (bodyLength > 0) {
				char[] charArray = new char[bodyLength];
				in.read(charArray, 0, bodyLength);
				httpRaw += "\n" + new String(charArray);
			}

			// Making the request object
			// Request request = new Request(httpRaw);

			// Just echoing back the reqeust for now. This is just for fun until we actually
			// make it do something lol
			String responseHeader = "HTTP/1.1 200 OK \n\n";
			out.write(responseHeader + httpRaw);

			out.flush();
			out.close();
			this.socket.close();
		} catch (IOException e) {
			Log.exception(e);
		}
	}

}

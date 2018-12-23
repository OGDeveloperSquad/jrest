package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import com.og.jrest.api.Controller;
import com.og.jrest.http.HTTPRequest;
import com.og.jrest.http.HTTPResponse;
import com.og.jrest.http.TextResponse;
import com.og.jrest.logging.Log;
import com.og.jrest.routing.RouteResult;
import com.og.jrest.routing.RouteTable;

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
	}

	/**
	 * Services this thread's client by first sending the client a welcome message
	 * then repeatedly reading strings and sending back the capitalized version of
	 * the string.
	 */
	public void run() {
		// Declare this out here so we can access in the catch blocks
		OutputStream out;
		try {
			// New Connection opened, update the counter
			RequestListener.openConnections++;
			Log.info("New connection opened. " + RequestListener.openConnections + " connections are currently open.");
			BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = this.socket.getOutputStream();

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
			String httpRaw = line + "\n";
			while (line != null && line.length() > 0) {
				line = in.readLine();
				httpRaw += line + "\n";
				/*
				 * Check to see if this line is the 'Content-Length' header, and if so, get the
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
				httpRaw += new String(charArray);
			}
			if (!httpRaw.startsWith("null")) {
				// Build the request object
				HTTPRequest request = new HTTPRequest(httpRaw);
				// Evaulate the route based on the uri requested
				RouteResult routeResult = RouteTable.evaluateRoute(request.uri);
				// Get the controller that was called by the request
				Controller controller = routeResult.getController();
				// Give the controller access to the request object we built
				controller.request = request;

				// Plain response in case the controller doesnt return a nice response
				HTTPResponse response = new TextResponse();

				/*
				 * Invoke the action specified by the request. If/else just checks whether it
				 * needs to pass in parameters to the method or not
				 */
				if (routeResult.getParameters().length > 0) {
					Object[] params = routeResult.getParameters();
					response = (HTTPResponse) routeResult.getAction().invoke(controller, params);
				} else {
					response = (HTTPResponse) routeResult.getAction().invoke(controller);
				}

				// We've done our job and gotten the response back from the api client, so let's
				// return it.
				out.write(response.getBytes());
			}

			out.flush();
			out.close();
			this.socket.close();
		} catch (IOException e) {
			/*
			 * TODO : Return an ErrorResponse to the web client for each one of these cases
			 */
			// Use Log.exception for exceptions
			Log.exception(e);
		} catch (ClassNotFoundException e) {
			Log.exception(e);
		} catch (NoSuchMethodException e) {
			Log.exception(e);
		} catch (InstantiationException e) {
			Log.exception(e);
		} catch (IllegalAccessException e) {
			Log.exception(e);
		} catch (IllegalArgumentException e) {
			Log.exception(e);
		} catch (InvocationTargetException e) {
			Log.exception(e);
		} catch (Exception e) {
			// Just a catchall for 500 response erros
			Log.exception(e);
		} finally {
			// Connection has ended, reduce the counter
			RequestListener.openConnections--;
			Log.info("Connection Closed. " + RequestListener.openConnections + " connections are still open.");
		}
	}

}

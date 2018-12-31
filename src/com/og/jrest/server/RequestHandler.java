package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import com.og.jrest.api.Controller;
import com.og.jrest.http.Request;
import com.og.jrest.http.response.Response;
import com.og.jrest.http.response.ErrorResponse;
import com.og.jrest.http.response.Response;
import com.og.jrest.http.response.TextResponse;
import com.og.jrest.logging.ILogger;
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
	private ILogger log;

	public RequestHandler(Socket socket) {
		this.socket = socket;
		this.log = Log.newInstance();
	}

	/**
	 * Asynchronously handles a request, processing it and returning the appropriate
	 * response to the web client.
	 */
	public void run() {
		// Declare the output stream here so we can access it in the catch blocks and
		// return error HTTPResponse
		OutputStream out = null;
		try {
			// New Connection opened, update the counter
			RequestListener.openConnections++;
			this.log.info(
					"New connection opened. " + RequestListener.openConnections + " connections are currently open.");
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
			String httpRaw = line + System.lineSeparator();
			while (line != null && line.length() > 0) {
				line = in.readLine();
				httpRaw += line + System.lineSeparator();
				/*
				 * Check to see if this line is the 'Content-Length' header, and if so, get the
				 * content length and save to bodyLength
				 */
				if (line != null && line.toLowerCase().startsWith("content-length:")) {
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
			// Don't really know why some phantom requests appear with "null" but whatever
			if (!httpRaw.startsWith("null")) {
				// Build the request object
				Request request = new Request(httpRaw);
				// Evaulate the route based on the uri requested
				RouteResult routeResult = RouteTable.evaluateRoute(request.getUri());
				// Get the controller that was called by the request
				Controller controller = routeResult.getController();
				// Give the controller access to the request object we built
				controller.request = request;

				// Plain response in case the controller doesnt return a nice response
				Response response = new TextResponse();

				/*
				 * Invoke the action specified by the request. If/else just checks whether it
				 * needs to pass in parameters to the method or not
				 */
				if (routeResult.getParameters().length > 0) {
					Object[] params = routeResult.getParameters();
					response = (Response) routeResult.getAction().invoke(controller, params);
				} else {
					response = (Response) routeResult.getAction().invoke(controller);
				}

				// We've done our job and gotten the response back from the api client, so let's
				// return it.
				out.write(response.getBytes());
			}

			this.socket.close();

		} catch (IOException e) {
			this.sendErrorResponse(out, 500);
			this.log.exception(e);
		} catch (ClassNotFoundException e) {
			this.sendErrorResponse(out, 404);
			this.log.exception(e);
		} catch (NoSuchMethodException e) {
			this.sendErrorResponse(out, 404);
			this.log.exception(e);
		} catch (InstantiationException e) {
			this.sendErrorResponse(out, 404);
			this.log.exception(e);
		} catch (IllegalAccessException e) {
			this.sendErrorResponse(out, 403);
			this.log.exception(e);
		} catch (IllegalArgumentException e) {
			this.sendErrorResponse(out, 500);
			this.log.exception(e);
		} catch (InvocationTargetException e) {
			this.sendErrorResponse(out, 500);
			this.log.exception(e);
		} catch (Exception e) {
			this.sendErrorResponse(out, 500);
			this.log.exception(e);
		} catch (Throwable e) {
			this.sendErrorResponse(out, 500);
			this.log.exception(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				this.log.exception(e);
			}
			// Connection has ended, reduce the counter
			RequestListener.openConnections--;
			this.log.info("Connection Closed. " + RequestListener.openConnections + " connections are still open."
					+ System.lineSeparator());
		}
	}

	/**
	 * Creates an ErrorResponse object with the given error code and writes it to
	 * the given output stream.
	 * 
	 * @param out       output stream to which the response will be written
	 * @param errorCode the response code with which to instantiate the error
	 *                  response
	 */
	private void sendErrorResponse(OutputStream out, int errorCode) {
		Response errorResponse = new ErrorResponse(errorCode);
		try {
			out.write(errorResponse.getBytes());
			out.flush();
		} catch (IOException e) {
			this.log.exception(e);
		}
	}

}

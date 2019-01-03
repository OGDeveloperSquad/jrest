package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import com.og.jrest.api.Controller;
import com.og.jrest.http.Header;
import com.og.jrest.http.Request;
import com.og.jrest.http.response.EmptyResponse;
import com.og.jrest.http.response.ErrorResponse;
import com.og.jrest.http.response.IResponse;
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
	private Request request;
	private ILogger log;

	public RequestHandler(Socket socket) {
		this.log = Log.newInstance();
		this.request = new Request();
		this.socket = socket;
	}

	/**
	 * Asynchronously handles a request, processing it and returning the appropriate
	 * response to the web client.
	 */
	public void run() {
		// New Connection opened, update the counter
		RequestListener.openConnections++;

		this.log.info("New connection opened. " + RequestListener.openConnections + " connections are currently open.");

		OutputStream out = null;
		try {
			out = this.socket.getOutputStream();

			this.buildRequest();

			if (this.request != null) {
				// Evaulate the route based on the uri requested
				RouteResult routeResult = RouteTable.evaluateRoute(request.getUri());
				// Get the controller that was called by the request
				Controller controller = routeResult.getController();
				// Give the controller access to the request object we built
				controller.request = request;

				// Plain response in case the controller doesnt return a nice response
				IResponse response = new EmptyResponse();

				/*
				 * Invoke the action specified by the request. If/else just checks whether it
				 * needs to pass in parameters to the method or not
				 */
				if (routeResult.getParameters().length > 0) {
					Object[] params = routeResult.getParameters();
					Method action = routeResult.getAction();
					response = (IResponse) action.invoke(controller, params);
				} else {
					response = (IResponse) routeResult.getAction().invoke(controller);
				}

				// We've done our job and gotten the response back from the api client, so let's
				// return it.
				byte[] bytes = response.getBytes();
				out.write(bytes);
				out.flush();
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

	private void buildRequest() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

		this.processRequestLine(in);
		if (this.request != null) {
			this.processHeaders(in);
			this.processBody(in);
		}
	}

	private String processRequestLine(BufferedReader in) throws IOException {
		String line = in.readLine();
		if (line == "null" || line == null)
			this.request = null;
		else
			this.request.setRequestLine(line);

		return line;
	}

	private void processHeaders(BufferedReader in) throws IOException {
		String line = in.readLine();
		while (line != null && line.length() > 0) {
			this.request.addHeader(line);
			line = in.readLine();
		}
	}

	private void processBody(BufferedReader in) throws IOException {
		Header contentLengthHeader = this.request.getHeader("Content-Length");
		if (contentLengthHeader != null) {
			int bodyLength = Integer.parseInt(contentLengthHeader.getValuesJoined());
			if (bodyLength > 0) {
				String body = readBody(in, bodyLength);
				this.request.setBody(body);
			}
		}
	}

	private String readBody(BufferedReader in, int bodyLength) throws IOException {
		char[] charArray = new char[bodyLength];
		in.read(charArray, 0, bodyLength);
		String body = new String(charArray);

		return body;
	}

	private void sendErrorResponse(OutputStream out, int errorCode) {
		IResponse errorResponse = new ErrorResponse(errorCode);
		try {
			out.write(errorResponse.getBytes());
			out.flush();
		} catch (IOException e) {
			this.log.exception(e);
		}
	}

}

package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.og.jrest.api.JRest;
import com.og.jrest.http.Header;
import com.og.jrest.http.Request;
import com.og.jrest.http.response.ErrorResponse;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.http.response.TextResponse;
import com.og.jrest.logging.ILogger;
import com.og.jrest.logging.Log;
import com.og.jrest.routing.IRouteTemplate;
import com.og.jrest.routing.RouteTable;

/**
 * This class handles the request received. Implements the Runnable interface so
 * that request handling can be done asynchronously (on a new thread), allowing
 * the server to handle many requests at once.
 * 
 * @author matthew.shoemaker
 *
 */
class RequestHandler implements Runnable {

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
		JRest.openConnections++;

		this.log.info("New connection opened. " + JRest.openConnections + " connections are currently open.");

		OutputStream out = null;
		try {
			out = this.socket.getOutputStream();

			this.buildRequest();

			if (this.request != null) {
				// Plain response in case the controller doesnt return a nice response
				IResponse response = new TextResponse(this.request.toString());
				String uri = this.request.getUri();

				IRouteTemplate route = RouteTable.findCorrespondingRoute(uri);

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
		} catch (IllegalArgumentException e) {
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
			JRest.openConnections--;
			this.log.info("Connection Closed. " + JRest.openConnections + " connections are still open."
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
		// Some phantom requests just have "null"? idk why, just nullify the request if
		// so
		if (line == "null" || line == null)
			this.request = null;
		else
			this.request.parseRequestLine(line);

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

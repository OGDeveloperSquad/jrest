package com.og.jrest.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.og.jrest.api.JRest;
import com.og.jrest.exceptions.JRestServerException;
import com.og.jrest.http.Request;
import com.og.jrest.http.response.ErrorResponse;
import com.og.jrest.http.response.IResponse;
import com.og.jrest.http.response.TextResponse;
import com.og.jrest.logging.ILogger;
import com.og.jrest.logging.Log;

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
		JRest.openConnections++;

		this.log.info("New connection opened. " + JRest.openConnections + " connections are currently open.");

		OutputStream out = null;
		try {
			out = this.socket.getOutputStream();
			this.request = RequestBuilder.buildFromSocket(this.socket);

			this.sendResponse(out);

			this.socket.shutdownOutput();

		} catch (JRestServerException e) {
			this.sendErrorResponse(out, e.getStatusCode().getCode());
		} catch (Throwable e) {
			this.sendErrorResponse(out, 500);
			this.log.exception(e);
		} finally {
			this.closeStream(out);
			JRest.openConnections--;
			this.log.info("Connection Closed. " + JRest.openConnections + " connections are still open.");
		}
	}

	private void sendResponse(OutputStream out) throws JRestServerException, IOException {
		if (this.request != null) {
			IResponse response = RouteEvaluator.evaluateRoute(this.request);
			if (response == null)
				response = new TextResponse("Something went wrong somewhere, somehow, in some way...");

			byte[] bytes = response.getBytes();
			out.write(bytes);
			out.flush();
		}
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

	private void closeStream(OutputStream out) {
		try {
			if (out != null)
				out.close();
		} catch (IOException e) {
			this.log.exception(e);
		}
	}

}

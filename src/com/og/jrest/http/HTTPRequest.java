package com.og.jrest.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is a model of an HTTP message. Contains things like Headers,
 * method (i.e. GET, POST, PUT, or DELETE), and request body. Pretty
 * straightforward
 * 
 * @author matthew.shoemaker
 * @author Hussain
 *
 */
public class HTTPRequest {

	private HTTPVerb verb;
	private HTTPVersion httpVersion;
	private List<HTTPHeader> headers;
	private String uri;
	private String body;

	public HTTPRequest(String httpRequest) {
		this.headers = new ArrayList<>();
		this.parseRequest(httpRequest);
	}

	/**
	 * Get the uri for this HTTP request.
	 * 
	 * @return uri string for this HTTP request
	 */
	public String getUri() {
		return this.uri;
	}

	/**
	 * Get the verb for this HTTP request.
	 * 
	 * @return HTTPVerb for this HTTP request
	 */
	public HTTPVerb getVerb() {
		return this.verb;
	}

	/**
	 * Get the HTTP version of this request.
	 * 
	 * @return HTTPVersion of this request
	 */
	public HTTPVersion getVersion() {
		return this.httpVersion;
	}

	/**
	 * Returns a list of HTTPHeader object containing all of the headers for this
	 * request.
	 * 
	 * @return list of headers for this request
	 */
	public List<HTTPHeader> getHeaders() {
		return this.headers;
	}

	/**
	 * Returns the body of this HTTP request.
	 * 
	 * @return body of this HTTP request
	 */
	public String getBody() {
		return this.body;
	}

	@Override
	public String toString() {
		String result = String.format("%s %s %s%s", this.verb, this.uri, this.httpVersion, System.lineSeparator());
		for (HTTPHeader header : this.headers) {
			result += header.toString() + System.lineSeparator();
		}
		// Insert space between headers and body
		result += System.lineSeparator();
		if (this.body != null)
			result += this.body.toString();

		return result;
	}

	/*
	 * Given a raw HTTP request, parses through the text and populates the fields of
	 * this according to the contents of the request.
	 * 
	 * @param httpRequest raw Http request text
	 * 
	 */
	private void parseRequest(String httpRequest) {
		Scanner request = new Scanner(httpRequest);

		// parse request line assuming format 'verb uri version\n'
		this.verb = HTTPVerb.valueOf(request.next());
		this.uri = request.next();
		this.httpVersion = HTTPVersion.fromString(request.next());
		// Consume line separator '\n'
		request.nextLine();

		// Add each header to the list
		String headerLine = request.nextLine();
		while (!headerLine.equals("")) {
			HTTPHeader header = new HTTPHeader(headerLine);
			this.headers.add(header);
			headerLine = request.nextLine();
		}

		String body = "";
		while (request.hasNext()) {
			body = body + request.nextLine();
		}

		// If there was no body, set this.body to null
		if (body.length() > 0)
			this.body = body;
		else
			this.body = null;

		request.close();
	}

}

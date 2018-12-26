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

	public String getUri() {
		return this.uri;
	}

	public HTTPVerb getVerb() {
		return this.verb;
	}

	public HTTPVersion getVersion() {
		return this.httpVersion;
	}

	public List<HTTPHeader> getHeaders() {
		return this.headers;
	}

	public String getBody() {
		return this.body;
	}

	@Override
	public String toString() {
		String result = String.format("%s %s %s%s", this.verb, this.uri, this.httpVersion, System.lineSeparator());
		for (HTTPHeader header : this.headers) {
			result += header.toString() + System.lineSeparator();
		}

		result += System.lineSeparator() + body.toString();

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

		// parse request line assuming format request-method-name request-URI
		// HTTP-version
		this.verb = HTTPVerb.valueOf(request.next());
		this.uri = request.next();
		this.httpVersion = HTTPVersion.fromString(request.next());

		// Consume line separator
		request.nextLine();

		// parse request headers into map
		String headerLine = request.nextLine();
		while (!headerLine.equals("")) {
			this.headers.add(new HTTPHeader(headerLine));
			headerLine = request.nextLine();
		}

		String body = "";
		while (request.hasNext()) {
			body = body + request.nextLine();
		}

		this.body = body;

		request.close();
	}

}

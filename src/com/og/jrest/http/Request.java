package com.og.jrest.http;

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
public class Request {

	private Verb verb;
	private Version httpVersion;
	private HeaderCollection headers;
	private String uri;
	private String body;

	/**
	 * Constructor to parse through a raw HTTP request and populate the fields of
	 * this with the information contained in the request.
	 * 
	 * @param httpRequest
	 *            raw HTTP request
	 */
	public Request(String httpRequest) {
		this.initialize();
		this.parseRequest(httpRequest);
	}

	public Request() {
		this.initialize();
	}

	private void initialize() {
		this.headers = new HeaderCollection();
		this.body = null;
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
	public Verb getVerb() {
		return this.verb;
	}

	/**
	 * Get the HTTP version of this request.
	 * 
	 * @return HTTPVersion of this request
	 */
	public Version getVersion() {
		return this.httpVersion;
	}

	/**
	 * Returns a list of HTTPHeader object containing all of the headers for this
	 * request.
	 * 
	 * @return list of headers for this request
	 */
	public HeaderCollection getHeaders() {
		return this.headers;
	}

	public boolean hasHeader(String key) {
		boolean hasHeader = this.headers.contains(key);
		return hasHeader;
	}

	public Header getHeader(String key) {
		Header header = this.headers.getHeader(key);
		return header;
	}

	/**
	 * Returns the body of this HTTP request.
	 * 
	 * @return body of this HTTP request
	 */
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void parseRequestLine(String requestLine) {
		Scanner lineScanner = new Scanner(requestLine);
		// parse request line assuming format 'verb uri version\n'
		this.verb = Verb.valueOf(lineScanner.next());
		this.uri = lineScanner.next();
		this.httpVersion = Version.fromString(lineScanner.next());
		lineScanner.close();
	}

	public void addHeader(String headerLine) {
		Header header = new Header(headerLine);
		this.headers.add(header);
	}

	public void addHeader(Header header) {
		this.headers.add(header);
	}

	private void parseRequest(String httpRequest) {
		Scanner request = new Scanner(httpRequest);

		this.parseRequestLine(request.nextLine());
		this.parseHeaders(request);
		this.parseBody(request);

		request.close();
	}

	private void parseHeaders(Scanner request) {
		if (request.hasNext()) {
			String headerLine = request.nextLine();
			while (!headerLine.equals("")) {
				this.addHeader(headerLine);
				headerLine = request.nextLine();
			}
		}
	}

	private void parseBody(Scanner request) {
		while (request.hasNext()) {
			this.body += request.nextLine();
		}
	}

	@Override
	public String toString() {
		String result = this.verb.toString() + " " + this.uri + " " + this.httpVersion.toString();
		for (Header header : this.headers) {
			result += header.toString() + System.lineSeparator();
		}
		// Insert space between headers and body
		result += System.lineSeparator();
		if (this.body != null)
			result += this.body.toString();

		return result;
	}

}

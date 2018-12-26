package com.og.jrest.http;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a model for the http response sent back to the end user.
 * 
 * @author matthew.shoemaker
 * @param <T>
 *
 */
public abstract class HTTPResponse {

	protected String httpVersion;
	protected int statusCode;
	protected String reasonPhrase;
	protected Map<String, String[]> headers;
	protected Object body;
	protected static Map<Integer, String> reasonPhrases;

	static {
		reasonPhrases = new HashMap<Integer, String>();
		reasonPhrases.put(200, "OK");
		reasonPhrases.put(201, "Created");
		reasonPhrases.put(204, "No Content");
		reasonPhrases.put(304, "Not Modified");
		reasonPhrases.put(400, "Bad Request");
		reasonPhrases.put(401, "Unauthorized");
		reasonPhrases.put(403, "Forbidden");
		reasonPhrases.put(404, "Not Found");
		reasonPhrases.put(409, "Conflict");
		reasonPhrases.put(500, "Internal Server Error");
	}

	public HTTPResponse() {
		this.headers = new HashMap<String, String[]>();
		this.body = null;
		this.httpVersion = "HTTP/1.1";
		this.statusCode = 200;
		this.reasonPhrase = "OK";
	}

	/**
	 * Set the headers
	 * 
	 * @param name   the key for the header
	 * @param values the values for the header
	 * 
	 */
	public void addHeader(String name, String[] values) {
		this.headers.put(name, values);
	}

	/**
	 * Set the Http version
	 * 
	 * @param version the version of http to have in the response
	 * 
	 */
	public void setHttpVersion(String version) {
		this.httpVersion = version;
	}

	/**
	 * Set status code
	 * 
	 * @param statusCode status code to set this to
	 * 
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrases.get(statusCode);
	}

	protected String getReponseLineAndHeaders() {

		// stringify res line
		String resLineAndHeaders = this.httpVersion + " " + this.statusCode + " " + this.reasonPhrase + "\n";
		// stringify headers
		for (String key : this.headers.keySet()) {
			resLineAndHeaders = resLineAndHeaders + key + ": ";
			String[] values = this.headers.get(key);
			for (int i = 0; i < values.length; i++) {
				resLineAndHeaders = resLineAndHeaders + values[i];
				if (i != (values.length - 1)) {
					resLineAndHeaders = resLineAndHeaders + ", ";
				}
			}
			resLineAndHeaders = resLineAndHeaders + "\n";
		}
		// add empty line at the end
		resLineAndHeaders = resLineAndHeaders + "\n";
		return resLineAndHeaders;

	}

	public byte[] concatenateBytes(byte[] headers, byte[] body) {
		byte[] result = new byte[headers.length + body.length];
		System.arraycopy(headers, 0, result, 0, headers.length);
		System.arraycopy(body, 0, result, headers.length, body.length);

		return result;
	}

	@Override
	public String toString() {
		String response = this.getReponseLineAndHeaders() + this.body.toString();
		return response;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	/**
	 * Convert this into an array of bytes representing the HTTP response message
	 * that will be sent back to the web client.
	 * 
	 * @return the array of bytes that will be streamed back to the web client as a
	 *         fully formed HTTP response.
	 */
	public abstract byte[] getBytes();
}

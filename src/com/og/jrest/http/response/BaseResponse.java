package com.og.jrest.http;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a model for the http response sent back to the end user.
 * 
 * @author matthew.shoemaker
 *
 */
public abstract class HTTPResponse {

	protected HTTPVersion httpVersion;
	protected HTTPResponseCode responseCode;
	protected List<HTTPHeader> headers;
	protected Object body;

	public HTTPResponse() {
		this.headers = new ArrayList<>();
		this.body = null;
		this.httpVersion = HTTPVersion.HTTP11;
		this.responseCode = new HTTPResponseCode(200);
	}

	/**
	 * Convert this into an array of bytes representing the HTTP response message
	 * that will be sent back to the web client.
	 * 
	 * @return the array of bytes that will be streamed back to the web client as a
	 *         fully formed HTTP response.
	 */
	public abstract byte[] getBytes();

	/**
	 * Set the headers
	 * 
	 * @param key    the key for the header
	 * @param values the values for the header
	 * 
	 */
	public void addHeader(String key, String[] values) {
		this.headers.add(new HTTPHeader(key, values));
	}

	/**
	 * Set the Http version
	 * 
	 * @param version the version of http to have in the response
	 * 
	 */
	public void setHttpVersion(HTTPVersion version) {
		this.httpVersion = version;
	}

	/**
	 * Set status code
	 * 
	 * @param statusCode status code to set this to
	 * 
	 */
	public void setStatusCode(int statusCode) {
		this.responseCode = new HTTPResponseCode(statusCode);
	}

	/**
	 * Convenient method for subclasses to concatenate the byte arrays for the body
	 * and the headers/responseline. Concatenating primitive arrays is weirdly
	 * difficult to do.
	 * 
	 * @param headers byte array for the response line and headers
	 * @param body    byte array for the response body
	 * @return byte array with headers array and body array concatenated together
	 */
	public byte[] concatenateBytes(byte[] headers, byte[] body) {
		byte[] result = new byte[headers.length + body.length];
		System.arraycopy(headers, 0, result, 0, headers.length);
		System.arraycopy(body, 0, result, headers.length, body.length);

		return result;
	}

	/**
	 * Set the body of this response message.
	 * 
	 * @param body
	 */
	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		String response = this.getReponseLineAndHeaders();
		if (this.body != null)
			response += this.body.toString();
		return response;
	}

	/**
	 * Returns the response line and headers as they would appear in the HTTP
	 * response message, appended with a blank line to separate the body and the
	 * headers.
	 * 
	 * @return response line and headers formatted as HTTP response message
	 */
	protected String getReponseLineAndHeaders() {
		// stringify res line
		String resLineAndHeaders = this.httpVersion + " " + this.responseCode + System.lineSeparator();
		// stringify headers
		for (HTTPHeader header : this.headers) {
			resLineAndHeaders += header.toString() + System.lineSeparator();
		}
		// add empty line at the end
		resLineAndHeaders += System.lineSeparator();
		return resLineAndHeaders;
	}
}

package com.og.jrest.http.response;

import com.og.jrest.http.Header;
import com.og.jrest.http.HeaderCollection;
import com.og.jrest.http.StatusCode;
import com.og.jrest.http.Version;

/**
 * This class is a model for the HTTP Response sent back to the web user.
 * 
 * @author matthew.shoemaker
 *
 */
public abstract class Response implements IResponse {

	private Version httpVersion;
	private StatusCode responseCode;
	private HeaderCollection headers;
	private Object body;
	protected static final String CONTENT_TYPE_KEY = "Content-Type";

	public Response() {
		this.headers = new HeaderCollection();
		this.body = null;
		this.httpVersion = Version.HTTP1_1;
		this.responseCode = new StatusCode(200);
	}

	/**
	 * Set the Http version of this Response to the given version.
	 * 
	 * @param version the version of http to use in the response
	 * 
	 */
	protected void setHttpVersion(Version version) {
		this.httpVersion = version;
	}

	/**
	 * Returns the response line and headers as they would appear in the HTTP response message, appended with a blank
	 * line to separate the body and the headers.
	 * 
	 * @return response line and headers formatted as HTTP response message
	 */
	protected String getReponseLineAndHeaders() {
		// stringify res line
		String resLineAndHeaders = this.httpVersion + " " + this.responseCode + System.lineSeparator();
		// stringify headers
		for (Header header : this.headers) {
			resLineAndHeaders += header.toString() + System.lineSeparator();
		}
		// add empty line at the end
		resLineAndHeaders += System.lineSeparator();
		return resLineAndHeaders;
	}

	/**
	 * Convenient method for subclasses to concatenate the byte arrays for the body and the headers/responseline.
	 * Concatenating primitive arrays is weirdly difficult to do.
	 * 
	 * @param headers byte array for the response line and headers
	 * @param body    byte array for the response body
	 * @return byte array with headers array and body array concatenated together
	 */
	protected byte[] concatenateBytes(byte[] headers, byte[] body) {
		byte[] result = new byte[headers.length + body.length];
		System.arraycopy(headers, 0, result, 0, headers.length);
		System.arraycopy(body, 0, result, headers.length, body.length);

		return result;
	}

	/**
	 * Sets the body of this response. Only available to subclasses, the api client will set the body in the constructor
	 * of the subclass. If the api client had access to this, they could set the body of an XMLResponse to a json object
	 * or the body of an EmptyResponse to an image. That would be very problematic.
	 * 
	 * @param body the body of this response
	 */
	protected void setBody(Object body) {
		this.body = body;
	}

	@Override
	public void setStatusCode(int statusCode) {
		this.responseCode = new StatusCode(statusCode);
	}

	@Override
	public Object getBody() {
		return this.body;
	}

	@Override
	public boolean hasBody() {
		return this.body != null;
	}

	@Override
	public String toString() {
		String response = this.getReponseLineAndHeaders();
		if (this.body != null)
			response += this.body.toString();
		return response;
	}

	@Override
	public boolean hasHeader(String key) {
		for (Header existingHeader : this.headers) {
			boolean matchingHeader = key.equals(existingHeader.getKey());
			if (matchingHeader)
				return true;
		}

		return false;
	}

	@Override
	public Header getHeader(String key) {
		for (Header existingHeader : this.headers) {
			boolean matchingHeader = key.equals(existingHeader.getKey());
			if (matchingHeader) {
				return existingHeader;
			}
		}

		return null;
	}

	@Override
	public Header deleteHeader(String key) {
		Header header = this.getHeader(key);
		if (header != null) {
			this.headers.remove(header.getKey());
		}

		return header;
	}

	@Override
	public void addHeader(Header header) {
		this.headers.remove(header.getKey());
		this.headers.add(header);
	}

	@Override
	public void addHeader(String key, String[] values) {
		Header header = new Header(key, values);
		this.headers.add(header);
	}

	@Override
	public void appendHeader(String key, String[] values) {
		Header header = this.getHeader(key);
		if (header != null)
			header.appendValues(values);
		else
			header = new Header(key, values);

		this.addHeader(header);
	}

}

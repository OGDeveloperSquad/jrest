package com.og.jrest.http.response;

import com.og.jrest.http.Header;

/**
 * Subclass of HTTPResponse used to send an Error response back to the web
 * client. For example, this will be used to send a 404 error or a 500 error
 * back to the client.
 * 
 * @author matthew.shoemaker
 *
 */
public class ErrorResponse extends Response {

	private static final String CONTENT_TYPE = "text/html";

	public ErrorResponse(int responseCode) {
		super();
		this.initialize(responseCode);
	}

	private void initialize(int responseCode) {
		String body = this.getErrorBody(responseCode);
		this.setBody(body);

		Header contentType = new Header(CONTENT_TYPE_KEY, CONTENT_TYPE);
		this.addHeader(contentType);

		this.setResponseCode(responseCode);
	}

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] result = headers;
		if (this.hasBody()) {
			byte[] body = ((String) this.getBody()).getBytes();
			result = this.concatenateBytes(headers, body);
		}
		return result;
	}

	/**
	 * Given an error code, returns some pretty html to display to the web client.
	 * 
	 * @param code
	 * @return
	 */
	private String getErrorBody(int code) {

		// TODO find a way to get some sweet html in here based on the error code

		return "";
	}

}

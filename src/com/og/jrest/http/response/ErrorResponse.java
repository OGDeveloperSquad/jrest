package com.og.jrest.http;

/**
 * Subclass of HTTPResponse used to send an Error response back to the web
 * client. For example, this will be used to send a 404 error or a 500 error
 * back to the client.
 * 
 * @author matthew.shoemaker
 *
 */
public class ErrorResponse extends HTTPResponse {

	public ErrorResponse(int statusCode) {
		super();
		this.responseCode = new HTTPResponseCode(statusCode);
		this.headers.add(new HTTPHeader("Content-Type: text/html"));
		this.body = this.getErrorBody(statusCode);
	}

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] result = headers;
		if (this.body != null) {
			byte[] body = ((String) this.body).getBytes();
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
